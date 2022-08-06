package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.kkuzmichev.simpleappforespresso.customViewMatchers.CustomViewAssertions.isRecyclerView;
import static ru.kkuzmichev.simpleappforespresso.customViewMatchers.CustomViewMatcher.recyclerViewSizeMatcher;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Story;

@RunWith(AllureAndroidJUnit4.class)
public class IntentAndIdlingTest {
    private ViewInteraction moreOptions = onView(
            allOf(withContentDescription("More options"), isDisplayed()));
    private ViewInteraction setting = onView(allOf(withId(R.id.title)));
    private ViewInteraction menu = onView(
            allOf(withContentDescription("Open navigation drawer")));
    private ViewInteraction elementNavGallery = onView(withId(R.id.nav_gallery));

    private ViewInteraction galleryItemSeven = onView(allOf(withId(R.id.item_number), withText("7")));
    private ViewInteraction recycleList = onView(withId(R.id.recycle_view));
    private ViewInteraction galleryLayout = onView(withId(R.id.nav_host_fragment_content_main));


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }

    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Story("Проверка с `подписыванием` на интент")
    @Test
    public void shouldCheckIntent() {
        moreOptions.perform(click());
        Intents.init();
        setting.perform(click());
        intended(
                hasData("https://google.com")
        );
        Intents.release();
    }

    @Story("Работа с асинхронымми событиями, через IdlingResources, поиск 7 элемента списка")
    @Test
    public void shouldCheckGalleryItems() {
        menu.perform(click());
        elementNavGallery.check(matches(isDisplayed()));
        elementNavGallery.perform(click());
        galleryLayout.perform(swipeUp());
        galleryItemSeven.check(matches(isDisplayed()));
    }

    @Story("Использование кастомных matcher и assertions")
    @Test
    public void shouldCheckSizeGalleryAndTypeView() {
        menu.perform(click());
        elementNavGallery.check(matches(isDisplayed()));
        elementNavGallery.perform(click());
        galleryLayout.perform(swipeUp());
        recycleList.check(isRecyclerView());
        recycleList.check(matches(recyclerViewSizeMatcher(10)));
    }

}
