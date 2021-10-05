package com.knights.hotelmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeliveryRecyclerViewAdapter extends RecyclerView.Adapter {

    List<delivery_model> deliveryDataList;

    public DeliveryRecyclerViewAdapter(List<delivery_model> deliveryDataList) {
        this.deliveryDataList = deliveryDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item_layout, parent, false);

        return new DeliveryRecyclerViewAdapter.DeliveryViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DeliveryRecyclerViewAdapter.DeliveryViewHolderClass viewHolderClass = (DeliveryRecyclerViewAdapter.DeliveryViewHolderClass)holder;
        delivery_model deliveryModel = deliveryDataList.get(position);
        viewHolderClass.fullname.setText(deliveryModel.getName());
        viewHolderClass.delivEmail.setText(deliveryModel.getEmail());
        viewHolderClass.delivphone.setText(deliveryModel.getPhone());
        viewHolderClass.delivFoodtype.setText(deliveryModel.getFooditm());
        viewHolderClass.delivquantity.setText(deliveryModel.getQuantity());
        viewHolderClass.delivaddress.setText(String.valueOf(deliveryModel.getAddress()));
        viewHolderClass.delivcity.setText(deliveryModel.getCity());
        viewHolderClass.delvTotal.setText(String.valueOf(deliveryModel.getTotalprice()));


    }

    @Override
    public int getItemCount() {
        return deliveryDataList.size();
    }

    public static class DeliveryViewHolderClass extends RecyclerView.ViewHolder{
        TextView fullname, delivEmail, delivphone, delivFoodtype, delivquantity, delivaddress, delivcity, delvTotal;

        public DeliveryViewHolderClass(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.delvfullName);
            delivEmail = itemView.findViewById(R.id.delvemail);
            delivphone = itemView.findViewById(R.id.delvphone);
            delivFoodtype = itemView.findViewById(R.id.delvfoodItem);
            delivquantity = itemView.findViewById(R.id.delvquantity);
            delivaddress = itemView.findViewById(R.id.delvaddress);
            delivcity = itemView.findViewById(R.id.delvcity);
            delvTotal = itemView.findViewById(R.id.delvtotal);
        }
    }
}
