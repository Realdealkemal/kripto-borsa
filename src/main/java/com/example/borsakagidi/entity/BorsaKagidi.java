package com.example.borsakagidi.entity;

import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BorsaKagidi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="stock")
    private Integer stock;

    @OneToMany(mappedBy = "borsaKagidi", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountsBorsaKagidi> accountsBorsaKagidi;
}
