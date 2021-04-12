package com.example.fitlinkv3.retrofit;

//ActivityStats variables taken from Strava API (removed variables we wont use)
public class ActivityStats
{
    //The longest distance ridden by the athlete.
    private Double biggest_ride_distance;

    //The highest climb ridden by the athlete.
    private Double biggest_climb_elevation_gain;

    //The recent (last 4 weeks) swim stats for the athlete.
    private RideTotals recent_swim_totals;

    //The recent (last 4 weeks) ride stats for the athlete.
    private RideTotals recent_ride_totals;

    //The recent (last 4 weeks) run stats for the athlete.
    private RideTotals recent_run_totals;

    //The all time swim stats for the athlete.
    private RideTotals all_swim_totals;

    //The all time ride stats for the athlete.
    private RideTotals all_ride_totals;

    //The all time run stats for the athlete.
    private RideTotals all_run_totals;


    //functions to call specific user statistics
    public Double getBiggest_ride_distance() {return biggest_ride_distance;}

    public Double getBiggest_climb_elevation_gain(){return biggest_climb_elevation_gain;}

    public Object getRecent_ride_totals(){return recent_ride_totals;}

    public Double getRecent_ride_total_distance(){return recent_ride_totals.distance;}

    public Object getRecent_run_totals(){return recent_run_totals;}

    public Object getAll_ride_totals(){return all_ride_totals;}

    public Object getAll_run_totals(){return all_run_totals;}

    //returns for all-time distance

    public Double getAll_run_totals_distance(){return all_run_totals.distance;}

    public Double getAll_ride_totals_distance(){return all_ride_totals.distance;}

    //returns for recent distance

    public Double getRecent_run_totals_distance(){return recent_run_totals.distance;}

    public Double getRecent_ride_totals_distance(){return recent_ride_totals.distance;}

    //returns for all-time count

    public Integer getAll_run_totals_count(){return all_run_totals.count;}

    public Integer getAll_ride_totals_count(){return all_ride_totals.count;}

    public Integer getAll_Swim_totals_count(){return all_swim_totals.count;}

    //RideTotals class

    public class RideTotals
    {
        private int count;
        private double distance;
        private int moving_time;
        private int elapsed_time;
        private double elevation_gain;
        private int achievement_count;
    }
}

