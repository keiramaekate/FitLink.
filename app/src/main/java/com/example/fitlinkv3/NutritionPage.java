package com.example.fitlinkv3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.fitlinkv3.AddingShat;
//import com.example.fitlinkv3.Database.Database1;
//import com.example.fitlinkv3.Database.Database1;
import com.github.mikephil.charting.charts.PieChart;
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

        ImageButton food = (ImageButton) findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String food = "https://www.hellofresh.co.uk/recipes/waldorf-salad-style-salad-with-chicken-and-bacon-6062fdb78e9ee6152752821f";
                Uri website = Uri.parse(food);

                Intent openfood = new Intent(Intent.ACTION_VIEW, website);
                if (!(openfood.resolveActivity(getPackageManager()) == null)) {
                    startActivity(openfood);
                }
            }
        });

//                // button doesnt open page
//                Button buttonadd = (Button)
//                        findViewById(R.id.buttonadd);

}




}
