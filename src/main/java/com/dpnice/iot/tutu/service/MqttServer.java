package com.dpnice.iot.tutu.service;

import org.eclipse.paho.client.mqttv3.*;
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
     * 设置回调
     *
     * @param callback MqttCallbackExtended
     */
    public void setCallback(MqttCallbackExtended callback) {
        this.mqttClient.setCallback(callback);
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
        logger.info("消息发布完成 {} msg :{}", token.isComplete(), message);
    }

    /**
     * 订阅消息
     */
    public void subscribe(String[] topics, int[] qos) throws Exception {
        mqttClient.subscribe(topics, qos);
        logger.info("订阅主题: {} 成功！", String.join(",", topics));
    }

    /**
     * 订阅消息
     */
    public void subscribe(String topic, int qos) throws Exception {
        mqttClient.subscribe(topic, qos);
        logger.info("订阅主题: {} 成功！", String.join(",", topic));
    }

    /**
     * 取消订阅
     */
    public void unsubscribe(String topic) throws Exception {
        mqttClient.unsubscribe(topic);
        logger.info("取消订阅成功,取消主题：{}", topic);
    }
}
