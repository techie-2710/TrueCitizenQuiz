package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton prevButton;

    private int currentquestionIndex=0;

    private Questions[] questionBank = new Questions[]{
            new Questions(R.string.question_amendments,false),
            new Questions(R.string.question_declaration,true),
            new Questions(R.string.question_constitution,true),
            new Questions(R.string.question_independence_rights,true),
            new Questions(R.string.question_religion,true),
            new Questions(R.string.question_government,false),
            new Questions(R.string.question_government_feds,false),
            new Questions(R.string.question_government_senators,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Questions question = new Questions(R.string.question_declaration,true);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        questionTextView = findViewById(R.id.answer_text_view);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch(v.getId()){
        case R.id.false_button:
            checkAnswer(false);
            break;

        case R.id.true_button:
            checkAnswer(true);
                break;
        case R.id.next_button:
            // go to next question
            currentquestionIndex = (currentquestionIndex + 1) % questionBank.length;
                updateQuestion();
                break;
        case R.id.prev_button:
            if(currentquestionIndex > 0)
            currentquestionIndex = (currentquestionIndex - 1) % questionBank.length;
            updateQuestion();
            break;
             }
    }

    private void updateQuestion(){
        Log.d("Current", "onClick: " + currentquestionIndex);
        questionTextView.setText(questionBank[currentquestionIndex].getAnswerResId());

    }

    private void checkAnswer(boolean userChooseCorrect){
        boolean answerIsTrue = questionBank[currentquestionIndex].isAnswerTrue();
        int toastMessageId;

        if(userChooseCorrect == answerIsTrue){
            toastMessageId = R.string.correct_answer;
        }
        else
            toastMessageId = R.string.wrong_answer;
        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}