package com.example.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnhac.Activity.DanhsachbaihatActivity;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    Context context;


    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {


        super(context, resource, objects);
    }

    class ViewHolder {

        TextView txtTenPlaylist;
        ImageView imgBackground, imgPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenPlaylist = convertView.findViewById(R.id.txtTenPlaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imgbackgroundPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imgPlaylist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.imgBackground);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgPlaylist);
        viewHolder.txtTenPlaylist.setText(playlist.getTen());


        return convertView;
    }
}
