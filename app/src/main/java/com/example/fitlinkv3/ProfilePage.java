package com.example.fitlinkv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView leftIcon = findViewById(R.id.toolbar_icon);
        ImageButton rightIcon = findViewById(R.id.settings);
        TextView title = findViewById(R.id.toolbar_title);

        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfilePage.this, "You selected settings", Toast.LENGTH_SHORT).show();
            }

            //set menu to inflate
            public boolean onCreateOptionsMenu(Menu menu){
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.toolbar_menu, menu);
                return ProfilePage.super.onCreateOptionsMenu(menu);
                //getMenuInflater().inflate(R.menu.toolbar_menu, menu);
                //return true;
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
                        break;
                    case R.id.Help:
                        Toast.makeText(getApplicationContext(), "You selected Get Help", Toast.LENGTH_LONG).show();
                        break;
                    default:
                }
                return ProfilePage.super.onOptionsItemSelected(item);
            }
        });

        // Assign and initialise variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        // set screen that is selected
        bottomNavigationView.setSelectedItemId(R.id.Profile);

        // code to listen to what item is selected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ExplorePage:
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
            }});

    }
}