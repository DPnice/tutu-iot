package com.dpnice.iot.tutu.handle;

import com.dpnice.iot.tutu.handle.alarm.AlarmContext;
import com.dpnice.iot.tutu.handle.alarm.AlarmType;
import com.dpnice.iot.tutu.handle.alarm.CompareResult;
import com.dpnice.iot.tutu.model.Alarm;
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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${alarm.humidity}")
    private String humidity;

    @Value("${alarm.temperature}")
    private String temperature;

    @Value("${alarm.water}")
    private String water;

    private final AlarmContext alarmContext;

    @Resource
    private final HumiditySequentialRepository humiditySequentialRepository;

    @Resource
    private final TemperatureSequentialRepository temperatureSequentialRepository;

    @Resource
    private final WaterQuantitySequentialRepository waterQuantitySequentialRepository;

    @Resource
    private final AlarmRepository alarmRepository;

    public THWHandle(AlarmContext alarmContext, HumiditySequentialRepository humiditySequentialRepository,
                     TemperatureSequentialRepository temperatureSequentialRepository,
                     WaterQuantitySequentialRepository waterQuantitySequentialRepository,
                     AlarmRepository alarmRepository) {
        this.alarmContext = alarmContext;
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
        logger.debug("是否是重新连接:{}", reconnect);
        logger.debug("serverURI:{}", serverURI);
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
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        //subscribe后得到的消息会执行到这里面
        logger.debug("接收消息主题:{}", topic);
        logger.debug("接收消息Qos:{}", message.getQos());
        String msg = new String(message.getPayload());
        logger.debug("接收消息内容:{}", msg);
        Date systemTime = getSystemTime();
        String[] split = msg.split(",");
        double t = Double.parseDouble(split[0]);
        double h = Double.parseDouble(split[1]);
        double w = Double.parseDouble(split[2]);

        CompareResult alarmT = alarmContext.isAlarm(AlarmType.T, temperature, t);
        if (alarmT.isAlarm()) {
            Alarm alarm = new Alarm();
            alarm.setAlarm(alarmT.toString() + ",当前温度:" + t);
            alarm.setType(AlarmType.T.value());
            alarm.setAlarmId(getUUID());
            alarm.setAlarmTime(systemTime);
            alarmRepository.save(alarm);
            logger.debug("温度告警:{}", alarmT + ",当前温度:" + t);
        }
        try {

            CompareResult alarmH = alarmContext.isAlarm(AlarmType.H, humidity, h);
            if (alarmH.isAlarm()) {
                Alarm alarm = new Alarm();
                alarm.setAlarm(alarmH.toString() + ",当前湿度:" + h);
                alarm.setType(AlarmType.H.value());
                alarm.setAlarmId(getUUID());
                alarm.setAlarmTime(systemTime);
                alarmRepository.save(alarm);
                logger.debug("湿度告警:{}", alarmH + ",当前湿度:" + h);
            }

            if (w < Double.parseDouble(water)) {
                Alarm alarm = new Alarm();
                alarm.setAlarm("水量低于 " + water + " ml");
                alarm.setType(AlarmType.W.value());
                alarm.setAlarmId(getUUID());
                alarm.setAlarmTime(systemTime);
                alarmRepository.save(alarm);
                logger.debug("水量告警:{}", "水量低于 " + water + " ml,当前水量:" + w + "ml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String uuid = getUUID();
        TemperatureSequential temperature = new TemperatureSequential(uuid, t, systemTime);
        temperatureSequentialRepository.save(temperature);
        HumiditySequential humidity = new HumiditySequential(uuid, h, systemTime);
        humiditySequentialRepository.save(humidity);
        WaterQuantitySequential water = new WaterQuantitySequential(uuid, w, systemTime);
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

    private static String getUUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        uuid = uuid.substring(uuid.length() - 8);
        return uuid;
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
        logger.debug("消息发送成功:{}", token.isComplete());
    }
}
