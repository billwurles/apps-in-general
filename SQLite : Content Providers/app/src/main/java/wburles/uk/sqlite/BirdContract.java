package wburles.uk.sqlite;

import android.provider.BaseColumns;

/**
 * Created by Will on 07/03/2016.
 */
public class BirdContract implements BaseColumns {
    public static final String TABLE_NAME_BIRD = "bird";
    public static final String COLUMN_NAME_BONOMIAL_NAME = "binomial_name";
    public static final String COLUMN_NAME_COMMON_NAME = "common_name";
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_BINOMIAL_NAME = 1;
    public static final int COLUMN_INDEX_COMMON_NAME = 2;
}
