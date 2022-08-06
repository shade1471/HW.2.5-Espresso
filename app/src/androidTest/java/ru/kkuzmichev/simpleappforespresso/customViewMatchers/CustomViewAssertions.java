package ru.kkuzmichev.simpleappforespresso.customViewMatchers;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

public class CustomViewAssertions {

    public static ViewAssertion isRecyclerView() {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                try {
                    RecyclerView recyclerView = (RecyclerView) view;
                } catch (ClassCastException cce) {
                    throw new IllegalStateException("This isn't a RecyclerView");
                }
            }
        };
    }
}
