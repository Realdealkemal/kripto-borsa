package com.example.account.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParaAktarDTO {
    private BigDecimal aktarilacakTutar;
    @NotNull
    private Long aktarilacakHesapId;
    private Long nerdenAktarimYapilacakHesapAdi;

}
