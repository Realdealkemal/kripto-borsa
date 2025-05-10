package com.example.account.mapper;

import com.example.account.controller.dto.AccountEkleDTO;
import com.example.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccacuntEkleDTOMapper {

    AccacuntEkleDTOMapper INSTANCE = Mappers.getMapper(AccacuntEkleDTOMapper.class);

    Account toAccount(AccountEkleDTO accountEkleDTO);
}
