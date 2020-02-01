package com.example.farmapp.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.farmapp.R;

public class SplaseActivity extends AppCompatActivity {
    RelativeLayout mainLayout;
    AnimationDrawable animationDrawable;
    Button btnLets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLets=findViewById(R.id.btnLets);

        mainLayout=findViewById(R.id.splaseMainLayout);
        animationDrawable= (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        btnLets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SplaseActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
        );



    }
}
