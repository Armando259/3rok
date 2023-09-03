package com.example.a12;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LikesDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "likes.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_LIKES = "likes_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_LIKED = "liked";
    public static final String COLUMN_DISLIKED = "disliked";
    public static final String COLUMN_USER_LIKED = "user_liked";
    public static final String COLUMN_USER_DISLIKED = "user_disliked";


    private static final String SQL_CREATE_LIKES_TABLE =
            "CREATE TABLE " + TABLE_LIKES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_LIKED + " INTEGER," +
                    COLUMN_DISLIKED + " INTEGER," +
                    COLUMN_USER_LIKED + " INTEGER," +
                    COLUMN_USER_DISLIKED + " INTEGER)";
    public LikesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_LIKES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIKES);
        onCreate(db);
    }
}
