package com.service;

import com.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    void customerByIdDelete(int id);

    Customer updateCustomerById(int id, String lastname);

    void addCustomer(Customer customer);

    List<Customer> allCustomers();

    long getCustomerCount();

    Customer findCustomerByID(int customerId);


    void getNizhegorodskiiRegionCustomers();


    public Set<String> getLocations();

}
