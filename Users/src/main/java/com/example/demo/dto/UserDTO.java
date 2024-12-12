package com.example.demo.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class UserDTO {
    private Long id;
    private String name;
    private String pseudonyme;
    private String email;
    private int lvl;
    private int totalPoints;
    private List<Long> friendIds;
}

