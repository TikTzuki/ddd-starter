package vn.unicloud.fnb.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;

@Entity
@Table(name = "usr")
@org.hibernate.annotations.Table(appliesTo = "usr", comment = "Nhân viên, quản lý, chủ nhà hàng")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String phoneNumber;
    private String email;

}
