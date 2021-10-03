package com.example.monthlychallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment chalListFragment;
    Fragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 로그인에서 id가지고 넘어옴
        Intent mIntent = getIntent();
        String userID = mIntent.getStringExtra("userID");
        Log.e("tag", "userID 잘 넘어왔당");

        // Cal에서 cnt가지고 넘어옴
        Intent from_cal_Intent = getIntent();
        String cnt = mIntent.getStringExtra("cnt");
        Log.e("tag", "cnt 두 잘 넘어왔당");

        chalListFragment = new ChalListFragment();
        homeFragment = new HomeFragment();

        //기본 시작 화면
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).commit();
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navi);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.List_bnt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, chalListFragment).commit();
                        return true;
                    case R.id.challenge_btn:
                        Intent cal_intent = new Intent(MainActivity.this, CalActivity.class);
                        cal_intent.putExtra("userID", userID);
                        startActivity(cal_intent);
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