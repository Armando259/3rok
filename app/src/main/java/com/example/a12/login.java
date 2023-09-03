package com.example.a12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class login extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);
        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = DB.checkusernamepassword(user, pass);
                    if (checkUserPass) {
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        String userName = user; // Promenite ovo sa stvarnim korisničkim imenom
                        String fileName = "user_data.txt";

                        String content = userName + "\n"; // Novi sadržaj koji će zamijeniti stari

                        writeContentToFile(fileName, content); // Upisivanje novog sadržaja u datoteku

                    } else {
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView regText = findViewById(R.id.registerTextView);
        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    private void writeContentToFile(String fileName, String content) {
        try {
            FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
