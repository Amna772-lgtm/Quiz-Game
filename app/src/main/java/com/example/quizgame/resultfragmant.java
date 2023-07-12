package com.example.quizgame;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.quizgame.quizfragment;

public class resultfragmant extends Fragment {

    private TextView answerTextView;

    public resultfragmant() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.resultfragmant, container, false);

        answerTextView = view.findViewById(R.id.answer_view);
        return view;
    }

    private void showResult(quizfragment qf) {
        double score = (double) qf.correctAnswerCount / qf.quizQuestions.size() * 100;
        String resultMessage = "Quiz Result:\n" +
                "Correct Answers: " + qf.correctAnswerCount + "\n" +
                "Total Questions: " + qf.quizQuestions.size() + "\n" +
                "Score: " + score + "%";

        answerTextView.setText(resultMessage);
    }
}