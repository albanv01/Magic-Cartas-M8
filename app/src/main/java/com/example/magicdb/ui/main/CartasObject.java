package com.example.magicdb.ui.main;

import androidx.room.Entity;

import java.io.Serializable;

@Entity
public class CartasObject implements Serializable {
    private int id;
    private String nombre;
    private String imageUrl;
    private String rarity;
    private String artista;
    private String tipo;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CartasObject{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", rarity='" + rarity + '\'' +
                ", artista='" + artista + '\'' +
                ", tipo='" + tipo + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}


