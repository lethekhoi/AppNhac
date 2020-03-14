package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appnhac.Adapter.DanhSachBaiHatAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    Quangcao quangcao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangbaihat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);

        DataIntent();
        AnhXa();
        Init();
//bấm vào quảng cáo
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat(), quangcao.getHinhanh());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }
//bấm vào playlist

        if (playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(), playlist.getIcon(), playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
//bấm vào thể loại
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }

//bấm vào Album

        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhanhAlbum(), album.getHinhanhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }


    }

    private void GetDataAlbum(String idalbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callbacks = dataservice.Getdanhsachbaihattheoalbum(idalbum);
        callbacks.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
                Log.d("BBB", "false 10");
            }
        });
    }


    private void GetDataTheLoai(String idtheloai) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callbacks = dataservice.Getdanhsachbaihattheotheloai(idtheloai);
        callbacks.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
                Log.d("BBB", "false 10");
            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callbacks = dataservice.Getdanhsachbaihattheoplaylist(idplaylist);
        callbacks.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
                Log.d("BBB", "false 8");
            }
        });
    }

    private void setValueInView(String ten, String hinhicon, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(hinhicon).into(imgdanhsachcakhuc);//hình nhỏ


    }

    private void GetDataQuangCao(String idquangcao) {

        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callbacks = dataservice.Getdanhsachbaihattheoquangcao(idquangcao);
        callbacks.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                Log.d("BBB", mangbaihat.get(0).getTenBaiHat());
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {
                Log.d("BBB", "false 7");
            }
        });
    }


    private void Init() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);


    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtollbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihats);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DataIntent() {

        Intent intent = getIntent();
        if (intent != null) {

            if (intent.hasExtra("banner")) {

                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangcao.getTenBaiHat(), Toast.LENGTH_LONG).show();
            }
            if (intent.hasExtra("playlist")) {
                playlist = (Playlist) intent.getSerializableExtra("playlist");
                Toast.makeText(this, playlist.getTen(), Toast.LENGTH_LONG).show();
            }
            if (intent.hasExtra("idtheloai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
                Toast.makeText(this, theLoai.getTenTheLoai(), Toast.LENGTH_LONG).show();
            }

            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
                Toast.makeText(this, album.getTenAlbum(), Toast.LENGTH_LONG).show();
            }

        }

    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });

    }

}
