package com.example.borsakagidi.mapper;

import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import com.example.borsakagidi.controller.dto.GoruntuleKulanicininBorsaKagidiDTO;
import com.example.borsakagidi.controller.dto.GoruntuleKulanicininBorsaKagitlariiResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {GoruntuleKulanicininBorsaKagidiDTOMapper.class})
public interface GoruntuleKulanicininBorsaKagitlariiResponseDTOMapper {
    GoruntuleKulanicininBorsaKagitlariiResponseDTOMapper INSTANCE = Mappers.getMapper(GoruntuleKulanicininBorsaKagitlariiResponseDTOMapper.class);

    default GoruntuleKulanicininBorsaKagitlariiResponseDTO toGoruntuleKulanicininBorsaKagitlariiResponseDTO(List<AccountsBorsaKagidi> accountsBorsaKagidiList) {
        List<GoruntuleKulanicininBorsaKagidiDTO> dtoList =
                GoruntuleKulanicininBorsaKagidiDTOMapper.INSTANCE.toGoruntuleKulanicininBorsaKagidiDTOList(accountsBorsaKagidiList);

        GoruntuleKulanicininBorsaKagitlariiResponseDTO response = new GoruntuleKulanicininBorsaKagitlariiResponseDTO();
        response.setGoruntuleKulanicininBorsaKagidiDTOList(dtoList);
        return response;
    }
}
