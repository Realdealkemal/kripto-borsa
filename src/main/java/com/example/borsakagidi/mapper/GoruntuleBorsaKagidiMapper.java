package com.example.borsakagidi.mapper;

import com.example.borsakagidi.controller.dto.GoruntuleBorsaKagidiDTO;
import com.example.borsakagidi.entity.BorsaKagidi;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GoruntuleBorsaKagidiMapper {

    GoruntuleBorsaKagidiMapper INSTANCE = Mappers.getMapper(GoruntuleBorsaKagidiMapper.class);

    GoruntuleBorsaKagidiDTO toGoruntuleBorsaKagidiDTO(BorsaKagidi borsaKagidi);

    List<GoruntuleBorsaKagidiDTO> toGoruntuleBorsaKagidiDTOList(List<BorsaKagidi> borsaKagidi);
}
