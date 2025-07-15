package com.talento_tech.mercado_liebre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public Rol() {}

    public Rol(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public void addUser(User user) {
        users.add(user);
        user.setRol(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setRol(null);
    }
}
