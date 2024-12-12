package com.example.games.services;

import com.example.games.entities.Game;
import com.example.games.entities.Participation;
import com.example.games.entities.Type;
import com.example.games.repositories.GameRepository;
import com.example.games.repositories.ParticipationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final GameRepository gameRepository;
    private final TypeService typeService;
    private final RestTemplate restTemplate;

    @Transactional
    public Participation addParticipation(Long gameId, Long playerId, Integer score) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game with ID " + gameId + " not found"));

        if (gameId == null || playerId == null || score == null || score < 0 || score > game.getScoreMax()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }


        Type type = typeService.getTypeByName(game.getTypePartie());
        if (type == null) {
            throw new RuntimeException("Type not found for name: " + game.getTypePartie());
        }

        long playerCount = participationRepository.countByGameId(gameId);
        if (playerCount >= type.getMaxParticipants()) {
            throw new IllegalStateException("The maximum number of participants for this game has been reached");
        }

        try {
            String url = "http://localhost:8080/joueurs/" + playerId;
            restTemplate.getForEntity(url, Void.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch player details: " + e.getMessage());
        }

        Participation participation = new Participation();
        participation.setGame(game);
        participation.setPlayerId(playerId);
        participation.setScore(score);
        participation.setVictory(false);

        return participationRepository.save(participation);
    }

}

