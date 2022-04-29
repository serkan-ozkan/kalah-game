package com.example.kalahgame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Response class for front-end to use. It sends the stone amount and status of the game.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameStateResponse {
    private List<Integer> stoneAmounts;
    private GameStatus gamestatus;
}
