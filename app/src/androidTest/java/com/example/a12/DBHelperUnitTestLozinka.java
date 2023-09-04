package com.example.a12;

import org.junit.Before;
import org.junit.Test;
import androidx.test.core.app.ApplicationProvider;
import static org.junit.Assert.*;

public class DBHelperUnitTestLozinka {

    private DBHelper dbHelper;

    @Before
    public void setUp() {
        dbHelper = new DBHelper(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void testCheckUsernamePassword_CorrectCredentials() {
        // Unos korisnika
        String username = "testuser";
        String password = "testpassword";
        dbHelper.insertData(username, password);

        // Provjera korisničkog imena i lozinke
        boolean correctCredentials = dbHelper.checkusernamepassword(username, password);

        // True ako je korisničko ime i lozinka tocna
        assertTrue(correctCredentials);
    }

    @Test
    public void testCheckUsernamePassword_IncorrectCredentials() {
        // Unos korisnika
        String username = "testuser";
        String password = "testpassword";
        dbHelper.insertData(username, password);

        // Provjera s pogrešnim podacima
        boolean incorrectCredentials = dbHelper.checkusernamepassword(username, "wrongpassword");

        // False jer ne odgovaraju podaci
        assertFalse(incorrectCredentials);
    }
}

