package osc.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicsConfig {

    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("update-order-topic", 1, (short) 1);
    }
    @Bean
    public NewTopic paymentTopic() {
        return new NewTopic("update-payment-topic", 1, (short) 1);
    }
//    @Bean
//    public NewTopic adviceTopic() {
//        return new NewTopic("update-payment-topic", 1, (short) 1);
//    }

}