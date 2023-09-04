package com.example.a12;

import org.junit.Before;
import org.junit.Test;
import androidx.test.core.app.ApplicationProvider;
import static org.junit.Assert.*;

public class DBHelperBP {

    private DBHelper dbHelper;

    @Before
    public void setUp() {
        dbHelper = new DBHelper(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void testInsertData_UserAddedSuccessfully() {
        // Unos korisnika
        String username = "novi_korisnik";
        String password = "lozinka";

        // Pozivanje metode za unos korisnika
        boolean isInserted = dbHelper.insertData(username, password);

        // Provjera je li korisnik uspješno dodan
        assertTrue(isInserted);

        // Provjera postojanja korisnika u bazi podataka
        boolean userExists = dbHelper.checkusername(username);

        // True ako postoji
        assertTrue(userExists);
    }
}
