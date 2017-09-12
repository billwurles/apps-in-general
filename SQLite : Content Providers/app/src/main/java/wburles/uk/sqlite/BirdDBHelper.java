package wburles.uk.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Will on 07/03/2016.
 */
public class BirdDBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "birds.db";
    public static final String CREATE_ENTRIES =
            "CREATE TABLE "+ BirdContract.TABLE_NAME_BIRD + " (" +
                    BirdContract._ID + " INTEGER" + " PRIMARY KEY "+ COMMA_SEP +
                    BirdContract.COLUMN_NAME_BONOMIAL_NAME + " "+ TEXT_TYPE +
                    " UNIQUE " + COMMA_SEP +
                    BirdContract.COLUMN_NAME_COMMON_NAME +" "+ TEXT_TYPE + ")";
    private static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BirdContract.TABLE_NAME_BIRD;

    public BirdDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("BIRDS", CREATE_ENTRIES);
        db.execSQL(CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
