package com.example.games.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipationDTO {
    private Long gameId;
    private Long playerId;
    private Integer score;
}
