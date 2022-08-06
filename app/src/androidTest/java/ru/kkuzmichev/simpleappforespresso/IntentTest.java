package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;

@RunWith(AllureAndroidJUnit4.class)

public class IntentTest {
    private ViewInteraction moreOptions = onView(
            allOf(withContentDescription("More options"),
                    isDisplayed()));
    private ViewInteraction setting = onView(
            allOf(withId(R.id.title)));

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Story("Проверка интента через использование правила IntentsTestRule ")
    @Test
    public void shouldCheckIntent(){
        moreOptions.perform(click());
        setting.perform(click());
        intended(
                hasData("https://google.com")
        );
    }

}
