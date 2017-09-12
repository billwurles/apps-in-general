package wburles.uk.draughts.MySQL;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class GameProvider extends ContentProvider {
    public static final String AUTHORITY = "wburles.uk.draughts";
    public static final Uri GAME_URI = Uri.parse("content://" + AUTHORITY + "/game");
    public static final Uri USERS_URI = Uri.parse("content://" + AUTHORITY + "/users");
    public static final Uri SETTINGS_URI = Uri.parse("content://" + AUTHORITY + "/settings");
    public static final String GAME_PATH = GameContract.TABLE_NAME_GAME;
    public static final String USER_PATH = GameContract.TABLE_NAME_USERS;
    public static final String SETTINGS_PATH = GameContract.TABLE_NAME_SETTINGS;
    private static final int GAME = 0;
    private static final int USER = 2;
    private static final int SETTINGS = 4;
    private static final int SETTINGS_ID = 5;

    private DBHelper helper;
    private UriMatcher urImatcher;

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        helper = new DBHelper(getContext());
        urImatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urImatcher.addURI(AUTHORITY,GAME_PATH,GAME);
        urImatcher.addURI(AUTHORITY,USER_PATH,USER);
        urImatcher.addURI(AUTHORITY,SETTINGS_PATH,SETTINGS);
        urImatcher.addURI(AUTHORITY,SETTINGS_PATH + "/#",SETTINGS_ID);
        return true;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsDeleted = 0;
        int uriType = urImatcher.match(uri);
        String newSelection;
        switch (uriType) {
            case GAME:
                rowsDeleted = db.delete(GameContract.TABLE_NAME_GAME,
                        selection, selectionArgs);
                break;
            case USER:
                rowsDeleted = db.delete(GameContract.TABLE_NAME_USERS,
                        selection, selectionArgs);
                break;
            case SETTINGS:
                rowsDeleted = db.delete(GameContract.TABLE_NAME_SETTINGS,
                        selection, selectionArgs);
                break;
            case SETTINGS_ID:
                newSelection = appendToSelection(uri, selection);
                rowsDeleted = db.delete(GameContract.TABLE_NAME_SETTINGS,
                        newSelection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unrecognised uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int uriType = urImatcher.match(uri);
        Uri resultUri = null;
        long rowId;
        switch (uriType){
            case GAME:
                rowId = db.insert(GameContract.TABLE_NAME_GAME, null, values);
                resultUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(resultUri, null);
                break;
            case USER:
                rowId = db.insert(GameContract.TABLE_NAME_USERS, null, values);
                resultUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(resultUri, null);
                break;
            case SETTINGS:
                rowId = db.insert(GameContract.TABLE_NAME_SETTINGS, null, values);
                resultUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(resultUri, null);
                break;
            case SETTINGS_ID:
                rowId = db.insert(GameContract.TABLE_NAME_SETTINGS, null, values);
                resultUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(resultUri, null);
                break;
            default: throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return resultUri;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        int uriType = urImatcher.match(uri);
        switch (uriType) {
            case GAME:
                builder.setTables(GameContract.TABLE_NAME_GAME);
                break;
            case USER:
                builder.setTables(GameContract.TABLE_NAME_USERS);
                break;
            case SETTINGS:
                builder.setTables(GameContract.TABLE_NAME_SETTINGS);
                break;
            case SETTINGS_ID:
                builder.setTables(GameContract.TABLE_NAME_SETTINGS);
                builder.appendWhere(GameContract._ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unrecognised URI");
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = builder.query(db, projection, selection, selectionArgs,null,null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsUpdated = 0;
        int uriType = urImatcher.match(uri);
        String newSelection;
        switch (uriType) {
            case GAME:
                rowsUpdated = db.update(GameContract.TABLE_NAME_GAME, values, selection, selectionArgs);
                break;
            case USER:
                rowsUpdated = db.update(GameContract.TABLE_NAME_USERS, values, selection, selectionArgs);
                break;
            case SETTINGS:
                rowsUpdated = db.update(GameContract.TABLE_NAME_SETTINGS, values, selection, selectionArgs);
                break;
            case SETTINGS_ID:
                newSelection = appendToSelection(uri, selection);
                rowsUpdated = db.update(GameContract.TABLE_NAME_SETTINGS, values, newSelection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unrecognised uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }

    private String appendToSelection(Uri uri, String selection){
        String id = uri.getLastPathSegment();
        StringBuilder newSelection = new StringBuilder(GameContract._ID + "=" + id);
        if(selection != null && !selection.isEmpty()){
            newSelection.append(" AND "+selection);
        }
        return newSelection.toString();
    }
}
