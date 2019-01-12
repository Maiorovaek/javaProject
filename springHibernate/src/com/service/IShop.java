package com.service;

import com.model.Shop;

import java.util.List;

public interface IShop {
    void shopByIdDelete(int id);
    Shop updateShopById(int id, String name);
    void addShop(Shop shop);
    List<Shop> allShops();
    long getShopsCount();
    Shop findShopID(int shopId);
    List<String> getLocationShopName();
    List<String> getShopNoAutozavod();

}
