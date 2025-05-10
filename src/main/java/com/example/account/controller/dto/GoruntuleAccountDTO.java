package com.example.account.controller.dto;

import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GoruntuleAccountDTO {
    private Long id;
    private String name;
    private BigDecimal budget;
    //private List<AccountsBorsaKagidi> accountsBorsaKagidi;
}
