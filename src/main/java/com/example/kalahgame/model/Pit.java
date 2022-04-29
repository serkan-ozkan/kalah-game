package com.example.kalahgame.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;
/**
 * Pit in the board. Contains index, amount of stone ant player 1 or 2
 */
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pit {
    @NotNull
    private Integer pitIndex;
    @NotNull
    private Integer stoneAmount;
    @NotNull
    private Integer player;



    public Boolean isHouse(){
        return this.pitIndex.equals(Board.PLAYER1_HOUSE) || this.pitIndex.equals(Board.PLAYER2_HOUSE);
    }

    public Boolean isPlayer1House(){
        return this.player.equals(1) && this.pitIndex.equals(Board.PLAYER1_HOUSE);

    }

    public Boolean isPlayer2House(){
        return this.player.equals(2) && this.pitIndex.equals(Board.PLAYER2_HOUSE);
    }

    public Integer getOppositePitIndex(){
        return  12 - this.getPitIndex();
    }
}
