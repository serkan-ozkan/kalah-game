package com.example.kalahgame.api;


import com.example.kalahgame.model.Game;
import com.example.kalahgame.model.GameStateResponse;
import com.example.kalahgame.model.GameStatus;
import com.example.kalahgame.model.Pit;
import com.example.kalahgame.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * End point for the game
 */
@Slf4j
@RestController
@RequestMapping("api")
@CrossOrigin("http://localhost:8081/")
public class GameApi {
    private final GameService gameService;

    public GameApi(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public ResponseEntity<GameStateResponse> initBoard() {
        Game initGame = gameService.initGame();
        List<Integer> stoneAmounts = initGame.getCurrentBoard().getAllStoneAmounts();
        GameStatus firstGameStatus = GameStatus.PLAYER1_TURN;
        GameStateResponse gameStateResponse = new GameStateResponse(stoneAmounts, firstGameStatus);
        return  ResponseEntity.status(HttpStatus.OK).body(gameStateResponse);
    }

    @PutMapping("/play/{pitIndex}")
    public ResponseEntity<GameStateResponse> play(@PathVariable Integer pitIndex){
        Game game = gameService.play(pitIndex);
        List<Integer> stoneAmounts = game.getCurrentBoard().getAllStoneAmounts();
        GameStateResponse gameStateResponse = new GameStateResponse(stoneAmounts, game.getGameStatus());
        return  ResponseEntity.status(HttpStatus.OK).body(gameStateResponse);
    }
}
