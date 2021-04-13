package com.example.fitlinkv3;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
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

    private ImageButton TotalActivity;
    private ImageButton TotalRunDistance;
    private ImageButton TotalRideDistance;
    private ImageButton TotalRideTime;
    private ImageButton TotalRunTime;


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
                                    if(stravaresponse !=null)
                                    {

                                        //Get TOTAL ACTIVITY COUNT for achievement
                                        int TotalRunCount = stravaresponse.getAll_run_totals_count();
                                        int TotalRideCount = stravaresponse.getAll_ride_totals_count();
                                        int TotalSwimCount = stravaresponse.getAll_swim_totals_count();

                                        int TotalActivityCount = TotalRunCount+TotalRideCount+TotalSwimCount;

                                        //put if statement here\\
                                        if (TotalActivityCount >= 10 )
                                        {
                                            //set image to gold
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_athlete));
                                            Toast.makeText(getContext(), "gold", Toast.LENGTH_LONG).show();
                                        }
                                            else  if (TotalActivityCount >= 6 ){
                                            //set silver
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_pro));
                                            Toast.makeText(getContext(), "silver", Toast.LENGTH_LONG).show();
                                        }
                                            //set bronze
                                            else if (TotalActivityCount < 5 ){
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_novice));
                                            Toast.makeText(getContext(), "bronze", Toast.LENGTH_LONG).show();
                                        }


                                        //Get RUN distance for achievement
                                        Double TotalRunMeters = stravaresponse.getAll_run_totals_distance();
                                        Double TotalRunMiles = TotalRunMeters*0.000621371;
                                        Double RunTotalDistance = TotalRunMiles;

                                        DecimalFormat df = new DecimalFormat("##.##");
                                        Double TotalRunDistance = Double.valueOf(df.format(RunTotalDistance));

                                        //if (TotalRunDistance >= 20) {
                                            //set image to gold
                                            //can't set image from string
                                            //TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_Run));
                                            //Toast.makeText(getContext(), "gold", Toast.LENGTH_LONG).show();
                                        }

                                        // Get RIDE distance for achievement
                                        Double TotalRideMeters = stravaresponse.getAll_ride_totals_distance();
                                        Double TotalRideMiles = TotalRideMeters*0.000621371;
                                        Double RideTotalDistance = TotalRideMiles;

                                        //Make TotalDistance to 2 decimal places
                                        DecimalFormat df = new DecimalFormat("##.##");
                                        String RoundedRideTotalDistance = String.valueOf(df.format(RideTotalDistance));

                                        //insert if statement here


                                        //Get all-time RUN time_elapsed
                                        int TotalRunTime = stravaresponse.getAll_run_totals_time();
                                        int RunTotalTime = TotalRunTime;

                                        //if statement to check run time totals more than or equal to 5 hours
                                        if (RunTotalTime >= 300){
                                            //set image to gold
                                            TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_run_time));
                                            Toast.makeText(getContext(), "gold", Toast.LENGTH_LONG).show();
                                        }

                                        //if statement to check run time totals more than or equal to than 3 hours
                                        else if (RunTotalTime >= 180){
                                            //set image to silver
                                            TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run_time));
                                            Toast.makeText(getContext(), "silver", Toast.LENGTH_LONG).show();
                                        }

                                        // if statement to check the run time totals is less than 2 hours
                                        else if (RunTotalTime < 120){
                                            //set image to bronze
                                            TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run_time));
                                            Toast.makeText(getContext(), "brozne", Toast.LENGTH_LONG).show();
                                        }

                                        //if statement to set image to default when run time total equals 0
                                        else if (RunTotalTime == 0){
                                            //set image to default if no data is retrieved 
                                            TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.default_medal));
                                            Toast.makeText(getContext(), "no data", Toast.LENGTH_LONG).show();
                                        }


                                        // Get all-time RIDE time-elapsed
                                        int TotalRideTime = stravaresponse.getAll_ride_totals_time();
                                        int RideTotalTime = TotalRideTime;

                                        //if statement to check RIDE time totals more than or equal to 5 hours
                                         if (RideTotalTime >= 300){
                                             //set image to gold
                                             TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_ride_time));
                                            Toast.makeText(getContext(), "gold", Toast.LENGTH_LONG).show();
                                         }

                                         //if statement to check RIDE time totals more than or equal to than 3 hours
                                         else if (RunTotalTime >= 180){
                                             //set image to silver
                                            TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_ride_time));
                                            Toast.makeText(getContext(), "silver", Toast.LENGTH_LONG).show();
                                         }

                                         // if statement to check the RIDE time totals is less than 2 hours
                                         else if (RunTotalTime < 120){
                                            //set image to bronze
                                            TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_ride_time));
                                            Toast.makeText(getContext(), "bronze", Toast.LENGTH_LONG).show();
                                         }

                                         //if statement to set image to default when RIDE time total equals 0
                                        else if (RunTotalTime == 0){
                                            //set image to default if no data is retrieved
                                             TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.default_medal));
                                            Toast.makeText(getContext(), "no data", Toast.LENGTH_LONG).show();
                                        }


                                        //Get all-time Run elevation_gain
                                        Double TotalRunElevationGain = stravaresponse.getAll_run_totals_elevation();
                                        Double RunTotalElevation = TotalRunElevationGain;

                                        //if (RunTotalElevation >= 10.00){
                                            //TotalRunElevationGain.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.default_medal));




                                        // Get all-time Ride elevation-gain
                                        Double TotalRideElevationGain = stravaresponse.getAll_ride_totals_elevation();
                                        Double RideTotalElevation = TotalRideElevationGain;

                                        //put if statement here\\

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
        TotalActivity = view.findViewById(R.id.TotalActivity);
        TotalRunDistance = view.findViewById(R.id.TotalRunDistance);
        TotalRideDistance = view.findViewById(R.id.TotalRideDistance);
        TotalRunTime = view.findViewById(R.id.TotalRunTime);
        TotalRideTime = view.findViewById(R.id.TotalRideTime);
        return view;
    }
}