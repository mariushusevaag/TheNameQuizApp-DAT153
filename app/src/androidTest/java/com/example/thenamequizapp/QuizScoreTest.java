package com.example.thenamequizapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.converters.Converter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class QuizScoreTest {

    // Brings in the quiz activity
    @Rule
    public ActivityTestRule<QuizActivity> qActivityTestRule = new ActivityTestRule<>(QuizActivity.class);

    @Test
    public void quizScoreTest() {

        if (qActivityTestRule.getActivity().activePerson == null) {
            addPersonsBeforeTest();
            onView(withId(R.id.startQuizBtn)).perform(click());
        }

        // Gets the active person of the quiz
        Person person = qActivityTestRule.getActivity().activePerson;

        // Paste the name of the active person in the quiz into the edit text field
        onView(withId(R.id.quizAns_Txt)).perform(replaceText(person.getName()));

        // Clicks the next button in the quiz
        onView(withId(R.id.quizNext_Btn)).perform(click());

        assertEquals(1, qActivityTestRule.getActivity().correct);

        Boolean quizDone = false;

        while (!quizDone) {
            try {
                onView(withId(R.id.quiz)).check(matches(isDisplayed()));

                // Paste in guaranteed the wrong answer in the edit text
                onView(withId(R.id.quizAns_Txt)).perform(replaceText("feil svar"));

                // Clicks the next button in the quiz
                onView(withId(R.id.quizNext_Btn)).perform(click());
            } catch (NoMatchingViewException e) {
                // Checks if counter has increased to 1
                assertEquals(1, qActivityTestRule.getActivity().correct);

                quizDone = true;
            }
        }
    }

    private void addPersonsBeforeTest() {
        Uri p1Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.jens);
        Uri p2Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.erna);

        String imageSource1 = "";
        String imageSource2 = "";
        try {
            InputStream is1 = qActivityTestRule.getActivity().getContentResolver().openInputStream(p1Uri);
            InputStream is2 = qActivityTestRule.getActivity().getContentResolver().openInputStream(p2Uri);
            Bitmap bitmap1 = BitmapFactory.decodeStream(is1);
            Bitmap bitmap2 = BitmapFactory.decodeStream(is2);
            imageSource1 = Converter.BitMapToString(bitmap1);
            imageSource2 = Converter.BitMapToString(bitmap2);
        } catch (FileNotFoundException e) {

        }

        Person person1 = new Person("Test1", imageSource1);
        Person person2 = new Person("Test2", imageSource2);

        qActivityTestRule.getActivity().appDb.personDao().addPerson(person1);
        qActivityTestRule.getActivity().appDb.personDao().addPerson(person2);
    }
}

