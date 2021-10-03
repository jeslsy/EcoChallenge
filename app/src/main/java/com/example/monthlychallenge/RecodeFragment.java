package com.example.monthlychallenge;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RecodeFragment extends Fragment {

    View view;

    public RecyclerView recode_view;
    public CalendarView calendarView;
    public Button del_Btn,save_Btn;
    public TextView diaryTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recode, container, false);

        calendarView=view.findViewById(R.id.cal_view);
        diaryTextView=view.findViewById(R.id.diaryTextView); // 날짜 얻어와서 출력력
        save_Btn=view.findViewById(R.id.save_btn);
        del_Btn=view.findViewById(R.id.del_btn);
        recode_view=view.findViewById(R.id.recode_view);
        diaryTextView.setText(String.format("< 날짜를 클릭하세요 >"));

        //로그인 및 회원가입 엑티비티에서 이름을 받아옴
        //Intent intent=getIntent();
        final String userID = "jeslsy@naver.com";
        //diaryTextView.setText(userID+"님의 달력 일기장");


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setText(String.format("< %d / %d / %d >",year,month+1,dayOfMonth));

                // db 기존 내용 불러오기
                try{


                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        //저장 버튼
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // edittext 작성 내용 db에 저장하기
                try{


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        del_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // db 내용 삭제하기
                try{


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}