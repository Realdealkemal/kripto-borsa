package com.example.user.mapper;

import com.example.user.AuthController.dto.UserDTO;
import com.example.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDTOMapper {

    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
