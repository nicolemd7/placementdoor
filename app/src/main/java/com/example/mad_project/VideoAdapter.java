package com.example.mad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class VideoAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<Video> arrayVideo;

    public VideoAdapter(Context context, int layout, List<Video> videoList){
        myContext = context;
        myLayout = layout;
        arrayVideo = videoList;
    }

    @Override
    public int getCount() {
        return arrayVideo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout, null);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
        txtTitle.setText(arrayVideo.get(position).Title);

        ImageView imgThumnail = (ImageView) convertView.findViewById(R.id.imageViewThumnail);
        Picasso.with(myContext).load(arrayVideo.get(position).Thumnail).into(imgThumnail);

        return convertView;
    }
}
