package com.dao;

import com.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void customerByIdDelete(int id);

    Customer updateCustomerById(int id, String lastname);

    void addCustomer(Customer customer);

    List<Customer> allCustomers();

    long getCustomerCount();

    Customer findCustomerByID(int customerId);


    List<Customer> getNizhegorodskiiRegionCustomers();

}

