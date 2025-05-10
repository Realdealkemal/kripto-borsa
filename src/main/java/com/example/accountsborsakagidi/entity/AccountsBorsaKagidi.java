package com.example.accountsborsakagidi.entity;

import com.example.account.entity.Account;
import com.example.borsakagidi.entity.BorsaKagidi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "accounts_borsa_kagidi",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"account_id", "borsa_kagidi_id"})
        }
)
public class AccountsBorsaKagidi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "adet")
    private Integer adet;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "borsa_kagidi_id")
    private BorsaKagidi borsaKagidi;

    public void borsaKagidiAlimEntitysiOlustur (Account account, BorsaKagidi borsaKagidi, Integer adet) {
        this.account = account;
        this.borsaKagidi = borsaKagidi;
        this.adet = adet;
    }
}
