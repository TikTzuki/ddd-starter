package org.dyson.dddstarter.user.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;
import org.dyson.dddstarter.constant.Sequences;

@Entity
@Table(name = "usr")
@org.hibernate.annotations.Table(appliesTo = "usr", comment = "Nhân viên, quản lý, chủ nhà hàng")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.USER, sequenceName = Sequences.USER, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.USER)
    private Long id;
    private String username;
    private String phoneNumber;
    private String email;

}
