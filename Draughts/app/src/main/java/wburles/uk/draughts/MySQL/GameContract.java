package wburles.uk.draughts.MySQL;

import android.provider.BaseColumns;

public class GameContract implements BaseColumns {
    public static final String TABLE_NAME_GAME = "game";
    public static final String TABLE_NAME_USERS = "users";
    public static final String TABLE_NAME_SETTINGS = "settings";

    public static final String COL_NAME_POS = "pos";
    public static final String COL_NAME_TEAM = "team";
    public static final String COL_NAME_KING = "king";

    public static final String COL_NAME_HASGAME = "hasgame";
    public static final String COL_NAME_GAMETYPE = "gametype";
    public static final String COL_NAME_BACKGROUND = "background";
    public static final String COL_NAME_PLAYERONE = "playerone";
    public static final String COL_NAME_PLAYERTWO = "playertwo";

    public static final String COL_NAME_USERNAME = "username";
    public static final String COL_NAME_USERDESC = "desc";
    public static final String COL_NAME_USERPOINTS = "points";

    public static final int COL_INDEX_ID = 0;
    public static final int COL_INDEX_POS = 1;
    public static final int COL_INDEX_TEAM = 2;
    public static final int COL_INDEX_KING = 3;

    public static final int COL_INDEX_HASGAME = 1;
    public static final int COL_INDEX_GAMETYPE = 2;
    public static final int COL_INDEX_BACKGROUND = 3;
    public static final int COL_INDEX_PLAYERONE = 4;
    public static final int COL_INDEX_PLAYERTWO = 5;

    public static final int COL_INDEX_USERNAME = 1;
    public static final int COL_INDEX_USERDESC = 2;
    public static final int COL_INDEX_USERPOINTS = 3;
}
