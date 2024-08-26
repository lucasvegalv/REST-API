package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.dtos.UserDTO;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CRUD Methods

    // CREATE
    public void createUser(UserDTO userDTO) {
        UserEntity user = UserMapper.toUserEntity(userDTO);
        userRepository.save(user);
    }

    // READ ALL
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
        return userDTOs;
    }

    // READ BY ID
    public UserDTO getUserById(Integer id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            UserDTO userDTO = UserMapper.toUserDTO(userEntity)  ;
            return userDTO;
        } else {
            // Manejar el caso donde no se encuentra el usuario, tal vez lanzar una excepción
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    // UPDATE
    public UserDTO updateUser(@PathVariable Integer id, UserDTO userDTO) {

        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserDTO updatedUserDTO = null;

        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            userEntity.setUsername(userDTO.getUsername());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setEmail(userDTO.getEmail());

            userEntity = userRepository.save(userEntity);
            updatedUserDTO = UserMapper.toUserDTO(userEntity);
        }
        return updatedUserDTO;
    }

    // DELETE BY ID
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // DELETE ALL
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
