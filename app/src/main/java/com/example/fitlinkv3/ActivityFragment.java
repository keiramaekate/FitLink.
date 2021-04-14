package com.example.fitlinkv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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
import com.samsandberg.stravaauthenticator.StravaAuthenticateActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityFragment extends Fragment {

    //AnyChart setup for recentstats
    AnyChartView recentStatsChart;

    //ID setup for athlete info
    public int stravaId;

    //TextViews for recent stats
    TextView RecentRunTime;
    TextView RecentRideTime;
    TextView RecentSwimTime;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        //set TextViews for RecentTimes
        RecentRunTime = view.findViewById(R.id.tvRecentRunTime);
        RecentRideTime = view.findViewById(R.id.tvRecentRideTime);
        RecentSwimTime = view.findViewById(R.id.tvRecentSwimTime);

        //set pie chart in xml
        recentStatsChart = view.findViewById(R.id.RecentStatsChart);
        //Create instance of piechart
        Pie pie = AnyChart.pie();
        //Arraylist for the piechart
        List<DataEntry> recentcountstats = new ArrayList<>();

        String accessToken = StravaAuthenticateActivity.getStravaAccessToken(getContext());
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
                                        //sets up the pie chart with the data
                                        int recentruns = stravaresponse.getRecent_run_totals_count();
                                        int recentrides = stravaresponse.getRecent_ride_totals_count();
                                        int recentswims = stravaresponse.getRecent_swim_totals_count();

                                        //sets api the data as variables
                                        recentcountstats.add(new ValueDataEntry("Recent Runs",recentruns));
                                        recentcountstats.add(new ValueDataEntry("Recent Rides",recentrides));
                                        recentcountstats.add(new ValueDataEntry("Recent Swims",recentswims));

                                        //set piechart data
                                        pie.data(recentcountstats);

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
                                        recentStatsChart.setChart(pie);

                                        //get recent run times
                                        int TotalRecentRunSeconds = stravaresponse.getRecent_run_totals_time();

                                        //convert seconds into hours, minutes and seconds
                                        //p1=seconds
                                        int p1 = TotalRecentRunSeconds % 60;
                                        //p2=hours
                                        int p2 = TotalRecentRunSeconds / 60;
                                        //p3 = minutes
                                        int p3 = p2 % 60;
                                        p2 = p2 / 60;

                                        if(TotalRecentRunSeconds==0) {
                                            RecentRunTime.setText("Record a run to see your statistics!");
                                        }
                                        else{RecentRunTime.setText("Recent Run Time: " + p2 + "Hour(s) " + p3 + "Minute(s) " + p1 + "Second(s)");}
                                        //get recent ride times
                                        int TotalRecentRideSeconds = stravaresponse.getRecent_ride_totals_time();

                                        //convert seconds into hours, minutes and seconds
                                        //p1=seconds
                                        int q1 = TotalRecentRideSeconds % 60;
                                        //p2=hours
                                        int q2 = TotalRecentRideSeconds / 60;
                                        //p3 = minutes
                                        int q3 = p2 % 60;
                                        q2 = q2 / 60;
                                        if(TotalRecentRideSeconds==0) {
                                            RecentRideTime.setText("Record a ride to see your statistics!");
                                        }
                                        else{RecentRideTime.setText("Recent Ride Time: " + q2 + "Hour(s) " + q3 + "Minute(s) " + q1 + "Second(s)");}

                                        //get recent ride times
                                        int TotalRecentSwimSeconds = stravaresponse.getRecent_swim_totals_time();

                                        //convert seconds into hours, minutes and seconds
                                        //p1=seconds
                                        int x1 = TotalRecentSwimSeconds % 60;
                                        //p2=hours
                                        int x2 = TotalRecentSwimSeconds / 60;
                                        //p3 = minutes
                                        int x3 = x2 % 60;
                                        x2 = x2 / 60;
                                        if(TotalRecentSwimSeconds==0) {
                                            RecentSwimTime.setText("Record a swim to see your statistics!");
                                        }
                                        else { RecentSwimTime.setText("Recent Swim Time: " + x2 + "Hour(s) " + x3 + "Minute(s) " + x1 + "Second(s)"); }

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

        // Inflate the layout for this fragment
        return view;

    }
}
