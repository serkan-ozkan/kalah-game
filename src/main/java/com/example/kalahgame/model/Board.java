package com.example.kalahgame.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Board of the game. Board contains list of pits.
 */
@Getter
@Setter
@ToString
public class Board {
    public static final Integer PLAYER1_HOUSE = 6;
    public static final Integer PLAYER2_HOUSE = 13;

    private List<Pit> pits;

    public Board(){
        this.pits = initBoard();
    }

    public Pit getPitByPitIndex(Integer pitIndex){
        return pits.get(pitIndex);
    }

    public Integer getStoneAmountByPitIndex(Integer pitIndex){
        return pits.get(pitIndex).getStoneAmount();
    }

    public List<Integer> getAllStoneAmounts(){
        List<Integer> stoneAmounts = new ArrayList<>();
        for (int i = 0; i < 14; i++){
            stoneAmounts.add(pits.get(i).getStoneAmount());
        }
        return stoneAmounts;
    }

    public Integer getPlayer1TotalStoneAmount(){
        Integer player1TotalStoneAmount = 0;
        for(int i = 0; i < Board.PLAYER1_HOUSE; i++){
            player1TotalStoneAmount += pits.get(i).getStoneAmount();
        }
        return player1TotalStoneAmount;
    }

    public Integer getPlayer2TotalStoneAmount(){
        Integer player2TotalStoneAmount = 0;
        for(int i = Board.PLAYER1_HOUSE + 1; i < Board.PLAYER2_HOUSE; i++){
            player2TotalStoneAmount += pits.get(i).getStoneAmount();
        }
        return player2TotalStoneAmount;
    }

    public Pit getOppositePit(Pit pit){
        return pits.get(pit.getOppositePitIndex());
    }


    private List<Pit> initBoard(){
        List<Pit> pits = new ArrayList<>();
        for (int i = 0; i<Board.PLAYER1_HOUSE;i++){
            Pit pit = new Pit(i, 6, 1);
            pits.add(pit);
        }
        Pit player1House = new Pit(Board.PLAYER1_HOUSE, 0, 1);
        pits.add(player1House);

        for (int i = Board.PLAYER1_HOUSE + 1; i<Board.PLAYER2_HOUSE; i++){
            Pit pit = new Pit(i, 6, 2);
            pits.add(pit);
        }
        Pit player2House = new Pit(Board.PLAYER2_HOUSE, 0, 2);
        pits.add(player2House);

        return pits;
    }
}
