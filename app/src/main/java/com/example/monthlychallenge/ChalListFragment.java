package com.example.monthlychallenge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChalListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Challenge> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    Button btn_addList;
    Spinner sp_addList;
    EditText ed_addList;

    Context ct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chal_list, container, false);
        ct = container.getContext();

        recyclerView = view.findViewById(R.id.recyclerView); //아이디 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존 성능 강화
        layoutManager = new LinearLayoutManager(ct);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //Challenge 담을 어레이 리스트 (어댑터 쪽으로 날림)

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("challengeList"); //디비 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스의 데이터 받아오는 곳
                arrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Challenge challenge = snapshot.getValue(Challenge.class); //Challenge 객체에 데이터 담기
                    arrayList.add(challenge);
                }
                adapter.notifyDataSetChanged(); //리스트 저장 새로고침
            }
            // 파이어베이스 데이터를 Challenge 클래스에 넣어주고 이를 arrayList에 넣어 Adapter에 쏘는 로직

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비 가져오던중 에러 발생시 동작
                Log.e("ChalListFragment", String.valueOf(error.toException()));
            }
        });

        adapter = new ChalListAdapter(arrayList, ct);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결



        // dialog
        btn_addList = view.findViewById(R.id.addList);

        btn_addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(ct);
                View dialogView = getLayoutInflater().inflate(R.layout.add_list_dialog,null);

                ad.setTitle("Challenge");

                Spinner sp = (Spinner) dialogView.findViewById(R.id.addListSp);
                EditText edCount = (EditText) dialogView.findViewById(R.id.addLIstCount);
                EditText edItem = (EditText) dialogView.findViewById(R.id.addLIstItem);

                ArrayAdapter<CharSequence> ad_monthly = ArrayAdapter.createFromResource(getActivity(), R.array.monthly_array, android.R.layout.simple_spinner_item);
                ad_monthly.setDropDownViewResource(android.R.layout.simple_spinner_item);
                sp.setAdapter(ad_monthly);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String ed_itemVal = edItem.getText().toString();
                        String ed_countVal = edCount.getText().toString();
                        String sp_val = sp.getSelectedItem().toString();

                        //firebase로 값 넘기기
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                // 창띄우기
                ad.setView(dialogView);
                ad.create();
                ad.show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


}