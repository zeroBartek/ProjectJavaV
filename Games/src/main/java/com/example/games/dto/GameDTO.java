package com.example.games.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class GameDTO {
    private LocalDate date;
    private String typePartie;
    private int scoreMax;
    private Long idHote;
}
