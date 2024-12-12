package com.example.games.repositories;

import com.example.games.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    <S extends Game> S save(S game);
}

