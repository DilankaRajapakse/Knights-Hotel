package com.knights.hotelmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HallRecyclerViewAdapter extends RecyclerView.Adapter {

    List<hall_model> hallDataList;

    public HallRecyclerViewAdapter(List<hall_model> hallDataList) {
        this.hallDataList = hallDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hallbooking_item_layout, parent, false);

        return new HallRecyclerViewAdapter.HallViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HallRecyclerViewAdapter.HallViewHolderClass viewHolderClass = (HallRecyclerViewAdapter.HallViewHolderClass)holder;
        hall_model hallModel = hallDataList.get(position);
        viewHolderClass.fullname.setText(hallModel.getFullName());
        viewHolderClass.hallEmail.setText(hallModel.getEmail());
        viewHolderClass.hallphone.setText(hallModel.getPhone());
        viewHolderClass.hallDate.setText(hallModel.getDate());
        viewHolderClass.hallTime.setText(hallModel.getTime());
        viewHolderClass.hallnoOfHours.setText(String.valueOf(hallModel.getNoOfHours()));
        viewHolderClass.halltype.setText(hallModel.getHallType());
        viewHolderClass.hallnoOfPeople.setText(hallModel.getNumpeople());
        viewHolderClass.halltotalPrice.setText(String.valueOf(hallModel.getTotal()));

    }

    @Override
    public int getItemCount() {
        return hallDataList.size();
    }

    public static class HallViewHolderClass extends RecyclerView.ViewHolder{
        TextView fullname, hallEmail, halltype, hallphone, hallDate, hallTime, hallnoOfHours, hallnoOfPeople, halltotalPrice;

        public HallViewHolderClass(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.hallfullName);
            hallEmail = itemView.findViewById(R.id.hallemail);
            hallphone = itemView.findViewById(R.id.hallphone);
            hallDate = itemView.findViewById(R.id.hallDate);
            hallTime = itemView.findViewById(R.id.halltime);
            hallnoOfHours = itemView.findViewById(R.id.hallnoOfHours);
            halltype = itemView.findViewById(R.id.hallType);
            hallnoOfPeople = itemView.findViewById(R.id.hallnoOfPeople);
            halltotalPrice = itemView.findViewById(R.id.halltotal);


        }
    }
}
