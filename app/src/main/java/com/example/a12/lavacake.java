package com.example.a12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class lavacake extends AppCompatActivity {

    private LikesDatabaseHelper dbHelper;
    private TextView likeCountTextView;
    private TextView dislikeCountTextView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavacake);

        dbHelper = new LikesDatabaseHelper(this);
        likeCountTextView = findViewById(R.id.likeCountTextView4); // Promijenite ID-a na odgovarajući u vašem XML-u
        dislikeCountTextView = findViewById(R.id.dislikeCountTextView4); // Promijenite ID-a na odgovarajući u vašem XML-u

        // Čitanje korisničkog imena iz datoteke
        username = readFromFile("user_data.txt");
        checkAndCreateFile();

        trbrojlike();

        ImageView myImageView = findViewById(R.id.homeIcon4); // Promijenite ID-a na odgovarajući u vašem XML-u

        // Postavite OnClickListener za ImageView
        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSongFragment();
            }
        });

        ImageView myImageView2 = findViewById(R.id.notificationsIcon4); // Promijenite ID-a na odgovarajući u vašem XML-u

        myImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbumiFragment();
            }
        });
    }

    private void checkAndCreateFile() {
        String fileName = "lavacakeLiked.txt"; // Promijenite naziv datoteke za "lavacake"

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
        String userLike = username + "_liked_lavacake"; // Promijenite naziv za "lavacake"
        return userLike;
    }

    private void updateCounts() {
        SharedPreferences sharedPreferences = getSharedPreferences("LikeDislikePrefsLavacake", MODE_PRIVATE);

        int currentLikeCount = sharedPreferences.getInt("like_count_lavacake", 0); // Učitaj trenutni broj lajkova
        int newLikeCount = currentLikeCount + 1; // Dodaj 1 na trenutni broj lajkova
        likeCountTextView.setText(String.valueOf(newLikeCount)); // Postavi trenutni broj lajkova u TextView

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("like_count_lavacake", newLikeCount); // Spremi novi broj lajkova
        editor.apply();
        trbrojlike();
    }

    private void openSongFragment() {
        Intent intent = new Intent(lavacake.this, MainActivity.class);
        String poruka = "song";
        intent.putExtra("dodatna", poruka);
        startActivity(intent);
    }

    private void openAlbumiFragment() {
        Intent intent = new Intent(lavacake.this, MainActivity.class);
        String poruka = "album";
        intent.putExtra("dodatna", poruka);
        startActivity(intent);
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
        String contentFromFile = readFromFile("lavacakeLiked.txt"); // Promijenite naziv datoteke za "lavacake"
        return contentFromFile.contains(likeId);
    }

    private void markUserLiked(String likeId) {
        writeToFile("lavacakeLiked.txt", likeId + "\n"); // Promijenite naziv datoteke za "lavacake"
    }

    private void writeToFile(String fileName, String content) {
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
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
        return content.toString().trim();
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
        SharedPreferences sharedPreferences = getSharedPreferences("DislikePrefsLavacake", MODE_PRIVATE);

        int currentLikeCount = sharedPreferences.getInt("dislike_count_lavacake", 0); // Učitaj trenutni broj dislajkova
        int newLikeCount = currentLikeCount + 1; // Dodaj 1 na trenutni broj dislajkova
        dislikeCountTextView.setText(String.valueOf(newLikeCount)); // Postavi trenutni broj dislajkova u TextView

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("dislike_count_lavacake", newLikeCount); // Spremi novi broj dislajkova
        editor.apply();
        trbrojlike();
    }

    private void trbrojlike() {
        SharedPreferences sharedPreferences = getSharedPreferences("LikeDislikePrefsLavacake", MODE_PRIVATE);
        int currentLikeCount = sharedPreferences.getInt("like_count_lavacake", 0);
        likeCountTextView.setText(String.valueOf(currentLikeCount)); // Postavi trenutni broj lajkova u TextView

        SharedPreferences dislikeSharedPreferences = getSharedPreferences("DislikePrefsLavacake", MODE_PRIVATE);
        int currentDislikeCount = dislikeSharedPreferences.getInt("dislike_count_lavacake", 0); // Učitaj trenutni broj dislajkova
        dislikeCountTextView.setText(String.valueOf(currentDislikeCount)); // Postavi trenutni broj dislajkova u TextView
    }

    public String generateDislikeId(String username) {
        String userDislike = username + "_disliked_lavacake"; // Promijenite naziv za "lavacake"
        return userDislike;
    }

    private boolean hasUserDisliked(String dislikeId) {
        String contentFromFile = readFromFile("lavacakeDisliked.txt"); // Promijenite naziv datoteke za "lavacake"
        return contentFromFile.contains(dislikeId);
    }

    private void markUserDisliked(String dislikeId) {
        writeToFile("lavacakeDisliked.txt", dislikeId + "\n"); // Promijenite naziv datoteke za "lavacake"
    }
}
