package com.example.thenamequizapp;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.app.Instrumentation.ActivityResult;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest {

    // Brings in the database activity
    @Rule
    public IntentsTestRule<AddActivity> aActivityTestRule = new IntentsTestRule<>(AddActivity.class);

    @Test
    public void addPerson() {
        int personsInDatabase = aActivityTestRule.getActivity().persons;

        Matcher<Intent> expectedIntent = allOf(
                hasAction(Intent.ACTION_PICK)
        );

        ActivityResult activityResult = createGalleryPickActivityResultStub();
        intending(expectedIntent).respondWith(activityResult);

        onView(withId(R.id.addPhotoBtn)).perform(click());
        intended(expectedIntent);

        onView(withId(R.id.addPersonNameEdit)).perform(replaceText("Marius"));
        onView(withId(R.id.savePersonBtn)).perform(click());

        checkPersonAmountAfterAdding(personsInDatabase);
    }

    @Test
    public void deletePerson() {
        int personsInDatabase = aActivityTestRule.getActivity().persons;

        onView(isRoot()).perform(ViewActions.pressBack());

        onView(allOf(withId(R.id.deleteBtn), hasSibling(withText("Marius")))).perform(click());

        checkPersonAmountAfterDeleting(personsInDatabase);
    }

    private boolean checkPersonAmountAfterAdding(int person) {
        assertEquals(aActivityTestRule.getActivity().appDb.personDao().getPersons().size(), person+1);
        return true;
    }

    private boolean checkPersonAmountAfterDeleting(int person) {
        assertEquals(aActivityTestRule.getActivity().appDb.personDao().getPersons().size(), person - 1);
        return true;
    }

    private ActivityResult createGalleryPickActivityResultStub() {
        Resources resources = InstrumentationRegistry.getInstrumentation()
                .getContext().getResources();

        Uri imageUri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.test);

        Intent resultIntent = new Intent();
        resultIntent.setData(imageUri);
        return new ActivityResult(Activity.RESULT_OK, resultIntent);
    }
}
