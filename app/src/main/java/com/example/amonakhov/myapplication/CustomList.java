package com.example.amonakhov.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igorpresnyakov.myapplication.R;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> urls = new ArrayList<String>();
    public ArrayList<Bitmap> cImg;
    public CustomList(Activity context,
                      ArrayList<String> ch,ArrayList<Bitmap> ur) {
        super(context, R.layout.table,ch);
        this.context = context;
        this.name = ch;
        this.cImg = ur;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.table, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(name.get(position));
        if (cImg.get(position)!=null) {
            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            imageView.setImageBitmap(cImg.get(position));
        }

        imageView.setImageResource(imageId[position]);
        return rowView;
    }


}