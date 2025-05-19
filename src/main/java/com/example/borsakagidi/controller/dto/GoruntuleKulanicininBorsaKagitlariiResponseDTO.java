package com.example.borsakagidi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoruntuleKulanicininBorsaKagitlariiResponseDTO {
    private List<GoruntuleKulanicininBorsaKagidiDTO> goruntuleKulanicininBorsaKagidiDTOList;
}