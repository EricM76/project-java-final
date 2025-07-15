package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.UserDTO;
import com.talento_tech.mercado_liebre.dto.UserUpdateDTO;
import com.talento_tech.mercado_liebre.exception.ResourceNotFoundException;
import com.talento_tech.mercado_liebre.model.Rol;
import com.talento_tech.mercado_liebre.model.User;
import com.talento_tech.mercado_liebre.repository.RolRepository;
import com.talento_tech.mercado_liebre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RolRepository rolRepository
    ){
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    private UserDTO mapToUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getImage(),
                user.getRol()
        );
    }

    @Override
    public List<UserDTO> listUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUserDTO);
    }

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User updateUser(
            Long id,
            UserUpdateDTO userUpdateDTO
    ){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " +id ));

        existingUser.setName(userUpdateDTO.getName());
        //TODO: agregar los demás campos

        if(userUpdateDTO.getRolId() != null){
            Rol rol = rolRepository.findById(userUpdateDTO.getRolId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userUpdateDTO.getRolId()));
            existingUser.setRol(rol);
        }
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
