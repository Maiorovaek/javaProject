package com.service;

import com.dao.CustomerDAO;
import com.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
@Autowired
    CustomerDAO customerDAO;
    @Override
    public void customerByIdDelete(int id) {
customerDAO.customerByIdDelete(id);
    }

    @Override
    public Customer updateCustomerById(int id, String lastname) {
        return customerDAO.updateCustomerById(id,lastname);
    }

    @Override
    public void addCustomer(Customer customer) {
customerDAO.addCustomer(customer);
    }

    @Override
    public List<Customer> allCustomers() {
     return customerDAO.allCustomers();
    }

    @Override
    public long getCustomerCount() {
       return customerDAO.getCustomerCount();
    }

    @Override
    public Customer findCustomerByID(int customerId) {
        return  customerDAO.findCustomerByID(customerId);
    }

    @Override
    public void getNizhegorodskiiRegionCustomers() {
        List<Customer> customers = customerDAO.getNizhegorodskiiRegionCustomers();
        for(Customer customer : customers) {
            System.out.println(customer.getLastname() + " --> " + customer.getDiscount());
        }
    }

    @Override
    public Set<String> getLocations() {
            List<Customer> customers = customerDAO.allCustomers();
            Set<String> locations = new HashSet<>();
            for(Customer customer : customers) {
                locations.add(customer.getLocation());
            }
            return locations;
    }
}
