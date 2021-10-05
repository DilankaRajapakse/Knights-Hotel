package com.knights.hotelmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableRecyclerViewAdapter extends RecyclerView.Adapter {

    List<tables_model> tableDataList;

    public TableRecyclerViewAdapter(List<tables_model> tableDataList) {
        this.tableDataList = tableDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tablebooking_item_layout, parent, false);

        return new TableViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TableViewHolderClass viewHolderClass = (TableViewHolderClass)holder;
        tables_model tableModel = tableDataList.get(position);
        viewHolderClass.fullname.setText(String.format("%s%s", tableModel.getFname(), tableModel.getLname()));
        viewHolderClass.userEmail.setText(tableModel.getEmail());
        viewHolderClass.userphone.setText(tableModel.getPhone());
        viewHolderClass.pickDate.setText(tableModel.getDate());
        viewHolderClass.pickTime.setText(tableModel.getTime());
        viewHolderClass.noOfHours.setText(String.valueOf(tableModel.getNoOfHours()));
        viewHolderClass.comments.setText(tableModel.getComments());
        viewHolderClass.noOfPeople.setText(tableModel.getNoOfPeople());
        viewHolderClass.totalPrice.setText(String.valueOf(tableModel.getTotal()));

    }

    @Override
    public int getItemCount() {
        return tableDataList.size();
    }

    public static class TableViewHolderClass extends RecyclerView.ViewHolder{
        TextView fullname, userEmail, userphone, pickDate, pickTime, noOfHours, comments, noOfPeople, totalPrice;

        public TableViewHolderClass(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.TablefullName);
            userEmail = itemView.findViewById(R.id.tblemail);
            userphone = itemView.findViewById(R.id.tblphone);
            pickDate = itemView.findViewById(R.id.tbldate);
            pickTime = itemView.findViewById(R.id.tbltime);
            noOfHours = itemView.findViewById(R.id.tblnoOfHours);
            comments = itemView.findViewById(R.id.tblcomments);
            noOfPeople = itemView.findViewById(R.id.tblnoOfPeople);
            totalPrice = itemView.findViewById(R.id.tbltotal);


        }
    }
}
