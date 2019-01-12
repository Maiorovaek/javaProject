package com.dao;

import com.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerDAO {
    void customerByIdDelete(int id);

    Customer updateCustomerById(int id, String lastname);

    void addCustomer(Customer customer);

    List<Customer> allCustomers();

    long getCustomerCount();

    Customer findCustomerByID(int customerId);


    List<Customer> getNizhegorodskiiRegionCustomers();

}

