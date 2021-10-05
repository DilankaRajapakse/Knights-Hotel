package com.knights.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton roomBtn;
    ImageButton tableBtn;
    ImageButton deliBtn;
    ImageButton hallBtn;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            overridePendingTransition(0,0);
            startActivity(intent);
            overridePendingTransition(0,0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomBtn = findViewById(R.id.roomBtn);
        tableBtn = findViewById(R.id.tableBtn);
        deliBtn = findViewById(R.id.deliBtn);
        hallBtn = findViewById(R.id.hall);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);


    }

    @Override
    protected void onResume() {
        super.onResume();

        roomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI = new Intent(MainActivity.this, rooms.class);
                startActivity(myI);
            }
        });

        tableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI = new Intent(MainActivity.this, tables.class);
                startActivity(myI);
            }
        });
        deliBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI = new Intent(MainActivity.this, deliverylanding.class);
                startActivity(myI);
            }
        });

        hallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myI = new Intent(MainActivity.this, hall.class);
                startActivity(myI);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_phone:
                Intent intent = new Intent(MainActivity.this, Contact_us.class);
                startActivity(intent);
                break;
            case R.id.nav_aboutus:
                Intent intentB = new Intent(MainActivity.this, About_us.class);
                startActivity(intentB);
                break;
            case R.id.nav_login:
                Intent intentC = new Intent(MainActivity.this, Admin_Login.class);
                startActivity(intentC);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}