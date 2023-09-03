package com.example.a12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public String getUserId(String inputUsername) {
        SQLiteDatabase db = this.getReadableDatabase();
        String username = null;

        Cursor cursor = db.query(
                "users",
                new String[] { "username" },
                "username = ?",
                new String[] { inputUsername },
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexUsername = cursor.getColumnIndex("username"); // Dohvati indeks stupca "username"
            username = cursor.getString(columnIndexUsername); // Dohvati "username" za trenutni redak

            cursor.close(); // Zatvori Cursor nakon dohvaćanja podataka
        } else {
            String errorMessage = "No user found with username: " + inputUsername;
            Log.d("DBHelper", errorMessage);
        }

        db.close();

        return username; // Vrati korisničko ime umjesto userId
    }
    public Boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Dohvati korisnika temeljem korisničkog imena i lozinke
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});

        if (cursor.getCount() > 0) {
            // User exists, move cursor to first row
            cursor.moveToFirst();

            // Extract the data you need from the cursor
            String foundUsername = cursor.getString(cursor.getColumnIndex("username"));
            String foundPassword = cursor.getString(cursor.getColumnIndex("password"));

            // Close the cursor
            cursor.close();

            // If the retrieved username and password match the input, insert the row at the beginning
            if (foundUsername.equals(username) && foundPassword.equals(password)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("username", foundUsername);
                contentValues.put("password", foundPassword);

                // Delete the existing row
                db.delete("users", "username = ?", new String[]{foundUsername});

                // Insert the row at the beginning
                db.insert("users", null, contentValues);

                return true;
            }
        }

        return false;
    }


    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
