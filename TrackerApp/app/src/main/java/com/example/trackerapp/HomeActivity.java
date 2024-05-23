package com.example.trackerapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView btmView;

    FrameLayout layout_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhome);

        layout_one = findViewById(R.id.framelayout_one);
        btmView = findViewById(R.id.btm_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_one, new Home()).commit();

        btmView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_one, new Home()).commit();
                } else if (menuItem.getItemId() == R.id.claim) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_one, new Form()).commit();
                } else if (menuItem.getItemId() == R.id.profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_one, new Profile()).commit();
                }
                return true;
            }
        });


    }
}
