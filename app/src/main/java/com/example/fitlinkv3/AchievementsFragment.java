package com.example.fitlinkv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.fitlinkv3.retrofit.ActivityStats;
import com.example.fitlinkv3.retrofit.Athlete;
import com.example.fitlinkv3.retrofit.ServiceGenerator;
import com.samsandberg.stravaauthenticator.StravaAuthenticateActivity;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AchievementsFragment extends Fragment {

    //ID setup for athlete info
    public int stravaId;

    //ImageViews for icon setting
    private ImageButton TotalActivity;
    private ImageButton TotalRunDistance;
    private ImageButton TotalRideDistance;
    private ImageButton TotalRideTime;
    private ImageButton TotalRunTime;
    private ImageButton TotalRunElevation;
    private ImageButton TotalRideElevation;

    //TextViews for achievement progress
    private TextView TotalActivityProgress;
    private TextView RunDistanceProgress;
    private TextView RideDistanceProgress;
    private TextView RunTimeProgress;
    private TextView RideTimeProgress;
    private TextView RunElevationProgress;
    private TextView RideElevationProgress;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AchievementsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //get access token for API calls
        String accessToken = StravaAuthenticateActivity.getStravaAccessToken(getContext());
        ServiceGenerator.getEndPointInterface().getAthlete("Bearer "+accessToken).enqueue(new Callback<Athlete>()
        {
            @Override
            public void onResponse(Call<Athlete> call, Response<Athlete> response)
            {
                if (response != null && response.isSuccessful())
                {
                    Athlete stravaresponse = response.body();
                    if(stravaresponse !=null)
                    {
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
                                    if(stravaresponse !=null) {

                                        //Get TOTAL ACTIVITY COUNT for achievement
                                        int TotalRunCount = stravaresponse.getAll_run_totals_count();
                                        int TotalRideCount = stravaresponse.getAll_ride_totals_count();
                                        int TotalSwimCount = stravaresponse.getAll_swim_totals_count();

                                        int TotalActivityCount = TotalRunCount + TotalRideCount + TotalSwimCount;

                                        //put if statement here\\
                                        if (TotalActivityCount >= 10) {
                                            //set image to athlete
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_athlete));
                                            TotalActivityProgress.setText("You have reached athlete status with: "+TotalActivityCount+" total activities! Well done!");
                                        } else if (TotalActivityCount >= 6) {
                                            //set image to pro
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_pro));
                                            TotalActivityProgress.setText("You need "+(10-TotalActivityCount)+" activities left to record to reach the next medal!");
                                        }
                                        //set image to novice
                                        else if (TotalActivityCount <= 5) {
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_novice));
                                            TotalActivityProgress.setText("You need "+(6-TotalActivityCount)+" activities left to record to reach the next medal!");
                                        }

                                            //Get RUN distance for achievement
                                            Double TotalRunMeters = stravaresponse.getAll_run_totals_distance();
                                            Double TotalRunMiles = TotalRunMeters * 0.000621371;
                                            Double RunTotalDistance = TotalRunMiles;

                                            DecimalFormat df = new DecimalFormat("##.##");
                                            Double RoundedRunDistance = Double.valueOf(df.format(RunTotalDistance));

                                            if (RoundedRunDistance >= 50){
                                                //set image to gold
                                                TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_run_distance));
                                                RunDistanceProgress.setText("You have achieved gold with: "+RoundedRunDistance+" miles ran! Well Done!");
                                            }
                                            else if(RoundedRunDistance >=20){
                                                //set image to silver
                                                TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run_distance));
                                                RunDistanceProgress.setText("You are "+(50-RoundedRunDistance)+" miles away from gold, keep going!");
                                            }
                                            else if(RoundedRunDistance >=10){
                                                //set image to bronze
                                                TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run_distance));
                                                RunDistanceProgress.setText("You are "+(20-RoundedRunDistance)+" miles away from gold, keep going!");

                                            }

                                                // Get RIDE distance for achievement
                                                Double TotalRideMeters = stravaresponse.getAll_ride_totals_distance();
                                                Double TotalRideMiles = TotalRideMeters * 0.000621371;
                                                Double TotalRideMilesDistance = TotalRideMiles;
                                                //Make TotalDistance to 2 decimal places
                                                Double RoundedRideDistance = Double.valueOf(df.format(TotalRideMilesDistance));

                                                if (RoundedRideDistance >= 15){
                                                    //set image to gold
                                                    TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_ride_distance));
                                                    RideDistanceProgress.setText("You have achieved gold with: "+RoundedRideDistance+" miles of cycling! Be proud!");

                                                }
                                                else if(RoundedRideDistance >=10) {
                                                    //set image to silver
                                                    TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_ride_distance));
                                                    RideDistanceProgress.setText("You are "+(15-RoundedRideDistance)+" miles away from gold, keep going!");

                                                }
                                                else if(RoundedRideDistance >=5) {
                                                    //set image to bronze
                                                    TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_ride_distance));
                                                    RideDistanceProgress.setText("You are "+(10-RoundedRideDistance)+" miles away from gold, keep going!");
                                                }


                                            //Get TOTAL RUN TIME for achievement
                                            int TotalRunSeconds = stravaresponse.getAll_run_totals_time();
                                            int TotalRunMinutes = TotalRunSeconds/60;
                                            int TotalRunHours = TotalRunMinutes/60;

                                            //Make TotalRunTime to 2 decimal places
                                            Double RoundedRunTime = Double.valueOf(df.format(TotalRunHours));

                                            //put if statement here\\
                                            if (RoundedRunTime >= 10) {
                                            //set image to gold
                                            TotalRunTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_run_time));
                                                RunTimeProgress.setText("You have achieved gold with: "+RoundedRunTime+" hours recorded for runs! Well done!");
                                            }
                                            else if (RoundedRunTime >= 6) {
                                            //set silver
                                            TotalRunTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run_time));
                                                RunTimeProgress.setText("You are "+(10-RoundedRunTime)+" hours from gold, Keep going!");
                                            }
                                            //set bronze
                                            else if (RoundedRunTime == 1) {
                                            TotalRunTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run_time));
                                                RunTimeProgress.setText("You are "+(6-RoundedRunTime)+" hours away from silver ");
                                            }

                                            //Get TOTAL RIDE TIME for achievement
                                            int TotalRideSeconds = stravaresponse.getAll_ride_totals_time();
                                            int TotalRideMinutes = TotalRideSeconds/60;
                                            int TotalRideHours = TotalRideMinutes/60;

                                            //Make TotalDistance to 2 decimal places
                                            Double RoundedRideTime = Double.valueOf(df.format(TotalRideHours));

                                            //put if statement here\\
                                            if (RoundedRideTime >= 10) {
                                            //set image to gold
                                            TotalRideTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_ride_time));
                                                RideTimeProgress.setText("You have achieved gold with: "+RoundedRideTime+" hours recorded for cycling! Well done!");

                                            }
                                            else if (RoundedRideTime >= 6) {
                                            //set silver
                                            TotalRideTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_ride_time));
                                                RideTimeProgress.setText("You are "+(10-RoundedRideTime)+" hours from gold, Keep going!");

                                            }
                                            //set bronze
                                            else if (RoundedRideTime == 1) {
                                            TotalRideTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_ride_time));
                                                RideTimeProgress.setText("You are "+(6-RoundedRideTime)+" hours away from silver ");
                                            }

                                            //get TOTAL RUN ELEVATION
                                            Double TotalRunElevationMeters = stravaresponse.getAll_run_totals_elevation();

                                            //Round to 2 dp
                                            Double RoundedRunElevation = Double.valueOf(df.format(TotalRunElevationMeters));

                                            if (RoundedRunElevation >= 10) {
                                            //set image to gold
                                            TotalRunElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_run_elevation));
                                                RunElevationProgress.setText("You have achieved gold with "+RoundedRunElevation+" meters. Well done!");
                                             }
                                            else if (RoundedRunElevation >= 6) {
                                            //set silver
                                            TotalRunElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run_elevation));
                                                RunElevationProgress.setText("You are "+(10-RoundedRunElevation)+" meters away from gold!");

                                            }
                                            //set bronze
                                            else if (RoundedRunElevation <= 1) {
                                            TotalRunElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run_elevation));
                                                RunElevationProgress.setText("You are "+(6-RoundedRunElevation)+" meters away from silver!");
                                            }

                                            //get TOTAL RIDE ELEVATION
                                            //get TOTAL RUN ELEVATION
                                            Double TotalRideElevationMeters = stravaresponse.getAll_ride_totals_elevation();

                                            //Round to 2 dp
                                            Double RoundedRideElevation = Double.valueOf(df.format(TotalRideElevationMeters));

                                            if (RoundedRideElevation >= 10) {
                                            //set image to gold
                                            TotalRideElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_ride_elevation));
                                                RideElevationProgress.setText("You have achieved gold with "+RoundedRideElevation+" meters. Well done!");
                                            }
                                            else if (RoundedRideElevation >= 6) {
                                            //set silver
                                            TotalRideElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_ride_elevation));
                                                RideElevationProgress.setText("You are "+(10-RoundedRideElevation)+" meters away from gold!");

                                            }
                                            //set bronze
                                            else if (RoundedRideElevation <= 1) {
                                            TotalRideElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_ride_elevation));
                                                RideElevationProgress.setText("You are "+(6-RoundedRideElevation)+" meters away from silver!");

                                            }

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
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);

        //Assigning ImageViews and toasts
        TotalActivity = view.findViewById(R.id.TotalActivity);
        TotalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Total Number of Recorded Activities Medal", Toast.LENGTH_SHORT).show();
            }
        });
        TotalRunDistance = view.findViewById(R.id.TotalRunDistance);
        TotalRunDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Total Run Distance Medal", Toast.LENGTH_SHORT).show();
            }
        });
        TotalRideDistance = view.findViewById(R.id.TotalRideDistance);
        TotalRideDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Total Ride Distance Medal", Toast.LENGTH_SHORT).show();
            }
        });
        TotalRunTime = view.findViewById(R.id.TotalRunTime);
        TotalRunTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Total Run Time Medal", Toast.LENGTH_SHORT).show();
            }
        });
        TotalRideTime = view.findViewById(R.id.TotalRideTime);
        TotalRideTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Total Ride Time Medal", Toast.LENGTH_SHORT).show();
            }
        });
        TotalRunElevation = view.findViewById(R.id.TotalRunElevation);
        TotalRunElevation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Total Run Elevation Medal", Toast.LENGTH_SHORT).show();
            }
        });
        TotalRideElevation = view.findViewById(R.id.TotalRideElevation);
        TotalRideElevation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Total Ride Elevation Medal", Toast.LENGTH_SHORT).show();
            }
        });

        //Assigning TextViews for medal progress
        TotalActivityProgress = view.findViewById(R.id.tvActivityTotal);
        RunDistanceProgress = view.findViewById(R.id.tvRunDistance);
        RideDistanceProgress = view.findViewById(R.id.tvRideDistance);
        RunTimeProgress = view.findViewById(R.id.tvRunTime);
        RideTimeProgress = view.findViewById(R.id.tvRideTime);
        RunElevationProgress = view.findViewById(R.id.tvRunElevation);
        RideElevationProgress = view.findViewById(R.id.tvRideElevation);
        return view;
    }
}