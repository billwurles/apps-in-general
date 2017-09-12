package wburles.uk.draughts.MySQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import wburles.uk.draughts.*;

public class GameDAO {
    public ArrayList<User> getUsers(Context context){
        ArrayList<User> users = new ArrayList<User>();
        Cursor cursor = context.getContentResolver().query(GameProvider.USERS_URI, null, null,
                                                null, GameContract.COL_NAME_USERPOINTS + " DESC");
        Log.d("Draughts", "Columns: "+ cursor.getColumnName(GameContract.COL_INDEX_USERPOINTS) + " " +
                                         cursor.getColumnName(GameContract.COL_INDEX_USERNAME) + " " +
                                         cursor.getColumnName(GameContract.COL_INDEX_USERDESC));
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(GameContract.COL_INDEX_USERNAME);
            String desc = cursor.getString(GameContract.COL_INDEX_USERDESC);
            int points = cursor.getInt(GameContract.COL_INDEX_USERPOINTS);
            users.add(new User(name,desc,points));
            cursor.moveToNext();
        }
        return users;
    }

    public void addPoint(Context context, String user){
        Cursor cursor = context.getContentResolver().query(GameProvider.USERS_URI, null, null, null, null);
        cursor.moveToFirst();
        boolean ok = false;
        int pos = -1;
        int points=-1;
        while(!cursor.isAfterLast() && !ok){
            String name = cursor.getString(GameContract.COL_INDEX_USERNAME);
            if(name.equals(user)){
                pos = cursor.getInt(GameContract.COL_INDEX_ID);
                points = cursor.getInt(GameContract.COL_INDEX_USERPOINTS);
                ok = true;
            }
            cursor.moveToNext();
        }
        if(ok && points > -1 && pos > -1){
            ContentValues values = new ContentValues();
            points++;
            values.put(GameContract.COL_NAME_USERPOINTS,points);
            context.getContentResolver().update(GameProvider.USERS_URI, values, GameContract._ID + " = "+pos, null);
        }
    }

    public void addUser(Context context, User newUser){
        ContentValues values = new ContentValues();
        values.put(GameContract.COL_NAME_USERNAME, newUser.getUser());
        values.put(GameContract.COL_NAME_USERDESC, newUser.getDesc());
        values.put(GameContract.COL_NAME_USERPOINTS,newUser.getPoints());
        context.getContentResolver().insert(GameProvider.USERS_URI, values);
    }

    public boolean gameExists(Context context){
        Cursor cursor = context.getContentResolver().query(GameProvider.SETTINGS_URI,
                                                            null, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(GameContract.COL_INDEX_HASGAME) != 0;
    }

    public void setExists(Context context){
        ContentValues values = new ContentValues();
        values.put(GameContract.COL_NAME_HASGAME, 1);

        if(context.getContentResolver().query(GameProvider.SETTINGS_URI,null,null,null,null).getCount()==0){
            context.getContentResolver().insert(GameProvider.SETTINGS_URI,values);
        } else {
            context.getContentResolver().update(GameProvider.SETTINGS_URI, values, null, null);
        }
    }

    public void addBoard(Context context, GameBoard board){
        Log.d("Draughts", "add board to db called");
        int count = 0;
        for(int i=0; i< 64; i++){
            context.getContentResolver().delete(GameProvider.SETTINGS_URI,null,null);
        }

        for(GamePiece piece : board.getBoard()){
            ContentValues values = new ContentValues();
            values.put(GameContract.COL_NAME_POS, count);
            values.put(GameContract.COL_NAME_TEAM, piece.getTeam());
            values.put(GameContract.COL_NAME_KING, piece.isKing() ? 1 : 0);
            context.getContentResolver().insert(GameProvider.GAME_URI, values);
            count++;
        }
        ContentValues values = new ContentValues();
        values.put(GameContract.COL_NAME_PLAYERONE, board.getPlayers()[0]);
        values.put(GameContract.COL_NAME_PLAYERTWO, board.getPlayers()[1]);

        if(context.getContentResolver().query(GameProvider.SETTINGS_URI,null,null,null,null).getCount()==0){
            context.getContentResolver().insert(GameProvider.SETTINGS_URI, values);
        } else {
            context.getContentResolver().update(GameProvider.SETTINGS_URI, values, null, null);
        }
    }
    
    public GameBoard getGame(Context context){
        GamePiece[] board = new GamePiece[64];
        Cursor cursor = context.getContentResolver().query(GameProvider.GAME_URI, null, null, null, null);
        Log.d("Draughts", "Columns: " + cursor.getColumnName(0) + " " + cursor.getColumnName(1) + " " +
                cursor.getColumnName(2) + " " + cursor.getColumnName(3));
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            int pos = cursor.getInt(GameContract.COL_INDEX_POS);
            int team = cursor.getInt(GameContract.COL_INDEX_TEAM);
            boolean king = cursor.getInt(GameContract.COL_INDEX_KING) != 0;
            board[pos] = new GamePiece(team);
            board[pos].setKing(king);
            cursor.moveToNext();
        }
        cursor = context.getContentResolver().query(GameProvider.SETTINGS_URI, null, null, null, null);
        cursor.moveToFirst();
        boolean gametype = cursor.getInt(GameContract.COL_INDEX_GAMETYPE) != 0;
        GameBoard game = new GameBoard(context,gametype,
                cursor.getString(GameContract.COL_INDEX_PLAYERONE),
                cursor.getString(GameContract.COL_INDEX_PLAYERTWO));
        game.setBoard(board);
        return game;
    }

    public void setSettings(Context context, boolean[] settings){
        ContentValues values = new ContentValues();
        values.put(GameContract.COL_NAME_GAMETYPE, settings[0] ? 1:0);
        values.put(GameContract.COL_NAME_BACKGROUND, settings[1] ? 1 : 0);
        if(context.getContentResolver().query(GameProvider.SETTINGS_URI,null,null,null,null).getCount()==0){
            context.getContentResolver().insert(GameProvider.SETTINGS_URI, values);
        } else {
        context.getContentResolver().update(GameProvider.SETTINGS_URI, values, null, null);
        }
    }

    public boolean[] getSettings(Context context) {
        Cursor cursor = context.getContentResolver().query(GameProvider.SETTINGS_URI, null, null, null, null);
        boolean[] settings = new boolean[2];
        cursor.moveToFirst();
        settings[0] = cursor.getInt(GameContract.COL_INDEX_GAMETYPE) != 0;
        settings[1] = cursor.getInt(GameContract.COL_INDEX_BACKGROUND) != 0;
        return settings;
    }

}
