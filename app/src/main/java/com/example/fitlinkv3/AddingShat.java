package com.example.fitlinkv3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddingShat extends AppCompatActivity {
    EditText caloriesint, proteinint, fatint;
    Button buttonadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_shat);

        caloriesint = findViewById(R.id.caloriesint);
        proteinint = findViewById(R.id.proteinint);
        fatint = findViewById(R.id.fatint);

        buttonadd = findViewById(R.id.buttonadd);
        buttonadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database1 dbse = new Database1(AddingShat.this);
                dbse.insertAction(Integer.valueOf(caloriesint.getText().toString().trim()),
                        Integer.valueOf(proteinint.getText().toString().trim()),
                        Integer.valueOf(fatint.getText().toString().trim()));

            }
        });

    }
}





