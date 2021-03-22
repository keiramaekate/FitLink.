package com.example.fitlinkv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NutritionPage extends AppCompatActivity {

    private Database1 database1;
    private CalendarView cv;
    private TextView date1;

    private RecyclerView reccv;
    FloatingActionButton buttonadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView leftIcon = findViewById(R.id.toolbar_icon);
        ImageButton rightIcon = findViewById(R.id.settings);
        TextView title = findViewById(R.id.toolbar_title);

        // Assign and initialise variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        // set screen that is selected
        bottomNavigationView.setSelectedItemId(R.id.Nutrition);

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
                        return true;

                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext()
                                , ProfilePage.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NutritionPage.this, "You selected settings", Toast.LENGTH_SHORT).show();
            }

            //set menu to inflate
            public boolean onCreateOptionsMenu(Menu menu){
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.toolbar_menu, menu);
                return NutritionPage.super.onCreateOptionsMenu(menu);
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
                return NutritionPage.super.onOptionsItemSelected(item);
            }
        });

        CalendarView cv = (CalendarView)
                findViewById(R.id.cv);
        RecyclerView reccv = (RecyclerView)
                findViewById(R.id.reccv);

        // button doesnt open page
        Button buttonadd = (Button)
                findViewById(R.id.buttonadd);

//        buttonadd.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void Method(View v) {
//                //when clicked it goes to the input page
//                Intent info = new Intent(NutritionPage.this, AddingShat.class);
//                startActivity(info);
//            }
//        });
    }
}
