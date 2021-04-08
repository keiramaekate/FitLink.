package com.example.fitlinkv3.retrofit;

//DetailedAthlete variables taken from Strava API
public class Athlete {

    //The unique identifier of the athlete
    private Integer id;

    //Resource state, indicates level of detail. Possible values: 1 -> "meta", 2 -> "summary", 3 -> "detail"
    private Integer resourceState;

    //The athlete's first name.
    private String firstname;

    // The athlete's last name.
    private String lastname;

    //The athlete's username.
    private String username;

    //The athlete's city.
    private Object city;

    //The athlete's state or geographical region.
    private Object state;

    //The athlete's country.
    private Object country;

    //The athlete's sex. May take one of the following values: M, F
    private String sex;

    //Whether the athlete has any Summit subscription.
    private Boolean summit;

    //The time at which the athlete was created.
    private String createdAt;

    //The time at which the athlete was last updated.
    private String updatedAt;

    //The athlete's weight.
    private Float weight;

    //URL to a 62x62 pixel profile picture.
    private String profileMedium;

    //URL to a 124x124 pixel profile picture.
    private String profile;

    //The athlete's friend count.
    private Object friend;

    //The athlete's follower count.
    private Object follower;

    //functions to call specific user information
    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getResourceState() {
        return resourceState;
    }

    public void setResourceState(Integer resourceState) {
        this.resourceState = resourceState;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getSummit() {
        return summit;
    }

    public void setSummit(Boolean summit) {
        this.summit = summit;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getProfileMedium() {
        return profileMedium;
    }

    public void setProfileMedium(String profileMedium) {
        this.profileMedium = profileMedium;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Object getFriend() {
        return friend;
    }

    public void setFriend(Object friend) {
        this.friend = friend;
    }

    public Object getFollower() {
        return follower;
    }

    public void setFollower(Object follower) {
        this.follower = follower;
    }

}

