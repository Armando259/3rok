package com.example.a12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;

public class pocetni extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni);

        RelativeLayout mainLayout = findViewById(R.id.pocetniLayout);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pokreni drugu aktivnost ovdje
                Intent intent = new Intent(pocetni.this, login.class);
                startActivity(intent);
            }
        });
        String fileName = "user_data.txt"; // Ime datoteke
        String content = ""; // Pocetni sadrzaj datoteke

        // Provjera da li datoteka vec postoji, ako ne, stvori je
        if (!fileExists(fileName)) {
            createTextFile(this, fileName, content);
        }
    }

    // Funkcija za provjeru postojanja datoteke
    private boolean fileExists(String fileName) {
        File file = new File(getFilesDir(), fileName);
        return file.exists();
    }

    // Funkcija za stvaranje tekstualne datoteke
    private void createTextFile(Context context, String fileName, String content) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

