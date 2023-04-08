package org.dyson.dddstarter.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;
import org.dyson.dddstarter.constant.Sequences;

import jakarta.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Menu extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.MENU, sequenceName = Sequences.MENU, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.MENU)
    private Long id;

    @OneToMany(mappedBy = "menu")
    List<MenuProduct> products;
}
