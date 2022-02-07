package io.github.zdeneklach.vbap.controller;

import io.github.zdeneklach.vbap.entity.GameRoom;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.service.GameRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gamerooms")
public class GameRoomController {
    @Autowired
    GameRoomService gameRoomService;

    @GetMapping("/list")
    private ResponseEntity<List<GameRoom>> listAllGameRooms(){
        return ResponseEntity.status(HttpStatus.OK).body(gameRoomService.getAllGameRooms());
    }

}
