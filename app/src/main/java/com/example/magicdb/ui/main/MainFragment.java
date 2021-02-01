package com.example.magicdb.ui.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.magicdb.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {

    private ListView lvMagic;
    private CartasAdapter adapter;
    private CartasViewModel cartasViewModel;
    private SharedPreferences sharedPreferences;
    private SharedViewModel sharedViewModel;
    private MainViewModel mainViewModel;
    public static String raredadCarta;


    private Bundle savedInstanceState;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        raredadCarta = sharedPreferences.getString("Raredad", "Raredad");


        ArrayList <CartasObject> items = new ArrayList<>();
        adapter = new CartasAdapter(
                getContext(),
                R.layout.lv_magic_row,
                items
        );

        sharedViewModel = ViewModelProviders.of(getActivity()).get(
        SharedViewModel.class
        );

        lvMagic=view.findViewById(R.id.lvMagic);
        lvMagic.setAdapter(adapter);

        lvMagic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int i, long l){
                CartasObject carta = (CartasObject) adapterView.getItemAtPosition(i);
                if (!isTablet()){
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("carta", carta);
                    startActivity(intent);
                } else {
                    sharedViewModel.select(carta);
                }
            }
        });

        cartasViewModel = ViewModelProviders.of(this).get(CartasViewModel.class);

        cartasViewModel.getCartas().observe(getViewLifecycleOwner(), cartas ->{
            adapter.clear();
            adapter.addAll(cartas);
        });


        return view;
    }

    //boolean esTablet() {
        //return getResources().getBoolean(R.bool.tablet);
    //}




    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cartas_fragment, menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            refresh();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<CartasObject>> {
        @Override
        protected ArrayList<CartasObject> doInBackground(Void... voids){
            CartasAPI api = new CartasAPI();
            ArrayList<CartasObject> result = api.getCartas();
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<CartasObject> card){
            adapter.clear();
            for (CartasObject carta : card){
                adapter.add(carta);
            }
        }


    }




    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }



    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
        cartasViewModel.reload();
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    boolean isTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }



}









