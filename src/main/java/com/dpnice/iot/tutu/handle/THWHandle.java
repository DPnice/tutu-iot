package com.dpnice.iot.tutu.handle;

import com.dpnice.iot.tutu.model.HumiditySequential;
import com.dpnice.iot.tutu.model.TemperatureSequential;
import com.dpnice.iot.tutu.model.WaterQuantitySequential;
import com.dpnice.iot.tutu.repository.AlarmRepository;
import com.dpnice.iot.tutu.repository.HumiditySequentialRepository;
import com.dpnice.iot.tutu.repository.TemperatureSequentialRepository;
import com.dpnice.iot.tutu.repository.WaterQuantitySequentialRepository;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author DPnice
 * @date 2019-12-14 下午 10:20
 * 温度湿度水量 处理器
 */
@Component("THWHandle")
public class THWHandle implements MqttCallbackExtended {
    private static Logger logger = LoggerFactory.getLogger(THWHandle.class);

    @Resource
    private final HumiditySequentialRepository humiditySequentialRepository;

    @Resource
    private final TemperatureSequentialRepository temperatureSequentialRepository;

    @Resource
    private final WaterQuantitySequentialRepository waterQuantitySequentialRepository;

    @Resource
    private final AlarmRepository alarmRepository;

    public THWHandle(HumiditySequentialRepository humiditySequentialRepository, TemperatureSequentialRepository temperatureSequentialRepository, WaterQuantitySequentialRepository waterQuantitySequentialRepository, AlarmRepository alarmRepository) {
        this.humiditySequentialRepository = humiditySequentialRepository;
        this.temperatureSequentialRepository = temperatureSequentialRepository;
        this.waterQuantitySequentialRepository = waterQuantitySequentialRepository;
        this.alarmRepository = alarmRepository;
    }

    /**
     * Called when the connection to the server is completed successfully.
     *
     * @param reconnect If true, the connection was the result of automatic reconnect.
     * @param serverURI The server URI that the connection was made to.
     */
    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        logger.info("是否是重新连接:{}", reconnect);
        logger.info("serverURI:{}", serverURI);
    }

    /**
     * This method is called when the connection to the server is lost.
     *
     * @param cause the reason behind the loss of connection.
     */
    @Override
    public void connectionLost(Throwable cause) {
        logger.warn(String.format("连接断开 %s", cause.getMessage()));
    }

    /**
     * This method is called when a message arrives from the server.
     *
     * <p>
     * This method is invoked synchronously by the MQTT client. An
     * acknowledgment is not sent back to the server until this
     * method returns cleanly.</p>
     * <p>
     * If an implementation of this method throws an <code>Exception</code>, then the
     * client will be shut down.  When the client is next re-connected, any QoS
     * 1 or 2 messages will be redelivered by the server.</p>
     * <p>
     * Any additional messages which arrive while an
     * implementation of this method is running, will build up in memory, and
     * will then back up on the network.</p>
     * <p>
     * If an application needs to persist data, then it
     * should ensure the data is persisted prior to returning from this method, as
     * after returning from this method, the message is considered to have been
     * delivered, and will not be reproducible.</p>
     * <p>
     * It is possible to send a new message within an implementation of this callback
     * (for example, a response to this message), but the implementation must not
     * disconnect the client, as it will be impossible to send an acknowledgment for
     * the message being processed, and a deadlock will occur.</p>
     *
     * @param topic   name of the topic on the message was published to
     * @param message the actual message.
     * @throws Exception if a terminal error has occurred, and the client should be
     *                   shut down.
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //subscribe后得到的消息会执行到这里面
        logger.debug("接收消息主题:{}", topic);
        logger.debug("接收消息Qos:{}", message.getQos());
        String msg = new String(message.getPayload());
        logger.debug("接收消息内容:{}", msg);
        //todo 处理

        String[] split = msg.split(",");
        String t = split[0];
        String h = split[1];
        String w = split[2];



        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        uuid = uuid.substring(uuid.length() - 8);

        Date systemTime = getSystemTime();
        TemperatureSequential temperature = new TemperatureSequential(uuid, Double.parseDouble(t), systemTime);
        temperatureSequentialRepository.save(temperature);
        HumiditySequential humidity = new HumiditySequential(uuid, Double.parseDouble(h), systemTime);
        humiditySequentialRepository.save(humidity);
        WaterQuantitySequential water = new WaterQuantitySequential(uuid, Double.parseDouble(w), systemTime);
        waterQuantitySequentialRepository.save(water);

    }

    private static Date getSystemTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Called when delivery for a message has been completed, and all
     * acknowledgments have been received. For QoS 0 messages it is
     * called once the message has been handed to the network for
     * delivery. For QoS 1 it is called when PUBACK is received and
     * for QoS 2 when PUBCOMP is received. The token will be the same
     * token as that returned when the message was published.
     *
     * @param token the delivery token associated with the message.
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("消息发送成功:{}", token.isComplete());
    }
}
