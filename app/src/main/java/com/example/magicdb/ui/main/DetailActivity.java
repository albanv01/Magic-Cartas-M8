package com.example.magicdb.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.magicdb.R;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        System.out.println("Detail Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DetailActivityFragment.newInstance())
                    .commitNow();
        }
    }
}
