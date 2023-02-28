package org.ddd.core.model;

import java.io.Serializable;

public abstract class LocalEntity<PK extends Serializable> extends AbstractEntity<PK> {
    public LocalEntity(PK id) {
        this.setId(id);
    }
}
