package com.hdh.shoplistfilter.data.model;

import java.util.Arrays;

public class Shop implements Comparable<Shop>{
    private int shopRank;
    private String shopName;
    private String shopAddress;
    private String[] shopStyle;
    private String[] shopAge;
    private String shopImageURL;
    private int filterStyleCount;

    public Shop(int shopRank, String shopName, String shopAddress, String[] shopStyle, String[] shopAge, String shopImageURL) {
        this.shopRank = shopRank;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopStyle = shopStyle;
        this.shopAge = shopAge;
        this.shopImageURL = shopImageURL;
    }

    public int getShopRank() {
        return shopRank;
    }

    public void setShopRank(int shopRank) {
        this.shopRank = shopRank;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String[] getShopStyle() {
        return shopStyle;
    }

    public void setShopStyle(String[] shopStyle) {
        this.shopStyle = shopStyle;
    }

    public String[] getShopAge() {
        return shopAge;
    }

    public void setShopAge(String[] shopAge) {
        this.shopAge = shopAge;
    }

    public String getShopImageURL() {
        return shopImageURL;
    }

    public void setShopImageURL(String shopImageURL) {
        this.shopImageURL = shopImageURL;
    }

    public int getFilterStyleCount() {
        return filterStyleCount;
    }

    public void setFilterStyleCount(int filterStyleCount) {
        this.filterStyleCount = filterStyleCount;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopRank=" + shopRank +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopStyle=" + Arrays.toString(shopStyle) +
                ", shopAge=" + Arrays.toString(shopAge) +
                ", shopImageURL='" + shopImageURL + '\'' +
                '}';
    }

    @Override
    public int compareTo(Shop o) {
        if (this.filterStyleCount < o.getFilterStyleCount()) {
            return 1;
        }
        if (this.filterStyleCount == o.getFilterStyleCount()){
            if (this.shopRank < o.getShopRank()){
                return 1;
            } else {
                return  -1;
            }
        }
        if(this.filterStyleCount > o.getFilterStyleCount()) {
            return -1;
        }
        return 0;
    }


}
