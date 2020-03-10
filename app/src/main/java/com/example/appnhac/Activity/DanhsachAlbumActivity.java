package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appnhac.Adapter.DanhsachAlbumAdapter;
import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachAlbumActivity extends AppCompatActivity {
    Toolbar toolbarallbalbum;
    RecyclerView recyclerViewallalbum;
    DanhsachAlbumAdapter danhsachAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_album);

        AnhXa();
        Init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                Log.d("BBB", albumArrayList.get(0).getTenAlbum());
                danhsachAlbumAdapter = new DanhsachAlbumAdapter(DanhsachAlbumActivity.this, albumArrayList);
                recyclerViewallalbum.setLayoutManager(new GridLayoutManager(DanhsachAlbumActivity.this, 2));
                recyclerViewallalbum.setAdapter(danhsachAlbumAdapter);


            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.d("BBB", "fail 13");
            }
        });


    }

    private void Init() {
        setSupportActionBar(toolbarallbalbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Album");
        toolbarallbalbum.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbarallbalbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void AnhXa() {
        toolbarallbalbum = findViewById(R.id.toolbarallalbum);
        recyclerViewallalbum = findViewById(R.id.recyclerviewallalbum);
    }


}
