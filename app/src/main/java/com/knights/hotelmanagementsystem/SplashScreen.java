package com.knights.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView welcome;
    Animation textFade;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        welcome = findViewById(R.id.welcomeText);
        progressBar = findViewById(R.id.progressBar);

        textFade = AnimationUtils.loadAnimation(this, R.anim.logo_fade);



        Thread splash_screen = new Thread(){
            @Override
            public void run() {
                try{
                    welcome.setAnimation(textFade);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setAnimation(textFade);
                    sleep(5000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };
        splash_screen.start();
    }
}