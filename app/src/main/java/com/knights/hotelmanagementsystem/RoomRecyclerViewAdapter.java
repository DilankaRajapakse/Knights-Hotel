package com.knights.hotelmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter {

    List<room_model> roomdataList;

    public RoomRecyclerViewAdapter(List<room_model> roomdataList) {
        this.roomdataList = roomdataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        room_model roomModel = roomdataList.get(position);
        viewHolderClass.checkIn.setText(roomModel.getCheckIn());
        viewHolderClass.checkOut.setText(roomModel.getCheckOut());
        viewHolderClass.roomType.setText(roomModel.getRoomlist());
        viewHolderClass.noOfRooms.setText(roomModel.getNumofRooms());
        viewHolderClass.noOfAdults.setText(roomModel.getNumofAdults());
        viewHolderClass.noOfChildren.setText(roomModel.getNumofChildren());
        viewHolderClass.fullName.setText(roomModel.getFullnin());
        viewHolderClass.email.setText(roomModel.getEmin());
        viewHolderClass.phone.setText(roomModel.getPhonein());
        viewHolderClass.total.setText(String.valueOf(roomModel.getTotal()));

    }

    @Override
    public int getItemCount() {
        return roomdataList.size();
    }

    public static class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView checkIn, checkOut, roomType, noOfRooms, noOfAdults, noOfChildren, fullName, email, phone, total;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            checkIn = itemView.findViewById(R.id.checkIn);
            checkOut = itemView.findViewById(R.id.checkOut);
            roomType = itemView.findViewById(R.id.roomType);
            noOfRooms = itemView.findViewById(R.id.noOfRooms);
            noOfAdults = itemView.findViewById(R.id.noOfAdults);
            noOfChildren = itemView.findViewById(R.id.noOfChildren);
            fullName = itemView.findViewById(R.id.fullName);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            total = itemView.findViewById(R.id.total);

        }
    }
}