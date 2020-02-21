package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        //trả về view tùy theo objiect
        return view == object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);
        ImageView imgbackgroundbanner = view.findViewById(R.id.imgbackgroundBanner);
        ImageView imgsongbanner = view.findViewById(R.id.imgBanner);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDeBanner);
        TextView txtNoiDung = view.findViewById(R.id.txtNoiDung);
        Picasso.with(context)
                .load(arrayListBanner.get(position).getHinhBaiHat())
                .into(imgsongbanner);
        Picasso.with(context)
                .load(arrayListBanner.get(position).getHinhanh())
                .into(imgbackgroundbanner);

        txtNoiDung.setText(arrayListBanner.get(position).getNoidung());
        txtTieuDe.setText(arrayListBanner.get(position).getTenBaiHat());
        container.addView(view);
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
