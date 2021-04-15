package com.example.fitlinkv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    LinearLayout food2;
    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.activity_nutrition_page);

            //button to new activity
            button = (Button) this.findViewById(R.id.buttonadd);
            button.setOnClickListener(v -> {

                Intent i = new Intent(NutritionPage.this,AddingShat.class);
                NutritionPage.this.startActivity(i);

            });

//        //ImageButton food = (ImageButton) findViewById(R.id.food);
//        //food.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String food = "https://www.strava.com/challenges/1704?hl=en-GB";
//                Uri website = Uri.parse(food);
//
//                Intent openfood = new Intent(Intent.ACTION_VIEW, website);
//                if (!(openfood.resolveActivity(getPackageManager()) == null)) {
//                    startActivity(openfood);
//                }
//            }
//        });

//        food2 = findViewById(R.id.articleTwo);
//        food2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String food2 = "https://realfood.tesco.com/meal-planner/view/seasonal-suppers-march.html";
//                Uri website = Uri.parse(food2);
//                Intent openfood = new Intent(Intent.ACTION_VIEW, website);
//                startActivity(openfood);
//            }
//        });

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
//                // button doesnt open page
//                Button buttonadd = (Button)
//                        findViewById(R.id.buttonadd);

}




}
