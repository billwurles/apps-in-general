package wburles.uk.draughts;

import android.content.Context;
import android.widget.Toast;

public class GameBoard {
    public final static int TEAM_BLACK = 1;
    public final static int TEAM_WHITE = 2;
    private static GamePiece EMPTY_CELL;
    private String[] players;
    private GamePiece[] board;
    private boolean gameType; // true for turkish - false for armenian
    private Context context;

    public GameBoard(Context c, boolean gameType, String playerone, String playertwo) {
        this.context = c;
        this.gameType = gameType;
        this.players = new String[2];
        players[0] = playerone;
        players[1] = playertwo;
        board = new GamePiece[64];
    }

    public void setEmpty(GamePiece empty){
        this.EMPTY_CELL = empty;
        if(board[0]!=null){
            for(int i = 0; i < board.length; i++){
                if(board[i].getTeam() == 0){
                    board[i] = empty;
                }
            }
        }
    }

    public boolean gameOver(int team){
        for(GamePiece piece : board){
            if(piece.getTeam()==notTeam(team)){
                return false;
            }
        }
        return true;
    }

    public void movePiece(int pos1, int pos2) { // pos 1 is first clicked cell, pos 2 is second (one to move piece into)
        board[pos1].select();
        if (pos2 < 8 | pos2 > 54) {
            board[pos1].setKing(true);
        }
        if (pos2 < 0 | pos2 > 63) {
            Toast.makeText(context, "Piece out of bounds", Toast.LENGTH_SHORT).show();
        } else {
            board[pos2] = board[pos1];
            board[pos1] = EMPTY_CELL;
        }

    }

    public void move(int pos1, int pos2) {
        if (board[pos2] == EMPTY_CELL) {
            if(board[pos1].isKing()){
                if((pos1 - pos2)%8==0){
                    movePiece(pos1, pos2);
                }
            }
            if ((pos1 - pos2 == 8 | pos1 - pos2 == -8 | pos1 - pos2 == 1 | pos1 - pos2 == -1)
                    && board[pos1].isKing()) {
                movePiece(pos1, pos2);
            } else if ((pos1 - pos2 == 8 | pos1 - pos2 == 1 | pos1 - pos2 == -1)
                    && board[pos1].getTeam() == TEAM_WHITE) {
                movePiece(pos1, pos2);
            } else if ((pos1 - pos2 == -8 | pos1 - pos2 == 1 | pos1 - pos2 == -1)
                    && board[pos1].getTeam() == TEAM_BLACK) {
                movePiece(pos1, pos2);
            }
        }
    }

    public void takePiece(int pos1, int pos2, Context context){
        int taken = legalMove(pos1,pos2);
        if(0 <= taken && taken < 64) {
            board[taken] = EMPTY_CELL;
            depossAll();
        }
        if(taken>-1){
            movePiece(pos1, pos2);
        } else {
            board[pos1].select();
            Toast.makeText(context,"Illegal Move",Toast.LENGTH_SHORT).show();
        }
    }

    public int legalMove(int pos1, int pos2){ // if illegal returns -1, if legal returns pos of piece being taken
        switch(pos1 - pos2){
            case -16: //moving down
                if(pos1<48){
                    if ((board[pos1].isKing() | board[pos1].getTeam() == TEAM_BLACK) && board[pos2] == EMPTY_CELL
                            && checkTeam(board[pos1 + 8].getTeam(), board[pos1].getTeam())) { // take a piece down
                        board[pos2].makePossibility();
                        return pos1+8;
                    }
                }
                break;
            case -2: //moving left
                int[] right = {6, 7, 14, 15, 22, 23, 30, 31, 38, 39, 46, 47, 54, 55, 62, 63};
                if(edges(pos1, right)){
                    if (checkTeam(board[pos1].getTeam(), board[pos2+1].getTeam()) &&
                            board[pos2] == EMPTY_CELL) {             // take a piece right
                        board[pos2].makePossibility();
                        return pos1+1;
                    }
                }
                break;
            case 2: //moving right

                int[] left = {0, 1, 8, 9, 16, 17, 24, 25, 32, 33, 40, 41, 48, 47, 56, 57};
                if(edges(pos1,left)){
                    if (checkTeam(board[pos1].getTeam(), board[pos2-1].getTeam()) &&
                            board[pos2] == EMPTY_CELL) {             // take a piece left
                        board[pos2].makePossibility();
                        return pos1-1;
                    }
                }
                break;
            case 16: //moving up
                if(pos1>15) {
                    if ((board[pos1].isKing() | board[pos1].getTeam() == TEAM_WHITE) && board[pos2] == EMPTY_CELL
                            && checkTeam(board[pos1 - 8].getTeam(), board[pos1].getTeam())) { // take a piece up
                        board[pos2].makePossibility();
                        return pos1-8;
                    }
                }
                break;
        }
        return -1;
    }

