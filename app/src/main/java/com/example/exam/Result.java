package com.example.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
TextView result,correct,wrong;
Button newGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=findViewById(R.id.result);
        correct=findViewById(R.id.correct);
        wrong=findViewById(R.id.wrong);
        newGame=findViewById(R.id.newGame);
        result.setText("you got : "+getIntent().getIntExtra("result",0));
        correct.setText("Correct :"+getIntent().getIntExtra("correct",0));
        wrong.setText("Wrong :"+getIntent().getIntExtra("wrong",0));
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start=new Intent(Result.this,MainActivity.class);

                startActivity(start);
            }
        });
    }

    public void onBackPressed() {
        Intent start=new Intent(Result.this,MainActivity.class);
        startActivity(start);
    }
}
