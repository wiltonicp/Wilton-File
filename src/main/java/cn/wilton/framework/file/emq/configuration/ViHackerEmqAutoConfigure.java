package cn.wilton.framework.file.emq.configuration;



import cn.wilton.framework.file.emq.service.MqttProducer;
import cn.wilton.framework.file.emq.service.impl.MqttServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViHackerEmqAutoConfigure {



    @Bean
    @ConditionalOnClass
    public MqttServiceImpl mqttService(){
        return new MqttServiceImpl();
    }

    @Bean
    @ConditionalOnClass
    public MqttProducer mqttProducer(){
        return new MqttProducer();
    }



}
