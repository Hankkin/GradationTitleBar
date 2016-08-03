package com.hankkin.gradationtitlebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button btnQQ;
    Button btnBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnQQ = (Button) findViewById(R.id.btn_qq);
        btnBanner = (Button) findViewById(R.id.btn_banner);

        btnQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QQSpeakActivity.class);
                startActivity(intent);
            }
        });

        btnBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BannerActivity.class);
                startActivity(intent);
            }
        });

    }

}
