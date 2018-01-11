package com.example.usuario.examenecasielles.data.db.model;

import java.util.Date;

/**
 * Modelo para las ofertas de la aplicación
 */
public class Offer {

    public interface Importance {
        int NOT_IMPORTANT = 0;
        int DEFAULT = 1;
        int IMPORTANT = 2;
    }

    private int id;
    private int idResImg;
    private String name;
    private String shop;
    private String date;
    private int importance;

    public Offer(int id, int idResImg, String name, String shop, String date, int importance) {
        this.id = id;
        this.idResImg = idResImg;
        this.name = name;
        this.shop = shop;
        this.date = date;
        this.importance = importance;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdResImg() {
        return idResImg;
    }
    public void setIdResImg(int idResImg) {
        this.idResImg = idResImg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShop() {
        return shop;
    }
    public void setShop(String shop) {
        this.shop = shop;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getImportance() {
        return importance;
    }
    public void setImportance(int importance) {
        this.importance = importance;
    }
}
