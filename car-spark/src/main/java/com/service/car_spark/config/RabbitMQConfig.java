package com.service.car_spark.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "car_queue";
    public static final String EXCHANGE_NAME = "car_exchange";
    public static final String ROUTING_KEY = "car_routingKey";

    @Bean
    public Queue carQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange carExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue carQueue, TopicExchange carExchange) {
        return BindingBuilder.bind(carQueue).to(carExchange).with(ROUTING_KEY);
    }
}
