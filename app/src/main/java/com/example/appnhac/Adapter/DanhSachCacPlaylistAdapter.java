package com.example.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCacPlaylistAdapter extends RecyclerView.Adapter<DanhSachCacPlaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> mangplaylist;

    public DanhSachCacPlaylistAdapter(Context context, ArrayList<Playlist> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = mangplaylist.get(position);
        holder.txttencacplaylist.setText(playlist.getTen());
        Picasso.with(context)
                .load(playlist.getHinhPlaylist())
                .into(holder.imghinhcacplaylist);
    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhcacplaylist;
        TextView txttencacplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AnhXa();
        }

        private void AnhXa() {
            imghinhcacplaylist = itemView.findViewById(R.id.imgdanhsachcacplaylist);
            txttencacplaylist = itemView.findViewById(R.id.txttendanhsachcacplaylist);

        }
    }
}
