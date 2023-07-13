package com.example.quizgame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class quizfragment extends Fragment {
    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] grassLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] rootLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private DatabaseHelper databaseHelper;
    private List<String> currentShiftAnswers;
    private int currentQuestionCount = 0;
    private int currentShiftNumber = 1;
    private String answerString = "";
    String L;
    int Score;


    public quizfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.quizfragment, container, false);


        databaseHelper = new DatabaseHelper(requireContext());
        currentShiftAnswers = new ArrayList<>();
        L=getRandomLetter();
        letterTextView = rootView.findViewById(R.id.letter_text_view);
        letterTextView.setText(L);

        answerTextView = rootView.findViewById(R.id.answer_text_view);

        Button skyButton = rootView.findViewById(R.id.sky_button);
        skyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("Sky Letter");
            }
        });

        Button grassButton = rootView.findViewById(R.id.grass_button);
        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("Grass Letter");
            }
        });

        Button rootButton = rootView.findViewById(R.id.root_button);
        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("Root Letter");
            }
        });

        return rootView;
    }
    private void checkAnswer(String expectedAnswer) {



        if (expectedAnswer.equals(answerString)) {
            //answerTextView.setText("Correct! The letter '"+L+"' is a "+answerString+".");
            currentShiftAnswers.add("Correct!");
            Score=Score+1;
        } else {
            //answerTextView.setText("Incorrect! The letter ' "+L+"' is a "+answerString+".");
            currentShiftAnswers.add("InCorrect!");
        }
        currentQuestionCount++;

        if (currentQuestionCount == 5) {
            answerTextView.setText("Quiz Completed!!!");
            currentShiftAnswers.add("Total Score "+Score);
            processShiftCompletion();
        }

        // Wait for 5 seconds and create a new question
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                L=getRandomLetter();
                letterTextView.setText(L);
                answerTextView.setText("");
            }
        }, 1000); // 3000 milliseconds = 3 seconds
    }

    private void processShiftCompletion() {
        if (!currentShiftAnswers.isEmpty()) {
            databaseHelper.addShift(currentShiftNumber, currentShiftAnswers);
            currentShiftNumber++;
            currentQuestionCount = 0;
            currentShiftAnswers.clear();
            Score= 0;
        }
    }
    private String getRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;
        switch (category) {
            case 0:
                letter = skyLetters[random.nextInt(skyLetters.length)];
                answerString = "Sky Letter";
                break;
            case 1:
                letter = grassLetters[random.nextInt(grassLetters.length)];
                answerString = "Grass Letter";
                break;
            default:
                letter = rootLetters[random.nextInt(rootLetters.length)];
                answerString = "Root Letter";
                break;
        }
        return String.valueOf(letter);
    }
}