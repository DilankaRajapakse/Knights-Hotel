package com.techMinions.hotelmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {
    TextView logout;
    Button rooms, tables, halls, delivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        logout = findViewById(R.id.logout);
        rooms = findViewById(R.id.rooms);
        tables = findViewById(R.id.tables);
        halls = findViewById(R.id.halls);
        delivery = findViewById(R.id.delivery);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboard.this);
                builder.setTitle("LOGGED OUT!");
                builder.setMessage("You have successfully logged out.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), Admin_Login.class);
                        startActivity(intent);
                    }
                });
                dialog = builder.create();
                dialog.show();

            }
        });

        rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminRooms.class);
                startActivity(intent);

            }
        });

        tables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), AdminTables.class);
                startActivity(intent2);

            }
        });

        halls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), AdminHalls.class);
                startActivity(intent3);
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(), AdminDelivery.class);
                startActivity(intent4);
            }
        });
    }
}