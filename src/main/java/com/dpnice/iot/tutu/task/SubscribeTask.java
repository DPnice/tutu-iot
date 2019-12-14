package com.dpnice.iot.tutu.task;

import com.dpnice.iot.tutu.service.MqttServer;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author DPnice
 * @date 2019-12-14 下午 10:02
 */
@Component
public class SubscribeTask  implements ApplicationRunner {

    /**
     * 温度 湿度 水量 topic
     */
    @Value("${mqtt.topic}")
    private String topic;

    @Value("${mqtt.qos}")
    private int qos;

    private final MqttCallbackExtended handle;

    private final MqttServer mqttServer;

    @Autowired
    public SubscribeTask(MqttServer mqttServer, @Qualifier("THWHandle") MqttCallbackExtended handle) {
        this.mqttServer = mqttServer;
        this.handle = handle;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mqttServer.setCallback(handle);
        mqttServer.subscribe(topic,qos);
    }
}
