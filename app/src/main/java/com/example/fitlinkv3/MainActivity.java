package com.example.fitlinkv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsandberg.stravaauthenticator.StravaAuthenticateActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String accessToken = StravaAuthenticateActivity.getStravaAccessToken(this);
        Toast.makeText(this, accessToken , Toast.LENGTH_SHORT).show();

        startActivity(new Intent(getApplicationContext(), ExplorePage.class));
        finish();
    }
}