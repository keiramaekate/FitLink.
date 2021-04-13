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
    private ImageButton TotalRunElevation;
    private ImageButton TotalRideElevation;


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
                                            //set image to gold
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_athlete));
                                        } else if (TotalActivityCount >= 6) {
                                            //set silver
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_pro));
                                        }
                                        //set bronze
                                        else if (TotalActivityCount < 5) {
                                            TotalActivity.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.total_activity_novice));
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
                                            }
                                            else if(RoundedRunDistance >=20){
                                                //set image to silver
                                                TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run_distance));
                                            }
                                            else if(RoundedRunDistance >=10){
                                                //set image to bronze
                                                TotalRunDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run_distance));
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
                                                }
                                                else if(RoundedRideDistance >=10) {
                                                    //set image to silver
                                                    TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_ride_distance));
                                                }
                                                else if(RoundedRideDistance >=5) {
                                                    //set image to bronze
                                                    TotalRideDistance.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_ride_distance));
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
                                            }
                                            else if (RoundedRunTime >= 6) {
                                            //set silver
                                            TotalRunTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run_time));
                                            }
                                            //set bronze
                                            else if (RoundedRunTime <= 1) {
                                            TotalRunTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run_time));
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
                                            }
                                            else if (RoundedRideTime >= 6) {
                                            //set silver
                                            TotalRideTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_ride_time));
                                            }
                                            //set bronze
                                            else if (RoundedRideTime <= 1) {
                                            TotalRideTime.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_ride_time));
                                            }

                                            //get TOTAL RUN ELEVATION
                                            Double TotalRunElevationMeters = stravaresponse.getAll_run_totals_elevation();

                                            //Round to 2 dp
                                            Double RoundedRunElevation = Double.valueOf(df.format(TotalRunElevationMeters));

                                            if (RoundedRunElevation >= 10) {
                                            //set image to gold
                                            TotalRunElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_run_elevation));
                                             }
                                            else if (RoundedRunElevation >= 6) {
                                            //set silver
                                            TotalRunElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run_elevation));
                                            }
                                            //set bronze
                                            else if (RoundedRunElevation <= 1) {
                                            TotalRunElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run_elevation));
                                            }

                                            //get TOTAL RIDE ELEVATION
                                            //get TOTAL RUN ELEVATION
                                            Double TotalRideElevationMeters = stravaresponse.getAll_ride_totals_elevation();

                                            //Round to 2 dp
                                            Double RoundedRideElevation = Double.valueOf(df.format(TotalRideElevationMeters));

                                            if (RoundedRideElevation >= 10) {
                                            //set image to gold
                                            TotalRideElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_ride_elevation));
                                            }
                                            else if (RoundedRideElevation >= 6) {
                                            //set silver
                                            TotalRideElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_ride_elevation));
                                            }
                                            //set bronze
                                            else if (RoundedRideElevation <= 1) {
                                            TotalRideElevation.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_ride_elevation));
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

        TotalActivity = view.findViewById(R.id.TotalActivity);

        TotalRunDistance = view.findViewById(R.id.TotalRunDistance);
        TotalRideDistance = view.findViewById(R.id.TotalRideDistance);

        TotalRunTime = view.findViewById(R.id.TotalRunTime);
        TotalRideTime = view.findViewById(R.id.TotalRideTime);

        TotalRunElevation = view.findViewById(R.id.TotalRunElevation);
        TotalRideElevation = view.findViewById(R.id.TotalRideElevation);
        return view;
    }
}