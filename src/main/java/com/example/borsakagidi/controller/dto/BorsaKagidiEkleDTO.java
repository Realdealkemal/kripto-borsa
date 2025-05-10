package com.example.borsakagidi.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorsaKagidiEkleDTO {
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer stock;
}
