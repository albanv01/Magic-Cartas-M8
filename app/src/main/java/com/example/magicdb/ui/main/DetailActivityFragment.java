package com.example.magicdb.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.magicdb.R;

public class DetailActivityFragment extends Fragment {

    private View view;
    private ImageView ivCardImage;
    private TextView tvTitle;
    private TextView tvRarity;
    private TextView tvArtist;
    private TextView tvType;
    private TextView tvText;

    public static DetailActivityFragment newInstance() {return new DetailActivityFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if (i!=null){
            CartasObject cartas = (CartasObject) i.getSerializableExtra("card");
            if (cartas != null){
                updateUI(cartas);
            }
        }
        SharedViewModel sharedViewModel = ViewModelProviders.of(
                getActivity()
        ).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<CartasObject>() {
            @Override
            public void onChanged(CartasObject cartas) {
                updateUI(cartas);
            }
        });
        return view;
    }

    private void updateUI (CartasObject cartas){
        Log.d("CARD", cartas.toString());

        ivCardImage = view.findViewById(R.id.ivCardImage);
        tvTitle= view.findViewById(R.id.tvTitle);
        tvRarity = view.findViewById(R.id.tvRarity);
        tvArtist = view.findViewById(R.id.tvArtist);
        tvType = view.findViewById(R.id.tvType);
        tvTitle = view.findViewById(R.id.tvText);

        tvTitle.setText(cartas.getNombre());
        tvRarity.setText(cartas.getRarity());
        tvArtist.setText(cartas.getArtista());
        tvText.setText(cartas.getText());

        Glide.with(getContext()).load(
                cartas.getImageUrl()
        ).into(ivCardImage);
    }

}
