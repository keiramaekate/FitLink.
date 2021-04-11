package com.example.fitlinkv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitlinkv3.retrofit.Athlete;
import com.example.fitlinkv3.retrofit.ServiceGenerator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsandberg.stravaauthenticator.StravaAuthenticateActivity;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends AppCompatActivity {

    TextView stravaUsername;
    ImageView stravaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //Get athlete information
        String accessToken = StravaAuthenticateActivity.getStravaAccessToken(this);
        ServiceGenerator.getEndPointInterface().getAthlete("Bearer " + accessToken).enqueue(new Callback<Athlete>() {
            @Override
            public void onResponse(Call<Athlete> call, Response<Athlete> response) {
                if (response != null && response.isSuccessful()) {
                    Athlete stravaresponse = response.body();
                    if (stravaresponse != null) {
                        stravaUsername.setText(stravaresponse.getUsername());
                        //set profile picture
                        Picasso.get()
                                .load(stravaresponse.getProfile())
                                //.placeholder(R.drawable.fitlink_logo)
                                //.error(R.drawable.bday_icon)
                                .into(stravaImage);
                    }
                }
            }


            @Override
            public void onFailure(Call<Athlete> call, Throwable t) {

            }
        });

        stravaUsername = findViewById(R.id.tvUsername);
        stravaImage = findViewById(R.id.ivStravaImage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView leftIcon = findViewById(R.id.toolbar_icon);
        TextView title = findViewById(R.id.toolbar_title);

        // Assign and initialise variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        // set screen that is selected
        bottomNavigationView.setSelectedItemId(R.id.Profile);

        // code to listen to what item is selected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Explore:
                        startActivity(new Intent(getApplicationContext()
                                , ExplorePage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.Progress:
                        startActivity(new Intent(getApplicationContext()
                                , ProgressPage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.Nutrition:
                        startActivity(new Intent(getApplicationContext()
                                , NutritionPage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.Profile:
                        return true;
                }
                return false;
            }
        });
    }

    //set menu to inflate
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // code to inflate the menu items
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Settings:
                Toast.makeText(getApplicationContext(), "You selected Search", Toast.LENGTH_LONG).show();
                //newSettings();
                return true;
            case R.id.chatbot_assistant:
                Toast.makeText(getApplicationContext(), "You selected Settings", Toast.LENGTH_LONG).show();
                //new(chatbotAssistant();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }}