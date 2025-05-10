package com.example.borsakagidi.mapper;

import com.example.borsakagidi.controller.dto.BorsaKagidiEkleDTO;
import com.example.borsakagidi.entity.BorsaKagidi;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BorsaKagidiEkleDTOMapper {

    BorsaKagidiEkleDTOMapper INSTANCE = Mappers.getMapper(BorsaKagidiEkleDTOMapper.class);

    BorsaKagidi toBorsaKagidi(BorsaKagidiEkleDTO borsaKagidiDTO);
}
