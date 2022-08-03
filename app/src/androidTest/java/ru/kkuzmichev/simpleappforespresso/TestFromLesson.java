package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;

@RunWith(AllureAndroidJUnit4.class)
public class TestFromLesson {
    private ViewInteraction menu = onView(
            allOf(withContentDescription("Open navigation drawer"),
                    isDisplayed()));
    private ViewInteraction elementNavHome = onView(withId(R.id.nav_home));
    private ViewInteraction elementNavGallery = onView(withId(R.id.nav_gallery));
    private ViewInteraction elementNavSlideshow = onView(withId(R.id.nav_slideshow));
    private ViewInteraction textSlideshow = onView(withId(R.id.text_slideshow));
    private ViewInteraction navBarGallery = onView(
            allOf(withText("Gallery"), withParent(withId(R.id.toolbar))));

//    private ViewInteraction elementHome = onView(allOf(withId(R.id.design_menu_item_text),
//            withText("Home")));
//    private ViewInteraction elementGallery = onView(allOf(withId(R.id.design_menu_item_text),
//            withText("Gallery")));
//    private ViewInteraction elementSlideshow = onView(allOf(withId(R.id.design_menu_item_text),
//            withText("Slideshow")));

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Feature(value = "Тесты с презентации")
    @Story("Проверка на видимость элементов меню")
    @Test
    public void shouldCheckMenu() {
        menu.perform(click());

        elementNavHome.check(matches(isDisplayed()));
        elementNavGallery.check(matches(isDisplayed()));
        elementNavSlideshow.check(matches(isDisplayed()));
    }
    @Feature(value = "Тесты с презентации")
    @Story("Проверка перехода в меню Gallery")
    @Test
    public void shouldCheckGallery() {
        menu.perform(click());
        elementNavGallery.perform(click());

        navBarGallery.check(matches(isDisplayed()));
    }
    @Feature(value = "Тесты с презентации")
    @Story("Проверка текста на экране Slideshow")
    @Test
    public void shouldCheckSlideshow() {
        menu.perform(click());
        elementNavSlideshow.perform(click());

        textSlideshow.check(matches(withText("This is slideshow fragment")));
    }

}
