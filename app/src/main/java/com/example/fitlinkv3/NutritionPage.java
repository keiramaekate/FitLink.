package com.example.fitlinkv3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlinkv3.Database.AddingShat;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class NutritionPage extends AppCompatActivity {

     //Database1 database1;
    private CalendarView cv;
    private PieChart piechart;
    private TextView date1;
    SQLiteDatabase db;
    SQLiteOpenHelper helper;
    private RecyclerView reccv;
    Button button;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_nutrition_page);

        //button to new activity
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(v -> {

            Intent i = new Intent(NutritionPage.this, AddingShat.class);
            NutritionPage.this.startActivity(i);

        });
//pie chart section
        piechart = (PieChart) findViewById(R.id.Piechart);
        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(5, 10, 5, 5);

        piechart.setDragDecelerationFrictionCoef(0.95f);
//layoyut of piechart
        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);
        piechart.setTransparentCircleRadius(31f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        // change to int and TH
        yValues.add(new PieEntry(34f, "Kcal"));
        yValues.add(new PieEntry(24f, "Protein"));
        yValues.add(new PieEntry(34f, "FAT"));

        PieDataSet dataa = new PieDataSet(yValues, "Nutrition");
        //layout for the sections .
        dataa.setSliceSpace(3f);
        dataa.setSelectionShift(5f);
        dataa.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData((dataa));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);
        piechart.setData(data);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ImageView leftIcon = findViewById(R.id.toolbar_icon);
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


        CalendarView cv = (CalendarView)
                findViewById(R.id.cv);
//


//
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

//    public void onClick(View view) {
//        Intent i = new Intent(NutritionPage.this,AddingShat.class);
//        NutritionPage.this.startActivity(i);
//
//    }



}

