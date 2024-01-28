package com.example.proyecto_usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_usuario.adapter.TripsAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = findViewById(R.id.mainLayaut);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.desp_arriba);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.desp_abajo);

        TextView txtv_bienve = findViewById(R.id.textBien);
        ImageView logoImageV = findViewById(R.id.logoBio);

        txtv_bienve.setAnimation(animation2);
        logoImageV.setAnimation(animation1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ZonasActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}