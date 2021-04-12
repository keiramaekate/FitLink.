package com.example.fitlinkv3;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private ImageView TotalRun;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AchievementsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AchievementsFragment newInstance(String param1, String param2) {
        AchievementsFragment fragment = new AchievementsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

                                        //Get total counts for achievement
                                        int TotalRunCount = stravaresponse.getAll_run_totals_count();
                                        int TotalRideCount = stravaresponse.getAll_ride_totals_count();
                                        int TotalSwimCount = stravaresponse.getAll_swim_totals_count();

                                        int TotalActivityCount = TotalRunCount+TotalRideCount+TotalSwimCount;

                                        //put if statement here\\
                                        if (TotalActivityCount >= 10 )
                                        {
                                            //set image to gold
                                            TotalRun.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.gold_run));
                                            Toast.makeText(getContext(), "gold", Toast.LENGTH_LONG).show();
                                        }
                                            else  if (TotalActivityCount >= 6 ){
                                            //set silver
                                            TotalRun.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.silver_run));
                                            Toast.makeText(getContext(), "silverrun", Toast.LENGTH_LONG).show();
                                        }
                                            //set bronze
                                            else if (TotalActivityCount < 5 ){
                                            TotalRun.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.bronze_run));
                                            Toast.makeText(getContext(), "bronze", Toast.LENGTH_LONG).show();
                                        }

                                        //Get total distance for achievement
                                        Double TotalRunMeters = stravaresponse.getAll_run_totals_distance();
                                        Double TotalRideMeters = stravaresponse.getAll_ride_totals_distance();

                                        //Convert distance into miles
                                        Double TotalRunMiles = TotalRunMeters*0.000621371;
                                        Double TotalRideMiles = TotalRideMeters*0.000621371;

                                        Double TotalDistance = TotalRunMiles+TotalRideMiles;

                                        //Make TotalDistance to 2 decimal places
                                        DecimalFormat df = new DecimalFormat("##.##");
                                        String RoundedTotalDistance = String.valueOf(df.format(TotalDistance));

                                        //put if statement here (use RoundedTotalDistance for if statement)\\


                                        //Get all-time time_elapsed
                                        int TotalRunTime = stravaresponse.getAll_run_totals_time();
                                        int TotalRideTime = stravaresponse.getAll_ride_totals_time();

                                        int TotalTime = TotalRunTime+TotalRideTime;

                                        //put if statement here\\
                                        if (TotalTime > 60){

                                        }

                                        //Get all-time elevation_gain
                                        Double TotalRunElevationGain = stravaresponse.getAll_run_totals_elevation();
                                        Double TotalRideElevationGain = stravaresponse.getAll_ride_totals_elevation();

                                        Double TotalElevation = TotalRunElevationGain+TotalRideElevationGain;

                                        //put if statement here\\

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
        TotalRun = view.findViewById(R.id.TotalRun);
        return view;
    }
}