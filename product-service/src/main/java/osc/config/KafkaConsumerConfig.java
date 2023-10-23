package osc.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import osc.events.OrderEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new StringDeserializer()
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, OrderEvent> productEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(OrderEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderEvent> productEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(productEventConsumerFactory());
        return factory;
    }

//    @Bean
//    public ConsumerFactory<String, List <ProductDto>> productconsumerFactory(){
//        Map<String,Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
//        ObjectMapper om = new ObjectMapper();
//        JavaType type = om.getTypeFactory().constructParametricType(List.class, ProductDto.class);
//        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new JsonDeserializer<List<ProductDto>>(type, om, false));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, List<ProductDto>> productKafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, List<ProductDto>> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(productconsumerFactory ());
//        return factory;
//    }
}

