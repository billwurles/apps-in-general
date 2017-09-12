package wburles.uk.draughts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wburles.uk.draughts.MySQL.GameDAO;

public class GameActivity extends AppCompatActivity {
    private static GameBoard game;
    private GamePiece[] board;
    private GameDAO gameDatabase;
    private static GridView boardGrid;
    private final static GamePiece EMPTY_CELL = new GamePiece(0);

    private static BoardAdapter boardAdapter;
    private int selectedPos = -1;
    private int turn=GameBoard.TEAM_BLACK;

    private TextView playerOne;
    private TextView playerTwo;
    boolean gameover = false;

    @Override
    protected void onDestroy() {
        gameDatabase.setExists(getApplicationContext());
        gameDatabase.addBoard(getApplicationContext(), game);
        super.onDestroy();
    }

    private void gameOver(){
        if(whiteWinCheck()&&!gameover){
            Toast.makeText(getApplicationContext(), "GAME OVER "+playerOne.getText()+" Wins!", Toast.LENGTH_LONG).show();
            playerOne.setBackgroundColor(Color.CYAN);
            playerTwo.setBackgroundColor(Color.BLACK);
            gameDatabase.addPoint(getApplicationContext(), game.getPlayers()[0]);
        }
        if(blackWinCheck()&&!gameover){
            Toast.makeText(getApplicationContext(), "GAME OVER "+playerTwo.getText()+" Wins!", Toast.LENGTH_LONG).show();
            playerTwo.setBackgroundColor(Color.CYAN);
            playerOne.setBackgroundColor(Color.BLACK);
            gameDatabase.addPoint(getApplicationContext(), game.getPlayers()[1]);
        }
        gameover = true;
    }

    private boolean whiteWinCheck() {
        for (GamePiece piece : game.getBoard()) {
            if (piece.getTeam() == GameBoard.TEAM_BLACK) {
                return false;
            }
        }
        return true;
    }
    private boolean blackWinCheck() {
        for(GamePiece piece : game.getBoard()){
            if(piece.getTeam()==GameBoard.TEAM_WHITE){
                return false;
            }
        }
        return true;
    }

    private void changeTurn(){
        game.deselectAll();
        game.depossAll();
        selectedPos = -1;
        if(turn==GameBoard.TEAM_WHITE){
            turn=GameBoard.TEAM_BLACK;
            playerOne.setBackgroundColor(Color.TRANSPARENT);
            playerTwo.setBackgroundColor(Color.YELLOW);
        } else {
            turn=GameBoard.TEAM_WHITE;
            playerOne.setBackgroundColor(Color.YELLOW);
            playerTwo.setBackgroundColor(Color.TRANSPARENT);
        }
        boardAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameDatabase = new GameDAO();
        boolean[] settings = gameDatabase.getSettings(getApplicationContext());

        playerOne = (TextView) findViewById(R.id.text_player_one);
        playerTwo = (TextView) findViewById(R.id.text_player_two);

        switch(getIntent().getExtras().getInt("button")) {
            case 0: // new game
                ArrayList<User> players = getIntent().getParcelableArrayListExtra("players");
                game = new GameBoard(getApplicationContext(), settings[0],
                        players.get(0).getUser(), players.get(1).getUser());
                game.setEmpty(EMPTY_CELL);
                game.setUpBoard();
                playerOne.setText(players.get(0).getUser());
                playerTwo.setText(players.get(1).getUser());
                break;
            case 1: // load game
                game = gameDatabase.getGame(getApplicationContext());
                game.setEmpty(EMPTY_CELL);
                String[] player = game.getPlayers();
                playerOne.setText(player[0]);
                playerTwo.setText(player[1]);
                break;
        }
        board = game.getBoard();
        boardGrid = (GridView) findViewById(R.id.board_grid);
        boardAdapter = new BoardAdapter(this, board, EMPTY_CELL, settings[1]);
        boardGrid.setAdapter(boardAdapter);
        changeTurn();
        boardGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!whiteWinCheck() && !blackWinCheck()) {
                    GamePiece item = board[position];
                    if (selectedPos != -1) {
                        if (position == selectedPos) { // if click the same spot then deselect
                            game.getBoard()[selectedPos].select();
                            game.deselectAll();
                            selectedPos = -1;
                            boardAdapter.notifyDataSetChanged();
                        } else { // attempt to move selected piece
                            if (game.checkTakes(turn)) {
                                boardAdapter.notifyDataSetChanged();
                                game.takePiece(selectedPos, position, getApplicationContext());
                                boardAdapter.notifyDataSetChanged();
                                if (!game.checkTakes(turn)) {
                                    changeTurn();
                                } else {
                                    selectedPos = -1;
                                    Toast.makeText(getApplicationContext(), "There are more moves to make",
                                            Toast.LENGTH_LONG).show();
                                }
                            } else if (game.checkMoves(turn)) {
                                game.move(selectedPos, position);
                                changeTurn();
                            }

                        }
                    } else if (item != EMPTY_CELL) { // select if click on a piece
                        if (item.getTeam() == turn && !game.gameOver(GameBoard.TEAM_WHITE) && !game.gameOver(GameBoard.TEAM_BLACK)) {
                            item.select();
                            selectedPos = position;
                            if (!game.checkTakes(turn)) {
                                game.checkMoves(turn);
                            }
                            boardAdapter.notifyDataSetChanged();
                        }
                    }
                } else{ gameOver(); }
            }
        });
    }
}
