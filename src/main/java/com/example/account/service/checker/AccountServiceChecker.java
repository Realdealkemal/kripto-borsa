package com.example.account.service.checker;

import com.example.account.entity.Account;
import com.example.account.repository.AccountRepository;
import com.example.borsakagidi.entity.BorsaKagidi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceChecker {

    private final AccountRepository accountRepository;
    public AccountServiceChecker(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isAccountExits(Account account) {
        boolean exists = accountRepository.existsByName(account.getName());
        if (exists) {
            throw new RuntimeException("duplicate account");
        }
        return true;
    }

    public boolean hesaptaYetericeParavarMi(Account account, BorsaKagidi borsaKagidi, Integer alimSayisi) {
        if(account.getBudget().compareTo(BigDecimal.valueOf(alimSayisi).multiply(borsaKagidi.getPrice())) < 0){
            return false;
        }
        return true;

    }

}
