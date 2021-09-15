package com.example.monthlychallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment chalListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chalListFragment = new ChalListFragment();

        //기본 시작 화면 getSupportFragmentManager().beginTransaction().replace(R.id.container, chal).commit();
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navi);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.List_bnt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, chalListFragment).commit();
                        return true;
                    case R.id.challenge_btn:
                        return true;
                    case R.id.Home_btn:
                        return true;
                }
                return false;
            }
        });
    }
}