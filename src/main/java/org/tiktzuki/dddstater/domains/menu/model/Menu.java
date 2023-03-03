package org.tiktzuki.dddstater.domains.menu.model;

import lombok.Data;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;
import java.util.List;

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
