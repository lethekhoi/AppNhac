package com.example.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appnhac.Adapter.DanhsachallchudeAdapter;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllChuDe;
    Toolbar toolbarAllChuDe;
    DanhsachallchudeAdapter danhsachallchudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);
        Anhxa();
        Init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callbacks = dataservice.GetAllChuDe();
        callbacks.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                Log.d("BBB", chuDeArrayList.get(0).getTenChuDe());
                danhsachallchudeAdapter = new DanhsachallchudeAdapter(DanhsachtatcachudeActivity.this, chuDeArrayList);
                recyclerViewAllChuDe.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this, 1));
                recyclerViewAllChuDe.setAdapter(danhsachallchudeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {
                Log.d("BBB", "fail 10");
            }
        });
    }

    private void Init() {
        setSupportActionBar(toolbarAllChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chủ Đề");
        toolbarAllChuDe.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbarAllChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        recyclerViewAllChuDe = findViewById(R.id.recyclerviewtatcachude);
        toolbarAllChuDe = findViewById(R.id.toolbartatcachude);

    }
}
