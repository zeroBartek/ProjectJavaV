package com.example.games.controllers;

import com.example.games.dto.ParticipationDTO;
import com.example.games.entities.Participation;
import com.example.games.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parties")
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    @PostMapping("/{id}/participations")
    public ResponseEntity<Participation> addParticipation(
            @PathVariable Long id,
            @RequestBody ParticipationDTO participationDto) {
        if (id == null || participationDto.getPlayerId() == null) {
            throw new IllegalArgumentException("gameId and playerId must not be null");
        }
        Participation participation = participationService.addParticipation(
                id,
                participationDto.getPlayerId(),
                participationDto.getScore()
        );
        return ResponseEntity.ok(participation);
    }

}
