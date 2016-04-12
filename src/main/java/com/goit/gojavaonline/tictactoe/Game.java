package com.goit.gojavaonline.tictactoe;

/**
 * Created by SashaKulek on 07/04/2016.
 */

public class Game {
    private Board board;
    private Player playerCross;
    private Player playerZero;
    private GameState gameState;


    public Game(Board board) {
        this.board = board;
    }


    public void startGame() {
        setUpPleers();
        gameState = GameState.PLAYING;

        int[] tmp = new int[2];
        int[] tmp2 = new int[2];

        do {

            // TODO: N3 12/04/2016 add variable "turn", rewrite like if(turn % 2 > 0 : CROSS ? ZERO)
            // TODO: N4 12/04/2016 use getStatus method here
            // TODO: N5 12/04/2016 at the end ask user is he want to play again?

            int[] crossNextMoves = playerCross.getNextMoves();
            board.getCells()[crossNextMoves[0]][crossNextMoves[1]].setContent(playerCross.getPlayerSide());
            board.print();

            if(board.isWin(CellContent.CROSS)){
                gameState= GameState.CROSS_WIN;
                break;
            } else if(!board.hasEmptyCell()){
                gameState = GameState.DRAW;
                break;
            }

            int[] zeroNextMoves = playerZero.getNextMoves();
            board.getCells()[zeroNextMoves[0]][zeroNextMoves[1]].setContent(playerZero.getPlayerSide());
            board.print();

            if(board.isWin(CellContent.ZERO)){
                gameState = GameState.ZERO_WIN;
                break;
            } else if(!board.hasEmptyCell()){
                gameState = GameState.DRAW;
                break;
            }

        } while (gameState == GameState.PLAYING);




    }

    public String getGameResult(){
        if(gameState == GameState.DRAW){
            return gameState.toString();
        } else {
            return gameState.toString() + " won!";
        }
    }


    private void setUpPleers() {

        // TODO: N1 12/04/2016 write method which asks user to select CROSS or ZERO
        CellContent answer = CellContent.ZERO; // TODO: N2 13/04/2016 remove this stub line after task N1 completion
        //--------------

        if (answer == CellContent.CROSS) {
            playerCross = new HumanPlayer(board, CellContent.CROSS);
            playerZero = new AiMinMaxPlayer(board, CellContent.ZERO);
        } else if (answer == CellContent.ZERO) {
            playerCross = new AiMinMaxPlayer(board, CellContent.CROSS);
            playerZero = new HumanPlayer(board, CellContent.ZERO);
        }
    }

    private void gameStatus() {
        if (board.isWin(CellContent.CROSS)) {
            gameState = GameState.CROSS_WIN;
        } else if (board.isWin(CellContent.ZERO)) {
            gameState = GameState.ZERO_WIN;
        } else if (board.getEmptyCells().size() == 0) {
            gameState = GameState.DRAW;
        } else {
            gameState = GameState.PLAYING;
        }
    }

}

enum GameState {
    PLAYING, DRAW, CROSS_WIN, ZERO_WIN
}
