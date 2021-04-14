package com.example.fitlinkv3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class FAQs extends AppCompatActivity {

    /*String[]  questions = new String [] {
            "What is FitLink?",
            "What is Strava?",
            "What does FitLink offer that Strava doesn't?",
            "Where can I log my intake of food?",
            "Why doesn't FitLink let me record like Strava does?",
            "Who created FitLink?",
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqs);
/*
        ArrayAdapter<String> cheeseAdapter =
                new ArrayAdapter<String>(this,
                        R.layout.support_simple_spinner_dropdown_item,
                        R.id.cheeseList,
                        cheeses);
        cheeseList.setAdapter(cheeseAdapter);*/

  /* ListView lvQuestions = (ListView)findViewById(R.id.lvQuestions);

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1
                    ,questions);

            lvQuestions.setAdapter(adapter);
*/
        }


}
