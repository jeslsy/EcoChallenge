package com.example.monthlychallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment chalListFragment;
    Fragment homeFragment;
    Fragment chalFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent mIntent = getIntent();
        mIntent.getStringExtra("email");
        Log.e("tag", "id 잘 넘어왔당");

        chalListFragment = new ChalListFragment();
        homeFragment = new HomeFragment();
        //chalFragment = new chalFragment();

        //기본 시작 화면
        getSupportFragmentManager().beginTransaction().replace(R.id.container, chalFragment).commit();
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navi);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.List_bnt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, chalListFragment).commit();
                        return true;
                    case R.id.challenge_btn:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container, chalFragment).commit();
                        return true;
                    case R.id.Home_btn:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}