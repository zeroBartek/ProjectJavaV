package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pseudonyme;
    private String email;
    private int lvl;
    private int totalPoints;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Friend> friends;
}

