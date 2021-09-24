package com.example.monthlychallenge;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
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
    private ArrayList<Challenge> arrayList;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference("challengeList");

    Button btn_addList;

    Context ct = getActivity();

    private void readChalList(View view){

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

        Log.e(TAG, "읽었다리");

        adapter = new ChalListAdapter(arrayList, ct);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
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

        readChalList(view);

        // Inflate the layout for this fragment
        return view;
    }


}