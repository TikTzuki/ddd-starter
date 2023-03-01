package org.tiktzuki.dddstater.domains.user.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contact {
    @Id
    private Long id;

    @Convert(converter = EmailAddressAttributeConverter.class)
    private EmailAddress emailAddress;
}
