package com.example.magicdb.ui.main;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.magicdb.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class CartasAPI {


    private final String BASE_URL = "https://api.magicthegathering.io/v1";

    ArrayList<CartasObject> getCartas(){
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("v1")
                .appendPath("cards")
                .build();
        String url= builtUri.toString();
        System.out.println(url);
        return doCall(url);
    }





    private ArrayList<CartasObject> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<CartasObject> processJson(String jsonResponse) {
        ArrayList<CartasObject> cartas = new ArrayList<>();
        String rarity = MainFragment.raredadCarta;

        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCartas = data.getJSONArray("cards");

            for (int i = 0; i < jsonCartas.length(); i++) {
                JSONObject jsonCarta = jsonCartas.getJSONObject(i);
                CartasObject cartasObject = new CartasObject();
                String rarityString = jsonCarta.getString("rarity");

                switch (rarity){
                    case "0":
                        addCarta(cartasObject, jsonCarta, cartas, rarityString);
                        break;
                    case "1":
                        if (rarityString.equals("Rare")){
                            addCarta(cartasObject, jsonCarta, cartas, rarityString);
                        }
                        break;
                    case "2":
                        if (rarityString.equals("Common")){
                            addCarta(cartasObject, jsonCarta, cartas, rarityString);
                        }
                        break;
                    case "3":
                        if (rarityString.equals("Uncommon")){
                            addCarta(cartasObject, jsonCarta, cartas, rarityString);
                        }
                        break;
                }




            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        imprimirArrayList(cartas);
        return cartas;
    }

    private void addCarta(CartasObject carta, JSONObject jsonCarta, ArrayList<CartasObject> cartasObjects, String rarity) throws JSONException{
        carta.setRarity(rarity);
        carta.setNombre(jsonCarta.getString("name"));
        carta.setTipo(jsonCarta.getString("type"));
        carta.setArtista(jsonCarta.getString("artist"));
        carta.setText(jsonCarta.getString("text"));
        if (jsonCarta.has("imageUrl")){
            carta.setImageUrl(jsonCarta.getString("imageUrl"));
        } else{
            carta.setImageUrl("https://media.wizards.com/2017/images/daily/ImyiSsT0tEZJ.png");
        }
        cartasObjects.add(carta);
    }

    private void imprimirArrayList(ArrayList<CartasObject> cartasObjects){

    }



}
