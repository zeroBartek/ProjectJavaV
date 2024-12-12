package com.example.games.controllers;

import com.example.games.dto.GameDTO;
import com.example.games.entities.Game;
import com.example.games.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parties")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameDTO gameDto) {
        Game game = new Game();
        game.setDate(gameDto.getDate());
        game.setTypePartie(gameDto.getTypePartie());
        game.setScoreMax(gameDto.getScoreMax());
        game.setIdHote(gameDto.getIdHote());
        return ResponseEntity.ok(gameService.createGame(game));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        return gameService.getGame(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDto) {
        Game updatedGame = new Game();
        updatedGame.setDate(gameDto.getDate());
        updatedGame.setTypePartie(gameDto.getTypePartie());
        updatedGame.setScoreMax(gameDto.getScoreMax());
        updatedGame.setIdHote(gameDto.getIdHote());
        return ResponseEntity.ok(gameService.updateGame(id, updatedGame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
