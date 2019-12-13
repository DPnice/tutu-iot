package com.dpnice.iot.tutu.config;

import com.dpnice.iot.tutu.service.MqttServer;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author DPnice
 * @date 2019-12-12 下午 6:50
 */
public class Callback implements MqttCallbackExtended {


    private static Logger logger = LoggerFactory.getLogger(Callback.class);

    /**
     * 连接丢失后
     */
    @Override
    public void connectionLost(Throwable cause) {
        logger.warn(String.format("连接断开 %s", cause.getMessage()));
    }

    /**
     * 订阅后收到到的消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        //subscribe后得到的消息会执行到这里面
        logger.info("接收消息主题:{}", topic);
        logger.info("接收消息Qos:{}", mqttMessage.getQos());
        logger.info("接收消息内容:{}", new String(mqttMessage.getPayload()));
    }

    /**
     * 消息发布成功后
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("消息发送成功:{}", iMqttDeliveryToken.isComplete());
    }

    /**
     * 连接成功后的回调
     */
    @Override
    public void connectComplete(boolean b, String s) {
        logger.info("是否是重新连接:{}", b);
        logger.info("serverURI:{}", s);
//        try {
//            //启动服务时订阅
//            String[] topics = {"Demo/a/", "Demo/b/"};
//            int[] qos = {2, 2};
//            MqttServer.subscribe(topics, qos);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            logger.info(String.format("消息订阅失败,异常信息%s", exception.getMessage()));
//        }
    }
}