    public boolean canTake(int player, int from, int over, int to){
        if(board[from]==EMPTY_CELL | from < 0 | from > 63 | to < 0 | to > 64 |
                board[to] != EMPTY_CELL | board[over] == EMPTY_CELL |
                checkTeam(board[from].getTeam(),player)){
            return false;
        }
        if(from - to == 2){
            int[] right = {6, 7, 14, 15, 22, 23, 30, 31, 38, 39, 46, 47, 54, 55, 62, 63};
            if(edges(from,right)){
                return false;
            }
        }
        if(from - to == -2){
            int[] left = {0, 1, 8, 9, 16, 17, 24, 25, 32, 33, 40, 41, 48, 47, 56, 57};
            if(edges(from,left)){
                return false;
            }
        }
        if(!checkTeam(player, board[over].getTeam())){
            return false;
        } else {
            if (!board[from].isKing()) {
                if (player == TEAM_WHITE && from - to == -16) {
                    return false;
                } else if (player == TEAM_BLACK && from - to == 16) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean canMove(int player, int from, int to){
        if(board[from]==EMPTY_CELL | from < 0 | from > 63 | to < 0 | to > 64 |
                board[to] != EMPTY_CELL | checkTeam(board[from].getTeam(),player)){
            return false;
        }
        if(from - to == 1){
            int[] right = {7, 15, 23, 31, 39, 47, 55, 63};
            if(edges(from,right)){
                return false;
            }
        }
        if(from - to == -1){
            int[] left = {0, 8, 16, 24, 32, 40, 48, 56};
            if(edges(from,left)){
                return false;
            }
        }
        if(!board[from].isKing()){
            if(player == TEAM_WHITE && from - to == -16){
                return false;
            } else if (player == TEAM_BLACK && from - to ==16){
                return false;
            }
        }
        return true;
    }

    public boolean checkTakes(int player){
        boolean ok = false;
        for(int pos = 0; pos < board.length; pos++){
            if(pos < 62){
                if(canTake(player,pos,pos+1,pos+2)){
                    board[pos+2].makePossibility();
                    ok = true;
                }
            }
            if(pos > 1) {
                if (canTake(player, pos, pos - 1, pos - 2)) {
                    board[pos - 2].makePossibility();
                    ok = true;
                }
            }
            if(pos < 47){
                if(canTake(player, pos, pos + 8, pos + 16)) {
                    board[pos+16].makePossibility();
                    ok = true;
                }
            }
            if(pos > 15){
                if(canTake(player, pos, pos - 8, pos - 16)) {
                    board[pos-16].makePossibility();
                    ok = true;
                }
            }
        }
        return ok;
    }

    public boolean checkMoves(int player) {
        boolean ok = false;
        for(int pos = 0; pos < board.length; pos++){
            if(pos < 62) {
                if (canMove(player, pos, pos + 1)) {
                    board[pos + 1].makePossibility();
                    ok = true;
                }
            }
            if(pos > 1) {
                if (canMove(player, pos, pos - 1)) {
                    board[pos - 1].makePossibility();
                    ok = true;
                }
            }
            if(pos < 47) {
                if (canMove(player, pos, pos + 8)) {
                    board[pos + 8].makePossibility();
                    ok = true;
                }
            }
            if(pos > 15) {
                if (canMove(player, pos, pos - 8)) {
                    board[pos - 8].makePossibility();
                    ok = true;
                }
            }
        }
        return ok;
    }

    private boolean checkTeam(int team1, int team2) {
        if(team1!=0){
            if (team1 != team2) { // true if different team, false if same
                return true;
            }
        }
        return false;

    }
    private int notTeam(int team) {
        if (team == TEAM_WHITE) {
            return TEAM_BLACK;
        } else if (team == TEAM_BLACK) {
            return TEAM_WHITE;
        } else return 0;
    }
    private boolean edges(int pos, int[] edges) {
        for (int edge : edges) {
            if (pos == edge) {
                return false;
            }
        }
        return true;
    }
    public void setUpBoard() {
        for (int i = 0; i < board.length; i++) {
            if (i < 8) {
                board[i] = EMPTY_CELL;
            }
            if (i > 7 && i < 24) {
                board[i] = new GamePiece(TEAM_BLACK);
            }
            if (i > 23 && i < 40) {
                board[i] = EMPTY_CELL;
            }
            if (i > 39 && i < 56) {
                board[i] = new GamePiece(TEAM_WHITE);
            }
            if (i > 55) {
                board[i] = EMPTY_CELL;
            }
        }
    }
    public void setUpBoardTwo() {
        for (int i = 0; i < board.length; i++) {
            board[i] = EMPTY_CELL;
        }
        board[23] = new GamePiece(TEAM_BLACK);
        board[31] = new GamePiece(TEAM_WHITE);
    }
    public GamePiece[] getBoard() {
        return board;
    }
    public void depossAll(){
        for(int i = 0; i < board.length; i++){
            board[i].notPossibility();
        }
    }
    public void deselectAll(){
        for(int i = 0; i < board.length; i++){
            board[i].deselect();
        }
    }
    public String[] getPlayers(){return players;}
    public void setBoard(GamePiece[] board) {
        this.board = board;
    }
    public boolean getGametype(){ return gameType; }

}