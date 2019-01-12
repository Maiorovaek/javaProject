package com.service;

import com.dao.IShopDAO;
import com.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements IShop {

    @Autowired
    IShopDAO shopDAO;

    @Override
    public void shopByIdDelete(int id) {
        shopDAO.shopByIdDelete(id);
    }

    @Override
    public Shop updateShopById(int id, String name) {
        return shopDAO.updateShopById(id, name);
    }

    @Override
    public void addShop(Shop shop) {
        shopDAO.addShop(shop);
    }

    @Override
    public List<Shop> allShops() {
        return shopDAO.allShops();
    }

    @Override
    public long getShopsCount() {
        return shopDAO.getShopsCount();
    }

    @Override
    public Shop findShopID(int shopId) {
        return shopDAO.findShopID(shopId);
    }

    @Override
    public List<String> getLocationShopName() {
        return shopDAO.getLocationShopName();
    }

    @Override
    public List<String> getShopNoAutozavod() {
        return shopDAO.getShopNoAutozavod();
    }
}
