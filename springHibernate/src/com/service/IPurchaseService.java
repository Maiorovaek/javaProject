package com.service;

import com.model.Purchase;

import java.util.List;
import java.util.Set;

public interface IPurchaseService {
    void purchaseByIdDelete(int id);

    Purchase updatePurchaseById(int id, int quantity);

    void addPurchase(Purchase purchase);

    List<Purchase> allPurchase();

    long getPurchaseCount();

    Purchase findPurchaseByID(int purchaseId);

    List<String> getPurchaseInfo();

    void getPurchaseoOver();

    void getPurchaseInLocation();


    Set<Integer> getPurchasedMonths();


    public void getLastnameAndNameShop();
}
