package com.example.fitlinkv3;

import androidx.appcompat.app.AppCompatActivity;

import com.samsandberg.stravaauthenticator.StravaAuthenticateActivity;
import com.samsandberg.stravaauthenticator.StravaScopes;

import android.content.Intent;
import android.os.Bundle;

import java.util.Arrays;
import java.util.Collection;

public class StravaAuth extends StravaAuthenticateActivity {

    //Strava authentication
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strava_auth);
    }

    /*****************************************
     * Methods override START
     */

    /**
     * Client ID
     */
    protected String getStravaClientId()
    {
        return "64178";
    }

    /**
     * Client Secret
     */
    protected String getStravaClientSecret()
    {
        return "52e2ac9830f751e83e2d9b92856e8119a82efda5";
    }

    /**
     * Scopes to auth for
     * (default public)
     */
    protected Collection<String> getStravaScopes() {
        return Arrays.asList("activity:read");
    }

    /**
     * Should we use the local cache?
     * (default true)
     */
    protected boolean getStravaUseCache()
    {
        return true;
    }

    /**
     * Should we check a token (against Strava's API) or should we just assume it's good?
     * (default true)
     */
    protected boolean getStravaCheckToken()
    {
        return true;
    }

    /**
     * What intent should we kick off, given OK auth
     */
    protected Intent getStravaActivityIntent()
    {
        return new Intent(this, MainActivity.class);
    }

    /**
     * Should we finish this activity after successful auth + kicking off next activity?
     * (default true)
     */
    protected boolean getStravaFinishOnComplete()
    {
        return true;
    }
}