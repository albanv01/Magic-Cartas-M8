package com.example.magicdb.ui.main;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class CartasViewModel extends AndroidViewModel {
    private final Application app;
    private final AppDB appDatabase;
    private final CartasDAO cartasDao;
    private LiveData<List<CartasObject>> cartas;

    public CartasViewModel(Application application) {
        super(application);
        this.app = application;
        this.appDatabase = AppDB.getDatabase(
                (this.getApplication()));
        this.cartasDao =appDatabase.getCartasDao();
    }

    public void reload() {
        // do async operation to fetch users
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    public LiveData<List<CartasObject>> getCartas() {
        return cartasDao.getCards();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            CartasAPI api=new CartasAPI();
            ArrayList<CartasObject> result;

            result=api.getCartas();

            cartasDao.deleteCard();
            cartasDao.addCards(result);

            return null;
        }
    }



}
