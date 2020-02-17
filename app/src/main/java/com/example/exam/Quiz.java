package com.example.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

public class Quiz extends AppCompatActivity {
    ImageView image;
    TextView textName, t_q, t_s;
boolean cheat;
    Button b_f, b_t, b_n, btn_cheat;
    int[] arrScore={0,0,0,0,0,0,0,0,0,0,0,0};
    int Level=5;
ArrayList<Question> quesList=new ArrayList();
    ArrayList<Question> quesCat=new ArrayList();
    Question[] quesArr=new Question[9];

int index,countWrong,countWrite;
    char gender,cat;
    String name="Ziad";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        conID();
        intant();
        buttonLes();
        addQues();

        for(Question q:quesList)
            if(q.getCat()==cat)
            quesArr[index++]=q;
index=4;

         Arrays.sort(quesArr,new Comparator<Question>() {
                @Override
                public int compare(Question o1, Question o2) {
                    return (int)Math.round(Math.random()*10)-6;
                }
            });
            setQues();
    }

    private void setQues() {
while (quesArr[index].getDegree()!=Level) index=(index+1)%9;
        t_q.setText(quesArr[index].gettQuestion());
        Toast.makeText(Quiz.this,Level+"  "+ index ,Toast.LENGTH_SHORT).show();

    }

    private void result() {
        Toast.makeText(Quiz.this,String.valueOf(sum()),Toast.LENGTH_SHORT).show();
        Level=5;
        Intent result=new Intent(Quiz.this,Result.class);
        result.putExtra("result",sum());
        result.putExtra("correct",countWrite);
        result.putExtra("wrong",countWrong);

        startActivity(result);
    }


    private void addQues() {
        quesList.add(new Question(R.string.g1, true,5,'g'));
        quesList.add(new Question(R.string.g2, false,5,'g'));
        quesList.add(new Question(R.string.g3, false,5,'g'));
        quesList.add(new Question(R.string.g4, false,10,'g'));
        quesList.add(new Question(R.string.g5, false,10,'g'));
        quesList.add(new Question(R.string.g6, false,10,'g'));
        quesList.add(new Question(R.string.g7, true,15,'g'));
        quesList.add(new Question(R.string.g8, false,15,'g'));
        quesList.add(new Question(R.string.g9, false,15,'g'));

        quesList.add(new Question(R.string.ch1, true,5,'c'));
        quesList.add(new Question(R.string.ch2, true,5,'c'));
        quesList.add(new Question(R.string.ch3, true,5,'c'));
        quesList.add(new Question(R.string.ch4, false,10,'c'));
        quesList.add(new Question(R.string.ch5, true,10,'c'));
        quesList.add(new Question(R.string.ch6, false,10,'c'));
        quesList.add(new Question(R.string.ch7, false,15,'c'));
        quesList.add(new Question(R.string.ch8, true,15,'c'));
        quesList.add(new Question(R.string.ch9, false,15,'c'));

        quesList.add(new Question(R.string.s1, true,5,'s'));
        quesList.add(new Question(R.string.s2, true,5,'s'));
        quesList.add(new Question(R.string.s3, false,5,'s'));
        quesList.add(new Question(R.string.s4, true,10,'s'));
        quesList.add(new Question(R.string.s5, false,10,'s'));
        quesList.add(new Question(R.string.s6, true,10,'s'));
        quesList.add(new Question(R.string.s7, true,15,'s'));
        quesList.add(new Question(R.string.s8, true,15,'s'));
        quesList.add(new Question(R.string.s9, true,15,'s'));
    }

    private void intant() {
        gender=getIntent().getCharExtra("gender",'n');
        cat=getIntent().getCharExtra("cat",'g');
        name=getIntent().getStringExtra("name");
        if(gender=='M')
            image.setImageDrawable(getDrawable(R.drawable.male));
        else if(gender=='F')
            image.setImageDrawable(getDrawable(R.drawable.female));
        textName.setText(name);
    }

    private void buttonLes() {
        b_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }

        });
        b_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        b_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index=(index+1)%9;
setQues();
            }
        });
        btn_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int text;
                if (quesArr[index].isAnswer())
                    text = R.string.correct;
                else
                    text = R.string.wrong;
              Toast.makeText(Quiz.this,text,Toast.LENGTH_SHORT).show();
              cheat=true;
            }
        });
    }

    private void conID() {
        image=findViewById(R.id.img);
        textName=findViewById(R.id.name);
        b_f = findViewById(R.id.b_f);
        b_t = findViewById(R.id.b_t);
        t_q = findViewById(R.id.t_q);
        t_s = findViewById(R.id.score);
        b_n = findViewById(R.id.change);
        btn_cheat = findViewById(R.id.cheating);
        t_s=findViewById(R.id.score);

    }

    void checkAnswer(boolean userAnswer) {
        int message = 0;
        if (userAnswer == quesArr[index].isAnswer()) {
            message = R.string.correct;
            if(!cheat)
            arrScore[index]=Level;
            countWrite++;
        } else {
            message = R.string.wrong;
            countWrong++;
        }
        t_s.setText(String.valueOf(sum()));
        Level+=5;
        cheat=false;

        if(Level<16)
        setQues();
        else result();
    }
    int sum(){
        int sa=0;
        for (int s:arrScore)
            sa+=s;
        return sa;
    }
}
