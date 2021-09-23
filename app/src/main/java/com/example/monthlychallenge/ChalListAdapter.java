package com.example.monthlychallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChalListAdapter extends RecyclerView.Adapter<ChalListAdapter.ChalListViewHolder> {

    private ArrayList<Challenge> arrayList;
    private Context context;

    public ChalListAdapter(ArrayList<Challenge> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChalListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 실제 리스트뷰가 어댑터에 연결된 후 뷰 홀더 최초로 만들어냄
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chal_item, parent, false);

       ChalListViewHolder holder = new ChalListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChalListViewHolder holder, int position) {
        // 각 아이템에 대한 실제 매칭 시켜주는 역할
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getSuccess())
                .into(holder.iv_success); //이미지 받아오기
        holder.tv_period.setText(arrayList.get(position).getPeriod());
        holder.tv_count.setText(arrayList.get(position).getCount());
        holder.tv_item.setText(arrayList.get(position).getItem());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class ChalListViewHolder extends RecyclerView.ViewHolder {
        //chal_item에서 만든거 불러오기
        ImageView iv_success;
        TextView tv_period;
        TextView tv_count;
        TextView tv_item;

        public ChalListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_success = itemView.findViewById(R.id.iv_success);
            this.tv_count = itemView.findViewById(R.id.tv_count);
            this.tv_period = itemView.findViewById(R.id.tv_period);
            this.tv_item = itemView.findViewById(R.id.tv_item);
        }
    }
}
