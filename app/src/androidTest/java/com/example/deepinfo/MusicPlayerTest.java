package com.example.deepinfo;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

public class MusicPlayerTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void launchDeepInfo(){
       onView(withId(R.id.user_name)).perform(typeText("Sad11"));
       onView(withId(R.id.password_name)).perform(typeText("12345678"));
       onView(withId(R.id.login_button)).perform(click());

       onView(withId(R.id.login_success_screen)).check(matches(isDisplayed()));
    }

    @Test
    public void createNewAccountAndlogin() throws InterruptedException {

        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withId(R.id.input_user_id)).perform(typeText("Goy217"));

        onView(withId(R.id.input_user_password)).perform(typeText("12345678"));
        onView(withId(R.id.confirm_password)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.confirm_password)).perform(typeText("12345678"));
        onView(withId(R.id.security_questions)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.security_questions)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.security_answer)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.security_answer)).perform(typeText("Noodles"));
        onView(withId(R.id.signup_complete)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_complete)).perform(click());

    }
}