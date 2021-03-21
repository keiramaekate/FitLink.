package com.example.fitlinkv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.BreakIterator;

public class ExplorePage extends AppCompatActivity {

    private BreakIterator title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView leftIcon = findViewById(R.id.toolbar_icon);
        ImageButton rightIcon = findViewById(R.id.settings);
        TextView title = findViewById(R.id.toolbar_title);

        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExplorePage.this, "You selected settings", Toast.LENGTH_SHORT).show();
            }

            //set menu to inflate
            public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.toolbar_menu, menu);
                return ExplorePage.super.onCreateOptionsMenu(menu);
                //getMenuInflater().inflate(R.menu.toolbar_menu, menu);
                // return true;
            }


            // code to inflate the menu items
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.Search:
                        Toast.makeText(getApplicationContext(), "You selected Search", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.Settings:
                        Toast.makeText(getApplicationContext(), "You selected Settings", Toast.LENGTH_LONG).show();
                        // This does not work, why?
                        // Intent settings = new Intent(this, SettingsActivity.class);
                        //startActivity(settings);
                        break;
                    case R.id.Help:
                        Toast.makeText(getApplicationContext(), "You selected Get Help", Toast.LENGTH_LONG).show();
                        break;
                    default:
                }
                return ExplorePage.super.onOptionsItemSelected(item);
            }
        });

        // open headspace website
        ImageButton headspaceBtn = (ImageButton) findViewById(R.id.headspaceBtn);
        headspaceBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String headspace = "https://www.headspace.com/?utm_source=google&utm_medium=cpc&utm_campaign=917256451&utm_content=51529951612&utm_term=409650155250&headspace&gclid=CjwKCAiA7939BRBMEiwA-hX5JyY-K1CL6RtaABWXDO2j_w497xTCa7Wu2AX04VwbIVkLbnajrixClRoCjPoQAvD_BwE";
                Uri website = Uri.parse(headspace);

//                check if Headspace app is installed, otherwise open on internet
                Intent openHeadpace = new Intent(Intent.ACTION_VIEW, website);
                if (!(openHeadpace.resolveActivity(getPackageManager()) == null)) {
                    startActivity(openHeadpace);
                }
            }
        });

        // open run together link
        ImageButton runtogetherBtn = (ImageButton) findViewById(R.id.runtogether);
        runtogetherBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String runtogether = "https://groups.runtogether.co.uk/MedwayFit";
                Uri website = Uri.parse(runtogether);

                Intent openRunTogether = new Intent(Intent.ACTION_VIEW, website);
                if (!(openRunTogether.resolveActivity(getPackageManager()) == null)) {
                    startActivity(openRunTogether);
                }
            }
        });

//      Open link to STRAVA running challenges
        ImageButton runchallenges = (ImageButton) findViewById(R.id.running_challenge);
        runchallenges.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String runchallenge = "https://www.strava.com/challenges/December-Running-Endurance-2020";
                Uri website = Uri.parse(runchallenge);

                Intent openrunchallenge = new Intent(Intent.ACTION_VIEW, website);
                if (!(openrunchallenge.resolveActivity(getPackageManager()) == null)) {
                    startActivity(openrunchallenge);
                }
            }
        });

        ImageButton cyclechallenges = (ImageButton) findViewById(R.id.cycling_challenge);
        cyclechallenges.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cyclechallenges = "https://www.strava.com/challenges/December-2020-Cycling-Challenge";
                Uri website = Uri.parse(cyclechallenges);

                Intent opencyclechallenges = new Intent(Intent.ACTION_VIEW, website);
                if (!(opencyclechallenges.resolveActivity(getPackageManager()) == null)) {
                    startActivity(opencyclechallenges);
                }
            }
        });

        ImageButton swimchallenges = (ImageButton) findViewById(R.id.swimming_challenge);
        swimchallenges.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String swimchallenges = "https://www.strava.com/challenges/1704?hl=en-GB";
                Uri website = Uri.parse(swimchallenges);

                Intent openswimchallenges = new Intent(Intent.ACTION_VIEW, website);
                if (!(openswimchallenges.resolveActivity(getPackageManager()) == null)) {
                    startActivity(openswimchallenges);
                }
            }
        });
    }
}