package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.UserDTO;
import com.talento_tech.mercado_liebre.dto.UserUpdateDTO;
import com.talento_tech.mercado_liebre.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> listUsers();
    Optional<UserDTO> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, UserUpdateDTO user);
    void deleteUser(Long id);
}
