package com.example.account.entity;

import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import com.example.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @JoinColumn(name = "UserId", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name="budget")
    private BigDecimal budget;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountsBorsaKagidi> accountsBorsaKagidiSet;
}
