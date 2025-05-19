package com.example.borsakagidi.mapper;


import com.example.accountsborsakagidi.entity.AccountsBorsaKagidi;
import com.example.borsakagidi.controller.dto.GoruntuleKulanicininBorsaKagidiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GoruntuleKulanicininBorsaKagidiDTOMapper {

    GoruntuleKulanicininBorsaKagidiDTOMapper INSTANCE = Mappers.getMapper(GoruntuleKulanicininBorsaKagidiDTOMapper.class);

    @Mapping(target = "name",source = "accountsBorsaKagidi.borsaKagidi.name")
    @Mapping(target = "price",source = "accountsBorsaKagidi.borsaKagidi.price")
    @Mapping(target = "stock",source = "accountsBorsaKagidi.borsaKagidi.stock")
    @Mapping(target = "eldekiAdet",source = "accountsBorsaKagidi.adet")
    GoruntuleKulanicininBorsaKagidiDTO toGoruntuleKulanicininBorsaKagidiDTO(AccountsBorsaKagidi accountsBorsaKagidi) ;

    List<GoruntuleKulanicininBorsaKagidiDTO> toGoruntuleKulanicininBorsaKagidiDTOList(List<AccountsBorsaKagidi> accountsBorsaKagidi) ;
}
