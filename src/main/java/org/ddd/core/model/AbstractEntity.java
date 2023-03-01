package org.ddd.core.model;

import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;

import javax.annotation.Nullable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> implements Persistable<PK>, IdentifiableDomainObject<PK> {
    @Version
    private Long version;

    @Override
    @Transient
    public abstract @Nullable PK getId();

    @Override
    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    public @NotNull Optional<Long> getVersion() {
        return Optional.ofNullable(version);
    }

    protected void setVersion(@Nullable Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        var that = (AbstractEntity<?>) obj;
        var id = getId();
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        var id = getId();
        return id == null ? super.hashCode() : id.hashCode();
    }
}
