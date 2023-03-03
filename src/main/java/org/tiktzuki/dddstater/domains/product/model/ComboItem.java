package org.tiktzuki.dddstater.domains.product.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.ddd.core.model.ValueObject;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import java.io.Serializable;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class ComboItem implements ValueObject, Serializable {
    Long refId;
}
