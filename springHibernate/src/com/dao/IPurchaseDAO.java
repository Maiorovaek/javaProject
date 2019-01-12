package com.dao;


import com.model.Purchase;

import java.util.List;

public interface IPurchaseDAO {
    void purchaseByIdDelete(int id);
    Purchase updatePurchaseById(int id, int quantity);
    void addPurchase(Purchase purchase);
    List<Purchase> allPurchase();
    long getPurchaseCount();
    Purchase findPurchaseByID(int purchaseId);
    List<String> getPurchaseInfo();
    List<Purchase> getPurchaseoOver();
    List getPurchaseInLocation();

}
