package com.example.account.repository;

import com.example.account.entity.Account;
import jakarta.persistence.criteria.JoinType;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class AccountSpecifications {

    public static Specification<Account> byId(Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            root.fetch("accountsBorsaKagidiSet", JoinType.LEFT);
            return criteriaBuilder.equal(root.get("id"), id);
        };
        }

}
