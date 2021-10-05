package com.knights.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rooms extends AppCompatActivity {
    Button btnbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rooms);
        btnbook = findViewById(R.id.rbtn);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rooms.this, roombooking.class);
                startActivity(intent);
            }
        });
    }
}
