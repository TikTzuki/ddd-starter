package org.dyson.dddstarter.config;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.dyson.dddstarter.order.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Repositories {
    @Bean
    public Repository<Order> repositoryForOrder(EventStore eventStore) {
        return EventSourcingRepository.builder(Order.class).eventStore(eventStore).build();
    }
}
