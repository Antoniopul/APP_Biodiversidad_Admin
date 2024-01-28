package com.example.app_biodiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,10000){
            public void onTick(long millisUntilFinished){
            }
            public void onFinish(){
                Intent intent=new Intent(MainActivity.this, Administrador_LogIn.class);
                startActivity(intent);
            }
        }.start();
    }
}