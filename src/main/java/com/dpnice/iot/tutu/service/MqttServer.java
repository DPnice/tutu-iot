package com.dpnice.iot.tutu.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DPnice
 * @date 2019-12-12 下午 6:44
 */
public class MqttServer {

    @Autowired
    private static MqttClient client;
    private static Logger logger = LoggerFactory.getLogger(MqttServer.class);

    /**
     * 发布消息
     *
     */
    public static void publish(String topic, String message, boolean isRetained, int qos) throws Exception {
        MqttTopic mqttTopic;
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(isRetained);
        mqttMessage.setPayload(message.getBytes());
        mqttTopic = client.getTopic(topic);

        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
        logger.info(String.format("消息发布完成%s! ", token.isComplete()));
    }

    /**
     * 订阅消息
     *
     */
    public static void subscribe(String[] topics, int[] qos) throws Exception {
        client.subscribe(topics, qos);
        logger.info(String.format("订阅主题:%s成功！", String.join(",", topics)));
    }

    /**
     * 取消订阅
     *
     */
    public static void unsubscribe(String topic) throws Exception {
        client.unsubscribe(topic);
        logger.info(String.format("取消订阅成功,取消主题：%s", topic));
    }
}
