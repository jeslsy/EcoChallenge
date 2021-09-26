package com.example.monthlychallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ListView listView = findViewById(R.id.exList);

        List<String> list = new ArrayList<>();
        list.add("e-mail 10개씩 지우기");
        list.add("배달음식 안 먹는 반찬 받지 않기");
        list.add("배달음식 일회용품 받지 않기");
        list.add("식물성 음식 먹기");
        list.add("텀블러 사용하기");
        list.add("장볼 때 장바구니 사용하기");
        list.add("분리수거 잘 하기");
        list.add("1회용 컵 대신 유리컵 사용하기");
        list.add("가전제품 및 전기용품 미사용시 코드 빼기");
        list.add("빗물 받아서 사용하기");
        list.add("충전식 배터리 사용하기");
        list.add("플라스틱 빨대 사용하지 않기");
        list.add("실내에 화초 놓기");
        list.add("샤워 시간 줄이기");
        list.add("낮에는 전등보단 자연광 이용하기");
        list.add("건조기 대신 건조대에 걸어서 말리기");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}