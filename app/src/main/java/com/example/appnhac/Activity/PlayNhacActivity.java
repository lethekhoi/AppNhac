package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong, txttotaltimesong;
    SeekBar seekBarTime;
    ImageButton imgplay, imgrepeat, imgnext, imgpreview, imgsuffle;
    ViewPager viewPagerplaynhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        Intent intent = getIntent();
        if (intent.hasExtra("cakhuc")) {
            Baihat baihat = intent.getParcelableExtra("cakhuc");
            Toast.makeText(this, baihat.getTenBaiHat(), Toast.LENGTH_SHORT).show();

        }

        if (intent.hasExtra("cacbaihat")) {
            ArrayList<Baihat> mangbaihat = intent.getParcelableArrayListExtra("cacbaihat");

            for (int i = 0; i < mangbaihat.size(); i++) {
                Log.d("BBB", mangbaihat.get(i).getTenBaiHat());
            }
        }
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.txttimesong);
        txttotaltimesong = findViewById(R.id.txttotaltimesong);
        seekBarTime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imgbuttonplay);
        imgrepeat = findViewById(R.id.imgbuttonrepeat);
        imgnext = findViewById(R.id.imgbuttonnext);
        imgpreview = findViewById(R.id.imgbuttonpreview);
        imgsuffle = findViewById(R.id.imgbuttonsuffle);
        viewPagerplaynhac = findViewById(R.id.myViewPagerPlaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);

    }
}
