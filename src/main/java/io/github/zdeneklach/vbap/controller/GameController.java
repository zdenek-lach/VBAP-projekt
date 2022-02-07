package io.github.zdeneklach.vbap.controller;

import io.github.zdeneklach.vbap.entity.Game;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/list")
    private ResponseEntity<List<Game>> listAllGames() {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllGames());

    }

}
