package com.example.magicdb.ui.main;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface CartasDAO{

    @Query("select * from cartas")
    LiveData<List<CartasObject>> getCards();

    @Insert
    void addCards(CartasObject card);

    @Insert
    void addCards(List<CartasObject> cards);

    @Delete
    void deleteCard(CartasObject card);

    @Query("DELETE FROM cartas")
    void deleteCard();

}