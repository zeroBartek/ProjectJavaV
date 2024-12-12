package com.example.games.services;

import com.example.games.entities.Game;
import com.example.games.entities.Participation;
import com.example.games.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_API_URL = "http://localhost:8080/joueurs/";

    public Game createGame(Game game) {
        String url = USER_API_URL + game.getIdHote();
        try {
            restTemplate.getForObject(url, Void.class);
        } catch (Exception e) {
            throw new RuntimeException("Host with ID " + game.getIdHote() + " does not exist in the user service.");
        }

        return gameRepository.save(game);
    }

    public Optional<Game> getGame(Long id) {
        return gameRepository.findById(id);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        game.setDate(updatedGame.getDate());
        game.setTypePartie(updatedGame.getTypePartie());
        game.setScoreMax(updatedGame.getScoreMax());
        game.setIdHote(updatedGame.getIdHote());
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

}
