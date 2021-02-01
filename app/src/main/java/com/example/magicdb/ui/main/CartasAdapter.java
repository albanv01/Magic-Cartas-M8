package com.example.magicdb.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.magicdb.R;

import java.util.List;

public class CartasAdapter extends ArrayAdapter<CartasObject> {

    public CartasAdapter(Context context, int resource, List<CartasObject> objects){
        super(context,resource,objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CartasObject cartas = getItem(position);
        Log.w("XXXX", cartas.toString());

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_magic_row, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvRarity = convertView.findViewById(R.id.tvRarity);
        ImageView ivCardImage = convertView.findViewById(R.id.ivCardImage);

        tvTitle.setText(cartas.getNombre());
        tvRarity.setText(cartas.getRarity());

        Glide.with(getContext()).load(
                    cartas.getImageUrl()
        ).into(ivCardImage);

        return convertView;
    }
}
