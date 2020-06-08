//package com.example.deepinfo;
//
//import androidx.test.rule.ActivityTestRule;
//
//import junit.framework.TestCase;
//
//import org.junit.Rule;
//import org.junit.Test;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
////import android.support.test.runner.screenshot
//public class LoginSucessScreenTest extends TestCase {
//
//    @Rule
//    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
//
//    @Test
//    public void launchDeepInfo(){
//       onView(withId(R.id.user_name)).perform(typeText("Sad11"));
//       onView(withId(R.id.password_name)).perform(typeText("12345678"));
//       onView(withId(R.id.login_button)).perform(click());
//
//       onView(withId(R.id.login_success_screen)).check(matches(isDisplayed()));
//    }
//}