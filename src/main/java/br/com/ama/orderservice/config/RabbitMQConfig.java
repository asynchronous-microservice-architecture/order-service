package br.com.ama.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.payment.approved.name}")
    private String paymentApprovedQueue;

    @Value("${broker.queue.payment.rejected.name}")
    private String paymentRejectedQueue;

    @Bean
    public Queue paymentApprovedQueue() {
        return new Queue(paymentApprovedQueue, true);
    }

    @Bean
    public Queue paymentRejectedQueue() {
        return new Queue(paymentRejectedQueue, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
