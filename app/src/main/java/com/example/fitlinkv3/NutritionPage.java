package com.example.fitlinkv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.fitlinkv3.AddingShat;
//import com.example.fitlinkv3.Database.Database1;
//import com.example.fitlinkv3.Database.Database1;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NutritionPage extends AppCompatActivity {

   // Database1 database1;
    private CalendarView cv;
    private PieChart piechart;
    private TextView date1;

//    SQLiteDatabase db;
//    SQLiteOpenHelper helper;
    private RecyclerView reccv;
    FloatingActionButton buttonadd;
    Button button;

    LinearLayout Recipe1;
    LinearLayout Recipe2;
    LinearLayout Recipe3;
    LinearLayout Recipe4;

    LinearLayout food2;
    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

        protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_nutrition_page);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //button to new activity
        button = (Button) this.findViewById(R.id.buttonadd);
        button.setOnClickListener(v -> {

            Intent i = new Intent(NutritionPage.this, AddingShat.class);
            NutritionPage.this.startActivity(i);

        });

        //set linearlayouts and make them clickable
        Recipe1 = findViewById(R.id.Recipe1);
        Recipe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = "https://realfood.tesco.com/meal-planner/view/seasonal-suppers-march.html";
                Uri website = Uri.parse(food);

                Intent openfood = new Intent(Intent.ACTION_VIEW, website);
                startActivity(openfood);
            }
        });
        Recipe2 = findViewById(R.id.Recipe2);
        Recipe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = "https://realfood.tesco.com/meal-planner/view/seasonal-suppers-march.html";
                Uri website = Uri.parse(food);

                Intent openfood = new Intent(Intent.ACTION_VIEW, website);
                startActivity(openfood);
            }
        });
        Recipe3 = findViewById(R.id.Recipe3);
        Recipe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = "https://realfood.tesco.com/meal-planner/view/seasonal-suppers-march.html";
                Uri website = Uri.parse(food);

                Intent openfood = new Intent(Intent.ACTION_VIEW, website);
                startActivity(openfood);
            }
        });
        Recipe4 = findViewById(R.id.Recipe1);
        Recipe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = "https://realfood.tesco.com/meal-planner/view/seasonal-suppers-march.html";
                Uri website = Uri.parse(food);

                Intent openfood = new Intent(Intent.ACTION_VIEW, website);
                startActivity(openfood);
            }
        });

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
                Toast.makeText(getApplicationContext(), "You selected settings", Toast.LENGTH_LONG).show();
                Intent Settings = new Intent(this,Settings.class);
                this.startActivity(Settings);
                return true;
            case R.id.FAQs:
                Toast.makeText(getApplicationContext(), "You selected FAQs", Toast.LENGTH_LONG).show();
                Intent FAQs = new Intent(this,FAQs.class);
                this.startActivity(FAQs);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

}


