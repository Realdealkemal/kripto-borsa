package com.example.accountsborsakagidi.entity.repository;

import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsBorsaKagidiRepository extends JpaRepository<AccountsBorsaKagidi, Long> {

    Optional<AccountsBorsaKagidi> findByAccountIdAndBorsaKagidiId(Long accountId, Long borsaKagidi);
}
