package com.example.demo.mappers;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.UserEntity;

public class UserMapper {

    public static UserDTO toUserDTO(UserEntity user){

        if(user == null){
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public static UserEntity toUserEntity(UserDTO userDTO){

        if(userDTO == null){
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }
}
