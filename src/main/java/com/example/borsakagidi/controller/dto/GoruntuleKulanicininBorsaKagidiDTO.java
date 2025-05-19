package com.example.borsakagidi.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoruntuleKulanicininBorsaKagidiDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Integer eldekiAdet;
}