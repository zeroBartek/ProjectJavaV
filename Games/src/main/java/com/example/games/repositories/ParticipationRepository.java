package com.example.games.repositories;

import com.example.games.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    int countByGameId(Long gameId);

}

