package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.helpers.AppHelper;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    // View
    ImageView imageView;
    Button button;
    EditText editText;

    // Variables
    ArrayList<Person> persons;
    Person activePerson;
    int personsAmount;
    int counter;
    int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Views
        imageView = findViewById(R.id.quiz_image);
        button = findViewById(R.id.quizNext_Btn);
        editText = findViewById(R.id.quizAns_Txt);

        button.setOnClickListener(v -> {
            // Check if editText field is empty
            if (!editText.getText().toString().matches("")) {
                nextBtnClick();
            } else {
                Toast.makeText(this, "You need to write an answer", Toast.LENGTH_SHORT).show();
            }
        });

        startQuiz();
    }

    public void startQuiz() {
        // Bringing in persons from the database
        persons = ((AppHelper) this.getApplication()).getPersons();

        // Size of the database array
        personsAmount = persons.size();

        continueQuiz();
    }

    public void continueQuiz() {
        if(counter < personsAmount) {
            // Pick a random person from the persons array
            randomPerson();

            // Inserts photo of person to the view
            if (activePerson.getIntImg() != 0) {
                imageView.setImageResource(activePerson.getIntImg());
            } else if(activePerson.getUri() != null) {
                imageView.setImageURI(activePerson.getUri());
            }

        } else {
            Intent i = new Intent(this, MainActivity.class);

            String resultTxt = correct + "/" + personsAmount;

            i.putExtra("titleString", "Latest score: " + resultTxt);

            startActivity(i);

            Toast.makeText(this, "You are done and got " + correct + " correct answers!", Toast.LENGTH_SHORT).show();
        }
    }

    public void nextBtnClick() {
        String answer = editText.getText().toString().toLowerCase();

        if(answer.matches(activePerson.getName().toLowerCase())) {
            correct++;
        }

        // Remove active person from array
        persons.remove(activePerson);

        // Count round
        counter += 1;

        // Clear textfield
        editText.getText().clear();

        continueQuiz();
    }

    public void randomPerson() {
        Random random = new Random();

        activePerson = persons.get(random.nextInt(persons.size()));
    }
}