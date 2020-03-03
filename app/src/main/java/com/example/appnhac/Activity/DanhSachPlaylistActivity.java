package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appnhac.Adapter.DanhSachCacPlaylistAdapter;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlaylistActivity extends AppCompatActivity {
    Toolbar toolbardanhsachplaylist;
    RecyclerView recyclerViewdanhsachplaylist;
    DanhSachCacPlaylistAdapter danhSachCacPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_playlist);
        AnhXa();
        Init();
        GetData();
    }

    private void GetData() {

        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callbacks = dataservice.GetDanhSachPlaylist();
        callbacks.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
                Log.d("BBB", playlistArrayList.get(0).getTen());
                danhSachCacPlaylistAdapter = new DanhSachCacPlaylistAdapter(DanhSachPlaylistActivity.this, playlistArrayList);
                recyclerViewdanhsachplaylist.setLayoutManager(new GridLayoutManager(DanhSachPlaylistActivity.this, 2));
                recyclerViewdanhsachplaylist.setAdapter(danhSachCacPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d("BBB", "fail 9");
            }
        });

    }

    private void Init() {
        setSupportActionBar(toolbardanhsachplaylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbardanhsachplaylist.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbardanhsachplaylist.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void AnhXa() {
        toolbardanhsachplaylist = findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewdanhsachplaylist = findViewById(R.id.recyclerviedanhsachcacplaylist);
    }
}
