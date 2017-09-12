package wburles.uk.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 07/03/2016.
 */
public class BirdDAO {
    private BirdDBHelper dbHelper;
    private SQLiteDatabase db;

        public BirdDAO(Context context){
            dbHelper = new BirdDBHelper(context);
        }
        public void open(){
            db = dbHelper.getWritableDatabase();
        }
        public void addBird(String binomialName, String commonName){
            Log.d("BIRDS","add bird called");
            ContentValues values = new ContentValues();
            values.put(BirdContract.COLUMN_NAME_BONOMIAL_NAME, binomialName);
            values.put(BirdContract.COLUMN_NAME_COMMON_NAME, commonName);

            long newRowID = db.insert(BirdContract.TABLE_NAME_BIRD,null,values);
        }
        public ArrayList<Bird> getBirds(ArrayList<Bird> birds){
            Cursor cursor = db.query(
                    BirdContract.TABLE_NAME_BIRD,
                    null,null,null,null,null,null);
            String col1 = cursor.getColumnName(0);
            String col2 = cursor.getColumnName(1);
            Log.d("BIRDS","Columns: "+col1 + " "+col2);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                String binomialName = cursor.getString(
                        BirdContract.COLUMN_INDEX_BINOMIAL_NAME);
                String commonName = cursor.getString(
                        BirdContract.COLUMN_INDEX_COMMON_NAME);
                birds.add(new Bird(binomialName, commonName));
                cursor.moveToNext();
            }
            return birds;

        }
}
