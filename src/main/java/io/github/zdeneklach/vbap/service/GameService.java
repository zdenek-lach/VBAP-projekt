package io.github.zdeneklach.vbap.service;

import io.github.zdeneklach.vbap.entity.Game;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames(){
        List<Game> allGames = null;
        try {
            allGames = gameRepository.findAll();
        } catch (Exception e) {
            throw new DbException("Failed to retrieve all games from DB.");
        }
        return allGames;
    }
}
