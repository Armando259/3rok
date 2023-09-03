package com.example.a12;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class biftek extends AppCompatActivity {

    private LikesDatabaseHelper dbHelper;
    private TextView likeCountTextView;
    private TextView dislikeCountTextView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biftek);

        dbHelper = new LikesDatabaseHelper(this);
        likeCountTextView = findViewById(R.id.likeCountTextView);
        dislikeCountTextView = findViewById(R.id.dislikeCountTextView);

        // Čitanje korisničkog imena iz datoteke
        username = readFromFile("user_data.txt");
        checkAndCreateFile();

        SharedPreferences sharedPreferences = getSharedPreferences("MyIntDataPrefs", MODE_PRIVATE);
        int currentLikeCount = sharedPreferences.getInt("like_count", 0);
        likeCountTextView.setText(String.valueOf(currentLikeCount)); // Postavi trenutni broj lajkova u TextView

        SharedPreferences dislikeSharedPreferences = getSharedPreferences("Dislike", MODE_PRIVATE);
        int currentDislikeCount = dislikeSharedPreferences.getInt("dislike_count", 0);
        dislikeCountTextView.setText(String.valueOf(currentDislikeCount)); // Postavi trenutni broj dislajkova u TextView

        ImageView myImageView = findViewById(R.id.homeIcon);

        // Postavite OnClickListener za ImageView
        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSongFragment();
            }
        });

        ImageView myImageView2 = findViewById(R.id.notificationsIcon);

        myImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumiFragment();
            }
        });

    }
    private void checkAndCreateFile() {
        String fileName = "biftekLiked.txt";

        File file = new File(getFilesDir(), fileName);

        if (!file.exists()) {
            try {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public String generateLikeId(String username) {
        String userLike = username + "_liked";
        return userLike;
    }

    private void updateCounts() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyIntDataPrefs", MODE_PRIVATE);

        int currentLikeCount = sharedPreferences.getInt("like_count", 0); // Učitaj trenutni broj lajkova
        int newLikeCount = currentLikeCount + 1; // Dodaj 1 na trenutni broj lajkova
        likeCountTextView.setText(String.valueOf(currentLikeCount)); // Postavi trenutni broj lajkova u TextView

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("like_count", newLikeCount); // Spremi novi broj lajkova
        editor.apply();
    }




    private void openSongFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.songss, new song()) // Zamijenite "SongFragment" s vašim fragmentom
                .commit();
    }

    // Otvorite AlbumiFragment (ili drugi fragment)
    private void openAlbumiFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.albummi, new album()) // Zamijenite "AlbumiFragment" s vašim fragmentom
                .commit();
    }





    public void onLikeClick(View view) {
        String likeId = generateLikeId(username);

        if (!hasUserLiked(likeId)) {
            markUserLiked(likeId);
            updateCounts();
        } else {
            Toast.makeText(this, "You have already liked this.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasUserLiked(String likeId) {
        String contentFromFile = readFromFile("biftekLiked.txt");
        return contentFromFile.contains(likeId);
    }

    private void markUserLiked(String likeId) {
        writeToFile("biftekLiked.txt", likeId + "\n", Context.MODE_APPEND); // Promijenite u Context.MODE_APPEND
    }


    private void writeToFile(String fileName, String content, int mode) {
        try {
            FileOutputStream fos = openFileOutput(fileName, mode | Context.MODE_APPEND); // Dodajte MODE_APPEND flag
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString().trim(); // Uklanja prazan redak na kraju
    }
    public void onBackClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onLogoutClick(View view) {

        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }


    public void onDislikeClick(View view) {
        String dislikeId = generateDislikeId(username);

        if (!hasUserDisliked(dislikeId)) {
            markUserDisliked(dislikeId);
            updateCounts1();
        } else {
            Toast.makeText(this, "You have already disliked this.", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateCounts1() {
        SharedPreferences sharedPreferences = getSharedPreferences("Dislike", MODE_PRIVATE);

        int currentLikeCount = sharedPreferences.getInt("dislike_count", 0); // Učitaj trenutni broj lajkova
        int newLikeCount = currentLikeCount + 1; // Dodaj 1 na trenutni broj lajkova
        dislikeCountTextView.setText(String.valueOf(currentLikeCount)); // Postavi trenutni broj lajkova u TextView

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("dislike_count", newLikeCount); // Spremi novi broj lajkova
        editor.apply();
    }


    public String generateDislikeId(String username) {
        String userDislike = username + "_disliked";
        return userDislike;
    }

    private boolean hasUserDisliked(String dislikeId) {
        String contentFromFile = readFromFile("biftekDisliked.txt");
        return contentFromFile.contains(dislikeId);
    }

    private void markUserDisliked(String dislikeId) {
        writeToFile("biftekDisliked.txt", dislikeId + "\n", Context.MODE_APPEND);
    }




}
