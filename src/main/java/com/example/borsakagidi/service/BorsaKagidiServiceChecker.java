package com.example.borsakagidi.service;

import com.example.account.entity.Account;
import com.example.borsakagidi.repository.BorsaKagidiRepository;
import org.springframework.stereotype.Service;

@Service
public class BorsaKagidiServiceChecker {

    private final BorsaKagidiRepository borsaKagidiRepository;

    public BorsaKagidiServiceChecker(BorsaKagidiRepository borsaKagidiRepository) {
        this.borsaKagidiRepository = borsaKagidiRepository;
    }

    public boolean isYeterliStockVar(Integer stockVt,Integer talepEdilenMiktar) {
        if (stockVt < talepEdilenMiktar) {
            return false;
        }
        return true;
    }
}
