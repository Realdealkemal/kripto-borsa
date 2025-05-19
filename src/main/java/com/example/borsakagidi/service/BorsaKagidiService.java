package com.example.borsakagidi.service;

import com.example.account.entity.Account;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountSpecifications;
import com.example.borsakagidi.controller.dto.GoruntuleKulanicininBorsaKagitlariiResponseDTO;
import com.example.borsakagidi.entity.BorsaKagidi;
import com.example.borsakagidi.mapper.GoruntuleBorsaKagidiMapper;
import com.example.borsakagidi.mapper.GoruntuleKulanicininBorsaKagitlariiResponseDTOMapper;
import com.example.borsakagidi.repository.BorsaKagidiRepository;
import com.example.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorsaKagidiService {

    private final BorsaKagidiRepository borsaKagidiRepository;
    private final AccountRepository accountRepository;

    public BorsaKagidiService(BorsaKagidiRepository borsaKagidiRepository, AccountRepository accountRepository) {
        this.borsaKagidiRepository = borsaKagidiRepository;
        this.accountRepository = accountRepository;
    }

    public List<BorsaKagidi> getAllBorsaKagidiList() {
        return borsaKagidiRepository.findAll();
    }

    public Optional<BorsaKagidi> getirBorsaKagidiById(Long id) {
        return borsaKagidiRepository.findById(id);
    }

    public void createBorsaKagidi(BorsaKagidi borsaKagidi) {
        if (!borsaKagidiRepository.existsByName(borsaKagidi.getName())) {
            borsaKagidiRepository.save(borsaKagidi);
        }
    }

    public void borsaKagidiAlimİslemiYap(BorsaKagidi borsaKagidi) {

    }

    public GoruntuleKulanicininBorsaKagitlariiResponseDTO getirKullaniciyaAitBorsaKagitlari(User user) {
        Account account = accountRepository.findOne(AccountSpecifications.byId(user.getAccount().getId())).orElse(null);
        return GoruntuleKulanicininBorsaKagitlariiResponseDTOMapper.INSTANCE.toGoruntuleKulanicininBorsaKagitlariiResponseDTO(account.getAccountsBorsaKagidiSet().stream().toList());
    }
}
