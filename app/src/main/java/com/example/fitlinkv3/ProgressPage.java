package com.example.fitlinkv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ProgressPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView leftIcon = findViewById(R.id.toolbar_icon);
        TextView title = findViewById(R.id.toolbar_title);

        // defining the pagerAdapter
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem progressTab = findViewById(R.id.progressTab);
        TabItem achievementsTab = findViewById(R.id.achiementsTab);
        ViewPager viewPager = findViewById(R.id.viewPager);

        // This will pass the data from the tabs and display them into viewpager
        PagerAdapter pagerAdapter = new
                PagerAdapter(getSupportFragmentManager(),
                    tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        // Switches tab views
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Assign and initialise variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        // Set screen that is selected
        bottomNavigationView.setSelectedItemId(R.id.Progress);
        // code to listen to what item is selected
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Explore:
                        startActivity(new Intent(getApplicationContext()
                                , ExplorePage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.Progress:
                        return true;

                    case R.id.Nutrition:
                        startActivity(new Intent(getApplicationContext()
                                , NutritionPage.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext()
                                , ProfilePage.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    //set menu to inflate
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // code to inflate the menu items
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Settings:
                Toast.makeText(getApplicationContext(), "You selected settings", Toast.LENGTH_LONG).show();
                Intent Settings = new Intent(this,Settings.class);
                this.startActivity(Settings);
                return true;
            case R.id.FAQs:
                Toast.makeText(getApplicationContext(), "You selected FAQs", Toast.LENGTH_LONG).show();
                Intent FAQs = new Intent(this,FAQs.class);
                this.startActivity(FAQs);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }};