package com.example.a12;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.a12.MainActivity;
import com.example.a12.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginE2ETest {

    @Rule
    public ActivityTestRule<login> activityRule =
            new ActivityTestRule<>(login.class);

    @Test
    public void loginTest() {
        // Klikni na polje za unos korisničkog imena i unesite korisničko ime
        Espresso.onView(ViewMatchers.withId(R.id.usernameEditText))
                .perform(ViewActions.click())
                .perform(ViewActions.typeText("luka"));

        // Klikni na polje za unos lozinke i unesite lozinku
        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
                .perform(ViewActions.click())
                .perform(ViewActions.typeText("12345"));

        // Klikni na gumb za prijavu
        Espresso.onView(ViewMatchers.withId(R.id.loginButton))
                .perform(ViewActions.click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Provjera je li korisnik uspješno prijavljen
        Espresso.onView(ViewMatchers.withId(R.id.logoutButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
