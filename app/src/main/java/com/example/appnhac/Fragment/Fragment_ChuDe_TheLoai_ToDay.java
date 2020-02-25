package com.example.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.TheLoai;
import com.example.appnhac.Model.Theloaitrongngay;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        AnhXa();
        GetData();
        return view;
    }

    private void AnhXa() {

        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        txtxemthemchudetheloai = view.findViewById(R.id.txtXemThemChuDeTheLoai);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<Theloaitrongngay> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
                Theloaitrongngay theloaitrongngay = response.body();
                Log.d("BBB", theloaitrongngay.getTheLoai().get(0).getTenTheLoai());

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theloaitrongngay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theloaitrongngay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);


                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(500, 250);
                layout.setMargins(10, 20, 10, 30);

                for (int i = 0; i < (chuDeArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                        Picasso.with(getActivity())
                                .load(chuDeArrayList.get(i).getHinhChuDe())
                                .into(imageView);
                    }

                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                for (int j = 0; j < (theLoaiArrayList.size()); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        Picasso.with(getActivity())
                                .load(theLoaiArrayList.get(j).getHinhTheLoai())
                                .into(imageView);
                    }

                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                horizontalScrollView.addView(linearLayout);


            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {
                Log.d("BBB", "false");
            }
        });
    }
}
