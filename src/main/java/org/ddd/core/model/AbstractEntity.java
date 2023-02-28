package org.ddd.core.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.annotation.Nullable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> extends AbstractPersistable<PK> implements IdentifiableDomainObject<PK> {
    @Version
    private Long version;

    public @NotNull Optional<Long> getVersion() {
        return Optional.ofNullable(version);
    }

    protected void setVersion(@Nullable Long version) {
        this.version = version;
    }

}
