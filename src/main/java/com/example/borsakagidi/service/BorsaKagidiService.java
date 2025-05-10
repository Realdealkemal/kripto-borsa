package com.example.borsakagidi.service;

import com.example.account.entity.Account;
import com.example.borsakagidi.entity.BorsaKagidi;
import com.example.borsakagidi.repository.BorsaKagidiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorsaKagidiService {

    private final BorsaKagidiRepository borsaKagidiRepository;

    public BorsaKagidiService(BorsaKagidiRepository borsaKagidiRepository) {
        this.borsaKagidiRepository = borsaKagidiRepository;
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
}
