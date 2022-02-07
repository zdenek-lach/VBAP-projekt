package io.github.zdeneklach.vbap.service;

import io.github.zdeneklach.vbap.entity.Customer;
import io.github.zdeneklach.vbap.entity.Game;
import io.github.zdeneklach.vbap.entity.GameRoom;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.repositories.CustomerRepository;
import io.github.zdeneklach.vbap.repositories.GameRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRoomService {
    GameRoomRepository gameRoomRepository;

    public GameRoomService(GameRoomRepository gameRoomRepository) {
        this.gameRoomRepository = gameRoomRepository;
    }

    public List<GameRoom> getAllGameRooms() {
        List<GameRoom> allGameRooms = null;
        try {
            allGameRooms = gameRoomRepository.findAll();
        } catch (Exception e) {
            throw new DbException("Failed to retrieve all Gamerooms from DB.");
        }
        return allGameRooms;
    }
}
