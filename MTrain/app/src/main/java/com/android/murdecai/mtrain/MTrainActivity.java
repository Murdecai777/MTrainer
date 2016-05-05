package com.android.murdecai.mtrain;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MTrainActivity extends Activity {

    private Button mButton1, mButton2, mButton3, mButton4;
    TextView mQuestion, mRightAnswersCount;
    GenerateData gen;
    int level = 1;
    int rightAnswersCount = 0;
    private static final String KEY_INDEX_OPT = "1", KEY_INDEX_EXP = "2", KEY_INDEX_COUNT = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gen = new GenerateData();
        gen.chooseLevel(1);
        if (savedInstanceState != null) {
            gen.option =savedInstanceState.getStringArray(KEY_INDEX_OPT);
            gen.s_exp = savedInstanceState.getString(KEY_INDEX_EXP);
            rightAnswersCount = savedInstanceState.getInt(KEY_INDEX_COUNT);
        }
        setContentView(R.layout.activity_mtrain);



        mQuestion = (TextView)findViewById(R.id.question);
        mQuestion.setText(gen.s_exp);

        mRightAnswersCount = (TextView)findViewById(R.id.rightAnswerCount);
        mRightAnswersCount.setText("Right answers: " + str(rightAnswersCount));

        mButton1 = (Button)findViewById(R.id.answer1);
        mButton1.setText(gen.option[0]);
        mButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isAnsRight(0);
                refreshView(level);

            }
        });

        mButton2 = (Button)findViewById(R.id.answer2);
        mButton2.setText(gen.option[1]);
        mButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isAnsRight(1);
                refreshView(level);
            }
        });

        mButton3 = (Button)findViewById(R.id.answer3);
        mButton3.setText(gen.option[2]);
        mButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isAnsRight(2);
                refreshView(level);
            }
        });

        mButton4 = (Button)findViewById(R.id.answer4);
        mButton4.setText(gen.option[3]);
        mButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isAnsRight(3);
                refreshView(level);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("MTrainActivity", "onSaveInstanceState");
        savedInstanceState.putStringArray(KEY_INDEX_OPT, gen.option);
        savedInstanceState.putString(KEY_INDEX_EXP, gen.s_exp);
        savedInstanceState.putInt(KEY_INDEX_COUNT, rightAnswersCount);
    }

    private void refreshView(int lvl){
        gen.chooseLevel(lvl);
        mQuestion.setText(gen.s_exp);
        mButton1.setText(gen.option[0]);
        mButton2.setText(gen.option[1]);
        mButton3.setText(gen.option[2]);
        mButton4.setText(gen.option[3]);
        mRightAnswersCount.setText("Right answers: " + str(rightAnswersCount));
    }


    private void isAnsRight(int n){

        if (gen.option[n].equals(gen.s_answer)) {
            final Toast toast = Toast.makeText(MTrainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 400);
            rightAnswersCount++;
        }
        else {
            final Toast toast = Toast.makeText(MTrainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 400);

        }

    }

    public String str (int number){
        String s = Integer.toString(number);
        return s;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
       // getMenuInflater().inflate(R.menu.activity_mtrain,menu);
        return true;
    }
}
