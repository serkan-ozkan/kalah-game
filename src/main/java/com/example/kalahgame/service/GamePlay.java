package com.example.kalahgame.service;

import com.example.kalahgame.model.Board;
import com.example.kalahgame.model.Game;
import com.example.kalahgame.model.GameStatus;
import com.example.kalahgame.model.Pit;
import org.springframework.stereotype.Component;

/**
 * Rules and interactions of the game.
 */
@Component
public class GamePlay {

    public void play(Game game, Pit pit){

        if(game.getGameStatus() != GameStatus.PLAYER1_TURN && game.getGameStatus() != GameStatus.PLAYER2_TURN) return;

        Board board = game.getCurrentBoard();
        Integer stonesOnPit = pit.getStoneAmount();
        GameStatus currentGameStatus = game.getGameStatus();
        if(!isMoveValid(currentGameStatus, pit.getPitIndex())){
            return;
        }
        pit.setStoneAmount(0);



        int i = stonesOnPit;
        int j = pit.getPitIndex() + 1;
        while (i > 0){
            if((currentGameStatus == GameStatus.PLAYER1_TURN && j != 13) || (currentGameStatus == GameStatus.PLAYER2_TURN && j != 6)){
                board.getPitByPitIndex(j%14).setStoneAmount(board.getPitByPitIndex(j%14).getStoneAmount()+1);
                i--;
            }

            if(j == 13) j = 0;
            else j++;
        }
        if(j == 0) j = 14;
        Integer lastPitIndex = (j-1) % 14;
        Pit lastPit = board.getPitByPitIndex(lastPitIndex);

        if(isCaptureMove(board, lastPit, currentGameStatus)){
            if (currentGameStatus==GameStatus.PLAYER1_TURN){
                Integer capturedStoneAmount = board.getStoneAmountByPitIndex(lastPitIndex) + board.getOppositePit(lastPit).getStoneAmount();
                Integer houseStoneAmount = board.getStoneAmountByPitIndex(6);
                board.getPitByPitIndex(6).setStoneAmount(houseStoneAmount + capturedStoneAmount);
                board.getPitByPitIndex(lastPitIndex).setStoneAmount(0);
                board.getOppositePit(lastPit).setStoneAmount(0);
            }
            else if (currentGameStatus==GameStatus.PLAYER2_TURN) {
                Integer capturedStoneAmount = board.getStoneAmountByPitIndex(lastPitIndex) + board.getOppositePit(lastPit).getStoneAmount();
                Integer houseStoneAmount = board.getStoneAmountByPitIndex(13);
                board.getPitByPitIndex(13).setStoneAmount(houseStoneAmount + capturedStoneAmount);
                board.getPitByPitIndex(lastPitIndex).setStoneAmount(0);
                board.getOppositePit(lastPit).setStoneAmount(0);
            }
        }

        game.setGameStatus(decideNextTurn(currentGameStatus, board, lastPitIndex));


    }

    private GameStatus decideNextTurn(GameStatus currentGameStatus,Board currentBoard, Integer lastPitIndex){
        if(currentBoard.getPlayer1TotalStoneAmount() == 0) {
            Integer remainingStones = currentBoard.getPlayer2TotalStoneAmount();
            Integer player2HouseStones = currentBoard.getStoneAmountByPitIndex(13);
            currentBoard.getPitByPitIndex(13).setStoneAmount(remainingStones + player2HouseStones);
            if(currentBoard.getStoneAmountByPitIndex(6) > currentBoard.getStoneAmountByPitIndex(13)) return GameStatus.PLAYER1_WON;
            else if (currentBoard.getStoneAmountByPitIndex(6) < currentBoard.getStoneAmountByPitIndex(13)) return GameStatus.PLAYER2_WON;
            else return GameStatus.DRAW;
        }

        if(currentBoard.getPlayer2TotalStoneAmount() == 0) {
            Integer remainingStones = currentBoard.getPlayer1TotalStoneAmount();
            Integer player1HouseStones = currentBoard.getStoneAmountByPitIndex(6);
            currentBoard.getPitByPitIndex(6).setStoneAmount(remainingStones + player1HouseStones);
            if(currentBoard.getStoneAmountByPitIndex(6) > currentBoard.getStoneAmountByPitIndex(13)) return GameStatus.PLAYER1_WON;
            else if (currentBoard.getStoneAmountByPitIndex(6) < currentBoard.getStoneAmountByPitIndex(13)) return GameStatus.PLAYER2_WON;
            else return GameStatus.DRAW;
        }



        if(currentGameStatus == GameStatus.PLAYER1_TURN && lastPitIndex != 6){
            return GameStatus.PLAYER2_TURN;
        }

        else if (currentGameStatus == GameStatus.PLAYER2_TURN && lastPitIndex != 13){
            return GameStatus.PLAYER1_TURN;
        }

        return currentGameStatus;
    }

    private boolean isMoveValid(GameStatus currentGameStatus, Integer pitIndex){
        if(currentGameStatus == GameStatus.PLAYER1_TURN && pitIndex > 6) {
            return false;
        }
        if(currentGameStatus == GameStatus.PLAYER2_TURN && pitIndex < 6) {
            return false;
        }
        return true;
    }

    private boolean isCaptureMove(Board board, Pit lastPit, GameStatus currentGameStatus){
        if(currentGameStatus == GameStatus.PLAYER1_TURN &&
                lastPit.getPitIndex() < 6 && !lastPit.isHouse() &&
                board.getStoneAmountByPitIndex(lastPit.getPitIndex()) == 1 &&
                board.getOppositePit(lastPit).getStoneAmount() != 0){
            return true;
        }
        if(currentGameStatus == GameStatus.PLAYER2_TURN &&
                lastPit.getPitIndex() > 6 && !lastPit.isHouse() &&
                board.getStoneAmountByPitIndex(lastPit.getPitIndex()) == 1 &&
                board.getOppositePit(lastPit).getStoneAmount() != 0){
            return true;
        }

        return false;
    }
}
