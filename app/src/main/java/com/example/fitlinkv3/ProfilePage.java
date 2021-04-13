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

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.fitlinkv3.retrofit.ActivityStats;
import com.example.fitlinkv3.retrofit.Athlete;
import com.example.fitlinkv3.retrofit.ServiceGenerator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsandberg.stravaauthenticator.StravaAuthenticateActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends AppCompatActivity {

    //profile info
    TextView stravaUsername;
    ImageView stravaImage;

    //ID for stats api call
    public Integer stravaId;

    //athlete stats
    TextView stravaTotalRunMiles;
    TextView stravaTotalRideMiles;
    TextView stravaRecentRunMiles;
    TextView stravaRecentRideMiles;

    //PieChart setup
    AnyChartView totalStatsChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //Create instance of piechart
        Pie pie = AnyChart.pie();
        //Arraylist for the piechart
        List<DataEntry> countstats = new ArrayList<>();

        //Get athlete information using retrofit call
        //Access token is called to make the API call
        String accessToken = StravaAuthenticateActivity.getStravaAccessToken(this);

        //Service generator (retrofit)
        ServiceGenerator.getEndPointInterface().getAthlete("Bearer "+ accessToken).enqueue(new Callback<Athlete>()
        {
            @Override
            public void onResponse(Call<Athlete> call, Response<Athlete> response)
            {
                if (response != null && response.isSuccessful())
                {
                    Athlete stravaresponse = response.body();
                    if(stravaresponse !=null)
                    {
                        stravaUsername.setText(stravaresponse.getUsername());
                        //set profile picture
                        Picasso.get()
                                .load(stravaresponse.getProfile())
                                .placeholder(R.drawable.fitlink_logo)
                                //.error(R.drawable.bday_icon)
                                .into(stravaImage);

                        stravaId = stravaresponse.getId();
                        //retrofit api call for AthleteStats using above id variable
                        ServiceGenerator.getEndPointInterface().getStats("Bearer "+accessToken,stravaId).enqueue(new Callback<ActivityStats>()
                        {
                            @Override
                            public void onResponse(Call<ActivityStats> call, Response<ActivityStats> response)
                            {
                                if (response != null && response.isSuccessful())
                                {
                                    ActivityStats stravaresponse = response.body();
                                    if(stravaresponse !=null)
                                    {

                                        //Get all-time total run miles
                                        Double TotalRunMeters = stravaresponse.getAll_run_totals_distance();
                                        //convert meters into miles
                                        Double TotalRunMiles = TotalRunMeters*0.000621371;

                                        //Turn to  2 decimal places
                                        DecimalFormat df = new DecimalFormat("##.##");
                                        String RoundedTotalMiles = String.valueOf(df.format(TotalRunMiles));


                                        stravaTotalRunMiles.setText("All-time Run distance: "+RoundedTotalMiles+"Miles");

                                        //Get all-time total ride miles
                                        Double TotalRideMeters = stravaresponse.getAll_ride_totals_distance();
                                        //convert meters into miles
                                        Double TotalRideMiles = TotalRideMeters*0.000621371;

                                        //Turn to  2 decimal places
                                        String RoundedTotaRidelMiles = String.valueOf(df.format(TotalRideMiles));

                                        stravaTotalRideMiles.setText("All-time Ride distance: "+RoundedTotaRidelMiles+"Miles");

                                        //Get recent run meters
                                        Double RecentRunMeters = stravaresponse.getRecent_run_totals_distance();
                                        //Convert meters into miles
                                        Double RecentRunMiles = RecentRunMeters*0.000621371;

                                        //Turn to 2 decimal places
                                        String RoundedRecentRunMiles = String.valueOf(df.format(RecentRunMiles));

                                        stravaRecentRunMiles.setText("Recent Run Distance:  "+RoundedRecentRunMiles+"Miles");

                                        //Get recent ride total meters
                                        Double RecentRideMeters = stravaresponse.getRecent_ride_totals_distance();
                                        //Convert meters into miles
                                        Double RecentRideMiles = RecentRideMeters*0.000621371;

                                        //Turn to 2 decimal places
                                        String RoundedRecentRideMiles = String.valueOf(df.format(RecentRideMiles));
                                        stravaRecentRideMiles.setText("Recent Ride Distance: "+RoundedRecentRideMiles+"Miles");

                                        //sets up the pie chart with the data
                                        int totalruns = stravaresponse.getAll_run_totals_count();
                                        int totalrides = stravaresponse.getAll_ride_totals_count();
                                        int totalswims = stravaresponse.getAll_swim_totals_count();

                                        //sets api the data as variables
                                        countstats.add(new ValueDataEntry("Total Runs",totalruns));
                                        countstats.add(new ValueDataEntry("Total Rides",totalrides));
                                        countstats.add(new ValueDataEntry("Total Swims",totalswims));

                                        //set piechart data
                                        pie.data(countstats);

                                        //customise pie chart
                                        pie.labels().position("inside");
                                        pie.legend().title().enabled(false);
                                        pie.legend()
                                                .position("inside")
                                                .fontSize(8)
                                                .iconSize(8)
                                                .itemsLayout(LegendLayout.HORIZONTAL)
                                                .align(Align.CENTER);
                                        pie.legend().title().enabled(false);


                                        //set piechart
                                        totalStatsChart.setChart(pie);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ActivityStats> call, Throwable t) {

                            }

                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Athlete> call, Throwable t)
            {

            }
        });

        stravaUsername = findViewById(R.id.tvUsername);
        stravaImage = findViewById(R.id.ivStravaImage);

        stravaTotalRunMiles = findViewById(R.id.tvStravaTotalRunMiles);
        stravaTotalRideMiles = findViewById(R.id.tvStravaTotalRideMiles);
        stravaRecentRunMiles = findViewById(R.id.tvStravaRecentRunMiles);
        stravaRecentRideMiles = findViewById(R.id.tvStravaRecentRideMiles);

        totalStatsChart = findViewById(R.id.TotalStatsChart);

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
    }
}