package wburles.uk.draughts.MySQL;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String COMMA_SEP = ", ";
    public static final int DATABASE_VERSION = 41;
    public static final String DATABASE_NAME = "database.db";
    public static final String CREATE_GAME_ENTRY =
            "CREATE TABLE "+ GameContract.TABLE_NAME_GAME + " (" +
                    GameContract._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    GameContract.COL_NAME_POS + " INTEGER NOT NULL"+ COMMA_SEP +
                    GameContract.COL_NAME_TEAM + " INTEGER NOT NULL"+ COMMA_SEP +
                    GameContract.COL_NAME_KING + " INTEGER NOT NULL)";
    public static final String CREATE_USERS_ENTRY =
            "CREATE TABLE "+ GameContract.TABLE_NAME_USERS + " (" +
                    GameContract._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    GameContract.COL_NAME_USERNAME + " TEXT NOT NULL" + COMMA_SEP +
                    GameContract.COL_NAME_USERDESC + " TEXT NOT NULL" + COMMA_SEP +
                    GameContract.COL_NAME_USERPOINTS + " INTEGER NOT NULL)";
    public static final String CREATE_SETTINGS_ENTRY =
            "CREATE TABLE "+ GameContract.TABLE_NAME_SETTINGS + " (" +
                    GameContract._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    GameContract.COL_NAME_HASGAME + " INTEGER DEFAULT 0" + COMMA_SEP +
                    GameContract.COL_NAME_GAMETYPE + " INTEGER" + COMMA_SEP +
                    GameContract.COL_NAME_BACKGROUND + " INTEGER" + COMMA_SEP +
                    GameContract.COL_NAME_PLAYERONE + " TEXT" + COMMA_SEP +
                    GameContract.COL_NAME_PLAYERTWO + " TEXT)";

    private static final String DELETE_GAME = "DROP TABLE IF EXISTS " + GameContract.TABLE_NAME_GAME;
    private static final String DELETE_USERS = "DROP TABLE IF EXISTS " + GameContract.TABLE_NAME_USERS;
    private static final String DELETE_SETTINGS = "DROP TABLE IF EXISTS " + GameContract.TABLE_NAME_SETTINGS;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("Draughts", "This is seriously unacceptable " + CREATE_GAME_ENTRY);
        db.execSQL(CREATE_GAME_ENTRY);
        db.execSQL(CREATE_USERS_ENTRY);
        db.execSQL(CREATE_SETTINGS_ENTRY);

        ContentValues defaultValues = new ContentValues();
        defaultValues.put(GameContract.COL_NAME_HASGAME, 0);
        defaultValues.put(GameContract.COL_NAME_BACKGROUND, 1);
        defaultValues.put(GameContract.COL_NAME_GAMETYPE, 0);
        db.insert(GameContract.TABLE_NAME_SETTINGS, null, defaultValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DELETE_GAME);
        db.execSQL(DELETE_SETTINGS);
        db.execSQL(DELETE_USERS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
