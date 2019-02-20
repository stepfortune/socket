package com.lab409.socket.demoServer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue order() {
        return new Queue("order");
    }

    @Bean
    public Queue msg() {
        return new Queue("msg");
    }

    @Bean
    public Queue storage() {
        return new Queue("storage");
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding orderQueueBinding(Queue order, TopicExchange exchange) {
        return BindingBuilder.bind(order).to(exchange).with("topic.order");
    }

    @Bean
    Binding msgQueueBinding(Queue msg, TopicExchange exchange) {
        return  BindingBuilder.bind(msg).to(exchange).with("topic.msg");
    }

    @Bean
    Binding storageQueue(Queue storage, TopicExchange exchange) {
        return BindingBuilder.bind(storage).to(exchange).with("topic.#");
    }
}
