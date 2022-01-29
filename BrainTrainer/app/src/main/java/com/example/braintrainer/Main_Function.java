package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main_Function extends AppCompatActivity {





    Button Go, button0,button1,button2,button3;
    int location,a,b,sum,scor=0,noquestion=0;
    Random rand=new Random();
    ArrayList<Integer> answer1;
    TextView time,score,question,answer,playAgain;
    String Lay="go_button";
    boolean pp=true;



    public void setRandomVariables() {
        a = rand.nextInt(21); b = rand.nextInt(21);
        sum=a+b;
        Log.i("new a,b =",String.valueOf(a)+"  "+String.valueOf(b));
    }

    //////////


    public void setLocation(){
        location=rand.nextInt(4);
        Log.i("Location", String.valueOf(location));
    }


    //////////////


    public void makeArray(){

        answer1=new ArrayList<Integer>();

    }


    //////////


    public void setQuestion(){
        // setRandomVariables();
        question.setText(a +" + "+ b);
        // noquestion++;

    }



    public void setRandom() {

        setRandomVariables();
        setQuestion();
        for (int i = 0; i < 4; i++) {


            if (i == location) {

                answer1.add(sum);
                Log.i("location  ",String.valueOf(i)+"  =  "+ String.valueOf(sum) );

            }

            else {
                int wrong = rand.nextInt(41);


                while (wrong == sum) {
                    wrong = rand.nextInt(41);
                }

                answer1.add(wrong);
            }



        }

        Log.d("list", answer1.toString());

        button0.setText(Integer.toString(answer1.get(0)));
        button1.setText(Integer.toString(answer1.get(1)));
        button2.setText(Integer.toString(answer1.get(2)));
        button3.setText(Integer.toString(answer1.get(3)));


    }


    //////////////




    public void play(){

        scor=0;
        noquestion=0;
        playAgain.setVisibility(View.INVISIBLE);
        score.setText(scor+"/"+noquestion);

        // setQuestion();

        time.setText("00:30");
        CountDownTimer start = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText("00:" + String.valueOf(l / 1000));

            }

            @Override
            public void onFinish() {
                answer.setText("TIME UP!");
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();


    }

//////////////



    public void choseAnswer(View view){
        String st=view.getTag().toString();
        //Log.i("info","button clicked= "+st);


        if(Integer.toString(location).equals(view.getTag())){
            // answer.setText("CORRECT!");
            Log.i("correct","  answer");
            answer.setText("Correct!");scor++;

        }
        else{
            answer.setText("OH WRONG!");
            Log.i("wrong","  answer"); //answer.setBackgroundColor(8);
        }
        noquestion++;
        answer1.removeAll(Collections.singleton(true));
        updateScore();
        makeArray();
        setLocation();
        // setRandomVariables();
        setRandom();
        //setQuestion();

    }

///////////////


    public void updateScore(){

        score.setText(scor+"/"+noquestion);
        Log.i("score updated","   its fine");
    }

    ///////////

    public void setPlayAgain(View view){

        play();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

    }











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Go=findViewById(R.id.Go_Button);
        button0=findViewById(R.id.Button0);
        button1=findViewById(R.id.Button1);
        button2=findViewById(R.id.Button2);
        button3=findViewById(R.id.Button3);
        playAgain = findViewById(R.id.button);

        question=findViewById(R.id.Question);
        score=findViewById(R.id.Result);
        time=findViewById(R.id.Times);
        answer=findViewById(R.id.Correct);
        setRandomVariables();



        setLocation();
        makeArray();
        setQuestion();
        setRandom();

        play();
    }
}