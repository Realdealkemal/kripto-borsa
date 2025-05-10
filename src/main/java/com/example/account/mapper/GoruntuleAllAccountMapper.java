package com.example.account.mapper;

import com.example.account.controller.dto.GoruntuleAccountDTO;
import com.example.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GoruntuleAllAccountMapper {

    GoruntuleAllAccountMapper INSTANCE = Mappers.getMapper(GoruntuleAllAccountMapper.class);

    GoruntuleAccountDTO toGoruntuleAllAccountDTO(Account account);

    List<GoruntuleAccountDTO> toGoruntuleAllAccountDTOList(List<Account> accounts);
}
