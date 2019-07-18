package com.hdh.shoplistfilter.data.model;

import java.util.ArrayList;

public class ShopList {
    private String week;
    private ArrayList<Shop> shopArrayList = new ArrayList<>();

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public ArrayList<Shop> getShopArrayList() {
        return shopArrayList;
    }

    public void setShopArrayList(ArrayList<Shop> shopArrayList) {
        this.shopArrayList = shopArrayList;
    }

    @Override
    public String toString() {
        return "ShopList{" +
                "week='" + week + '\'' +
                ", shopArrayList=" + shopArrayList +
                '}';
    }
}
