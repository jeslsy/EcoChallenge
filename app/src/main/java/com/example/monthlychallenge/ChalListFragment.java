package com.example.monthlychallenge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChalListFragment extends Fragment {
    String TAG = "ChallListFragment";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Challenger> arrayList;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("challenger");

    Context ct = getActivity();

    Challenger user;
    String userId;
    String currentProgress;
    String myGoal;

    String im_inchal="https://firebasestorage.googleapis.com/v0/b/monthlychallenge-fb8a3.appspot.com/o/Inchallenge.png?alt=media&token=86bce698-036e-4089-b69c-696eaa1fc60c";
    String im_suc="https://firebasestorage.googleapis.com/v0/b/monthlychallenge-fb8a3.appspot.com/o/success.png?alt=media&token=3542eebb-3627-4eab-9843-774a0e4afb43";

    TextView tv_goal;
    TextView tv_currentProgress;

      // 사용자 챌린지 DB에 저장하기
//    private void writeChalList(){
//        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Challenger challenger = new Challenger(userId, currentProgress, myGoal, im_inchal);
//                databaseReference.setValue(challenger);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    // Challenger들 데이터 가져오기
    private void readChalList(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스의 데이터 받아오는 곳
                arrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Challenger challenger = snapshot.getValue(Challenger.class); //Challenger 객체에 데이터 담기
                    arrayList.add(challenger);

                    // TODO: 2021-09-26 사용자 데이터 따로 저장
//                    if(challenger.getId() == userId){
//                        user.setId(userId);
//                        user.setCount(challenger.getCount());
//                        user.setGoal(challenger.getGoal());
//                        user.setSuccess(challenger.getSuccess());
//                    }
                }
            }
            // 파이어베이스 데이터를 Challenge 클래스에 넣어주고 이를 arrayList에 넣어 Adapter에 쏘는 로직

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비 가져오던중 에러 발생시 동작
                Log.e("ChalListFragment", String.valueOf(error.toException()));
            }
        });

        Log.e(TAG, "읽었다");

        adapter = new ChalListAdapter(arrayList, ct);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
    }

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

        // 회원가입 후 사용자 데이터 받기 -> join에서 해버리는게 나을지도

        readChalList();

        // 사용자 데이터 저장
        // writeChalList();

        // 목표 성공시 이미지 변경
//        if(Integer.valueOf(myGoal) == Integer.valueOf(currentProgress)){
//            databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Challenger challenger = new Challenger(userId, currentProgress, myGoal, im_suc);
//                    databaseReference.setValue(challenger);
//                    user.setSuccess(im_suc);
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }

        // TODO: 2021-09-26 넘겨받은 아이디 목표 저장해주기 -> writeChalList







        // id, currentProgress 값 세팅해주기기
       tv_currentProgress = view.findViewById(R.id.currentProgress);
        tv_goal = view.findViewById(R.id.myGoal);

        tv_currentProgress.setText(currentProgress);
        tv_goal.setText(myGoal);




        adapter.notifyDataSetChanged(); //리스트 저장 새로고침
        
        // Inflate the layout for this fragment
        return view;
    }


}