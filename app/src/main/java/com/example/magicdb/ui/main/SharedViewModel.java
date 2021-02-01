package com.example.magicdb.ui.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<CartasObject> selected = new MutableLiveData<CartasObject>();

    public void select(CartasObject cartas) {
        selected.setValue(cartas);
    }

    public LiveData<CartasObject> getSelected() {
        return selected;
    }
}
