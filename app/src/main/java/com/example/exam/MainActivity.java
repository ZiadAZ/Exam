package com.example.exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button chem,sport,geo,startt;
EditText name;
RadioButton male,female;
TextView catText;
char gender;
char cat;
boolean[] enable=new boolean[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chem = findViewById(R.id.chem);
        sport = findViewById(R.id.sport);
        geo = findViewById(R.id.geo);
        startt=findViewById(R.id.star);
        name=findViewById(R.id.name);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        catText=findViewById(R.id.cat);
        buttonClick();

    }

    void buttonClick(){
        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

cat='c';
catText.setText("كيمياء");
enable[0]=true;
            }
        });
        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enable[0]=true;

cat='g';
                catText.setText("جغرافيا");

            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enable[0]=true;
cat='s';

                catText.setText("رياضة");
            }
        });
        startt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enable[0]&&enable[1]) {
                    Intent quiz = new Intent(MainActivity.this, Quiz.class);
                    quiz.putExtra("gender", gender);
                    quiz.putExtra("name", name.getText().toString());
                    quiz.putExtra("cat", cat);
                    startActivity(quiz);
                }
                else Toast.makeText(MainActivity.this,"ادخل التصنيف والنوع",Toast.LENGTH_LONG).show();


            }

        });
    }
    public void onRadioClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        enable[1]=true;

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    gender='M';
                    break;
            case R.id.female:
                if (checked)
                   gender='F';
                    break;
        }
    }

}

