package com.example.fitlinkv3.retrofit;

//ActivityStats variables taken from Strava API
public class ActivityStats
{
    //The longest distance ridden by the athlete.
    private Double biggest_ride_distance;

    //The highest climb ridden by the athlete.
    private Double biggest_climb_elevation_gain;

    //The recent (last 4 weeks) ride stats for the athlete.
    private Integer recent_ride_totals;

    //The recent (last 4 weeks) run stats for the athlete.
    private Integer recent_run_totals;

    //The recent (last 4 weeks) swim stats for the athlete.
    private Integer recent_swim_totals;

    //The year to date ride stats for the athlete.
    private Integer ytd_ride_totals;

    //The year to date run stats for the athlete.
    private Integer ytd_run_totals;

    //The year to date swim stats for the athlete.
    private Integer ytd_swim_totals;

    //The all time ride stats for the athlete.
    private Integer all_ride_totals;

    //The all time run stats for the athlete.
    private Integer all_run_totals;

    //The all time swim stats for the athlete.
    private Integer all_swim_totals;

    //functions to call specific user statistics
    public Double getBiggest_ride_distance() {return biggest_ride_distance;}

    public Double getBiggest_climb_elevation_gain(){return biggest_climb_elevation_gain;}

    public Integer getRecent_ride_totals(){return recent_ride_totals;}

    public Integer getRecent_run_totals(){return recent_run_totals;}

    public Integer getRecent_swim_totals(){return recent_swim_totals;}

    public Integer getYtd_ride_totals(){return ytd_ride_totals;}

    public Integer getYtd_run_totals(){return ytd_run_totals;}

    public Integer getYtd_swim_totals(){return ytd_swim_totals;}

    public Integer getAll_ride_totals(){return all_ride_totals;}

    public Integer getAll_run_totals(){return all_run_totals;}

    public Integer getAll_swim_totals(){return all_swim_totals;}
}
