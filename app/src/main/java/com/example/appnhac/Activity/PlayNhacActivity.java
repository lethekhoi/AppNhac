package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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

import com.example.appnhac.Adapter.ViewPagerPlaylistNhac;
import com.example.appnhac.Fragment.Fragment_Dia_Nhac;
import com.example.appnhac.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong, txttotaltimesong;
    SeekBar seekBarTime;
    ImageButton imgplay, imgrepeat, imgnext, imgpreview, imgsuffle;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistNhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        init();
        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();

        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);

            }

            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;

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
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylistNhac(getSupportFragmentManager());
        adapternhac.addFragment(fragment_dia_nhac);
        adapternhac.addFragment(fragment_play_danh_sach_cac_bai_hat);
        viewPagerplaynhac.setAdapter(adapternhac);
    }
}
