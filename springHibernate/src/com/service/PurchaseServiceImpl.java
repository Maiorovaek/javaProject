package com.service;

import com.dao.CustomerDAO;
import com.dao.IPurchaseDAO;
import com.dao.IShopDAO;
import com.model.Customer;
import com.model.Purchase;
import com.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service("purchaseService")
public class PurchaseServiceImpl implements  IPurchaseService {


    @Autowired
    IPurchaseDAO purchaseDAO;
    @Autowired
    IShopDAO shopDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Override
    public void purchaseByIdDelete(int id) {
        purchaseDAO.purchaseByIdDelete(id);
    }

    @Override
    public Purchase updatePurchaseById(int id, int quantity) {
        return purchaseDAO.updatePurchaseById(id, quantity);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseDAO.addPurchase(purchase);
    }

    @Override
    public List<Purchase> allPurchase() {
        return purchaseDAO.allPurchase();
    }

    @Override
    public long getPurchaseCount() {
        return purchaseDAO.getPurchaseCount();
    }

    @Override
    public Purchase findPurchaseByID(int purchaseId) {
        return purchaseDAO.findPurchaseByID(purchaseId);
    }

    @Override
    public List<String> getPurchaseInfo() {
        return purchaseDAO.getPurchaseInfo();
    }

    @Override
    public void getPurchaseoOver() {
        List<Purchase> list = purchaseDAO.getPurchaseoOver();
        for(Purchase purchase : list) {
            System.out.println("Number id --> " + purchase.getNumberOder() + ", customer lastname --> " + purchase.getCustomer().getLastname() + ", date --> " + purchase.getDate());
        }
    }

    @Override
    public void getPurchaseInLocation() {
        List list = purchaseDAO.getPurchaseInLocation();
        for(Object obj : list) {
            Object[] arr = (Object[]) obj;
            System.out.println("Lastname --> " + arr[0] + ", location --> " + arr[1] + ", date --> " + arr[2]);
        }
    }

    @Override
    public Set<Integer> getPurchasedMonths() {
        List<Purchase> purchases = purchaseDAO.allPurchase();
        Set<Integer> months = new HashSet<>();

        for(Purchase purchase : purchases) {
            months.add(purchase.getDate().getMonth());
        }

        return months;
    }

    @Override
    public void getLastnameAndNameShop() {
        List<Purchase> purchaseList = purchaseDAO.allPurchase();
        List<LastnameAndNameShop> list = new ArrayList<>();

        for(Purchase purchase : purchaseList) {
            Customer customer = customerDAO.findCustomerByID(purchase.getCustId());
            Shop shop = shopDAO.findShopID(purchase.getShopId());

            list.add(new LastnameAndNameShop(customer.getLastname(),shop.getNameShop(), purchase.getNumberOder()));
        }

        System.out.println(list);
    }

    private class LastnameAndNameShop {
        private String lastname;
        private String shopname;
        private int orderId;

        public LastnameAndNameShop(String lastname, String shopname, int orderId) {
            this.lastname = lastname;
            this.shopname = shopname;
            this.orderId = orderId;
        }

        @Override
        public String toString() {
            return "LastnameAndShopname{" +
                    "lastname='" + lastname + '\'' +
                    ", shopname='" + shopname + '\'' +
                    ", orderId=" + orderId +
                    '}';
        }

    }
}
