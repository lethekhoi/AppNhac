package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Model.Album;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachAlbumAdapter extends RecyclerView.Adapter<DanhsachAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public DanhsachAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.with(context)
                .load(album.getHinhanhAlbum())
                .into(holder.imghinhAllAlbum);
        holder.txtTenAllAlbum.setText(album.getTenAlbum());
        holder.txtTenCasiAllAlbum.setText(album.getTencasiAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhAllAlbum;
        TextView txtTenAllAlbum, txtTenCasiAllAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhAllAlbum = itemView.findViewById(R.id.imgAllAlbum);
            txtTenAllAlbum = itemView.findViewById(R.id.txttenAllAlbum);
            txtTenCasiAllAlbum = itemView.findViewById(R.id.txttencasiAllAlbum);
        }
    }

}
