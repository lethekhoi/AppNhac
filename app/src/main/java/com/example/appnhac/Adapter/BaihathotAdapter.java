package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Activity.PlayNhacActivity;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.R;
import com.example.appnhac.Service.APIService;
import com.example.appnhac.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;

    public BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.txtTenBaiHatHot.setText(baihat.getTenBaiHat());
        holder.txtTenCasiBaiHatHot.setText(baihat.getCaSi());
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imgHinhBaihathot);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBaiHatHot, txtTenCasiBaiHatHot;
        ImageView imgHinhBaihathot, imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHatHot = itemView.findViewById(R.id.txttenbaihathot);
            txtTenCasiBaiHatHot = itemView.findViewById(R.id.txttencasibaihathot);
            imgHinhBaihathot = itemView.findViewById(R.id.imgBaihathot);
            imgluotthich = itemView.findViewById(R.id.imgThich);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", baihatArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("OK")) {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(context, "Bị lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("BBB", "false lt");
                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
