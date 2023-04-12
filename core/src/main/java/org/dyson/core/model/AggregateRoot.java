package org.dyson.core.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@MappedSuperclass
public abstract class AggregateRoot<PK extends Serializable> extends AbstractEntity<PK> {
    private transient final @Transient List<Object> domainEvents = new ArrayList<>();


    /**
     * Registers the given event object for publication on a call to a Spring Data repository's save methods.
     *
     * @param event must not be {@literal null}.
     * @return the event that has been added.
     */
    protected <T> T apply(T event) {
        Assert.notNull(event, "Domain event must not be null");
        this.domainEvents.add(event);
        return event;
    }


    /**
     * All domain events currently captured by the aggregate.
     */
    @DomainEvents
    protected Collection<Object> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    /**
     * Clears all domain events currently held. Usually invoked by the infrastructure in place in Spring Data
     * repositories.
     */
    @AfterDomainEventPublication
    protected void clearDomainEvents() {
        this.domainEvents.clear();
    }

    protected final AggregateRoot<PK> andApplyFrom(AggregateRoot<PK> aggregate) {
        Assert.notNull(aggregate, "Aggregate must not be null");
        this.domainEvents.addAll(aggregate.domainEvents());
        return this;
    }

    protected AggregateRoot<PK> andApply(Object event) {
        apply(event);
        return this;
    }
}
