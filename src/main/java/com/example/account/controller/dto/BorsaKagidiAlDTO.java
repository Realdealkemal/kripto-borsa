package com.example.account.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorsaKagidiAlDTO {
    @NotNull
    private Long islemYapanAccoundId;
    @NotNull
    private Long islemYapilanBorsaKagidiId;
    @NotNull
    private Integer alimSayisi;
}
