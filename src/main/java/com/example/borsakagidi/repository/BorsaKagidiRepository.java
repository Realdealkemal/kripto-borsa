package com.example.borsakagidi.repository;

import com.example.borsakagidi.entity.BorsaKagidi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorsaKagidiRepository extends JpaRepository<BorsaKagidi, Long> {
    Boolean existsByName(String borsaKagidiName);
}
