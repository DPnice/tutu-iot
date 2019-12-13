package com.dpnice.iot.tutu.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author DPnice
 * @date 2019-12-12 下午 6:44
 */
@Service
public class MqttServer {
    private static Logger logger = LoggerFactory.getLogger(MqttServer.class);

    private final MqttClient mqttClient;

    public MqttServer(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    /**
     * 发布消息
     */
    public void publish(String topic, String message, boolean isRetained, int qos) throws Exception {
        MqttTopic mqttTopic;
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(isRetained);
        mqttMessage.setPayload(message.getBytes());
        mqttTopic = mqttClient.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
        logger.info("消息发布完成 {}", token.isComplete());
    }

    /**
     * 订阅消息
     */
    public void subscribe(String[] topics, int[] qos) throws Exception {
        mqttClient.subscribe(topics, qos);
        logger.info("订阅主题: {} 成功！", String.join(",", topics));
    }

    /**
     * 取消订阅
     */
    public void unsubscribe(String topic) throws Exception {
        mqttClient.unsubscribe(topic);
        logger.info("取消订阅成功,取消主题：{}", topic);
    }
}
