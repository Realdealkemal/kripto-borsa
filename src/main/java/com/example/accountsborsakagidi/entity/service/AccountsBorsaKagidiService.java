package com.example.accountsborsakagidi.entity.service;

import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import com.example.accountsborsakagidi.entity.repository.AccountsBorsaKagidiRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsBorsaKagidiService {

    private final AccountsBorsaKagidiRepository accountsBorsaKagidiRepository;

    public AccountsBorsaKagidiService(AccountsBorsaKagidiRepository repository) {
        this.accountsBorsaKagidiRepository = repository;
    }

    public Optional<AccountsBorsaKagidi> getirAccountsBorsaKagidiByAccountAndBorsaKagidiId(Long accountId, Long borsaKagidiId) {
        return accountsBorsaKagidiRepository.findByAccountIdAndBorsaKagidiId(accountId, borsaKagidiId);
    }
}
