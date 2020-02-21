package com.example.appnhac.Service;

import com.example.appnhac.Model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {
    //gửi phương thức và nhận dữ liệu từ phía server về

    //gửi tương tác lên server

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();



}
