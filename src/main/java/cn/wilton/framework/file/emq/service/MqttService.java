package cn.wilton.framework.file.emq.service;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttService {
    void processMessage(String topic, MqttMessage message);
}
