package org.tiktzuki.dddstater.domains.user.model;

import lombok.Data;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;

@Entity
@Table(name = "usr")
@org.hibernate.annotations.Table(appliesTo = "usr", comment = "Nhân viên, quản lý, chủ nhà hàng")
@Data
public class User extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.USER, sequenceName = Sequences.USER, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.USER)
    private Long id;
    private String username;
    private String phoneNumber;
    private String email;

}
