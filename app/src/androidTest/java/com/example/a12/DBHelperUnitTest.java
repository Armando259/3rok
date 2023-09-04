package com.example.a12;

import org.junit.Before;
import org.junit.Test;
import androidx.test.core.app.ApplicationProvider;
import static org.junit.Assert.*;

public class DBHelperUnitTest {

    private DBHelper dbHelper;

    @Before
    public void setUp() {
        dbHelper = new DBHelper(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void testCheckUsername_UserExists() {
        // Unos korisnika
        String username = "testuser";
        String password = "testpassword";
        dbHelper.insertData(username, password);

        // Provjera postojanja korisnika
        boolean userExists = dbHelper.checkusername(username);

        // Ako postoji korisnik
        assertTrue(userExists);
    }

    @Test
    public void testCheckUsername_UserDoesNotExist() {
        // Unos korisnika
        String nonExistentUsername = "nonexistentuser";

        // Provjera postojanja korisnika
        boolean userExists = dbHelper.checkusername(nonExistentUsername);

        // Ne postoji korisnik
        assertFalse(userExists);
    }
}

