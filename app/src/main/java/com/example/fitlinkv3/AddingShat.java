package com.example.fitlinkv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddingShat extends AppCompatActivity {

    EditText caloriesint, proteinint, fatint;
    Button button2,button3, button4;
    Database1 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_shat);


        proteinint = findViewById(R.id.proteinint);
        caloriesint = findViewById(R.id.caloriesint);
        fatint = findViewById(R.id.fatint);

        button2 = findViewById(R.id.insertbutton);
        button3 = findViewById(R.id.update);
        button4 = findViewById(R.id.delete);
        db = new Database1(this);

//        button2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Database1 dbse = new Database1(AddingShat.this);
//                dbse.insertAction(Integer.valueOf(caloriesint.getText().toString().trim()),
//                        Integer.valueOf(proteinint.getText().toString().trim()),
//                        Integer.valueOf(fatint.getText().toString().trim()));
//setContentView(R.layout.activity_adding_shat);
//            }
//        });











    }
}





