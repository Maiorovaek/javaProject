package com.dao;

import com.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl extends AppDAO implements CustomerDAO {


    @Override
    public void customerByIdDelete(int id) {
        Query query = getSession().createQuery("delete from Customer where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();

    }

    @Override
    public Customer updateCustomerById(int id, String lastname) {

        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("id", id));
        Customer customer = (Customer) criteria.uniqueResult();
        customer.setLastname(lastname);
        update(customer);
        return customer;
    }

    @Override
    public void addCustomer(Customer customer) {
        persist(customer);
    }

    @Override
    public List<Customer> allCustomers() {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.setFetchMode("purchase", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public long getCustomerCount() {
        Object result = getSession().createCriteria(Customer.class).setProjection(Projections.rowCount()).uniqueResult();
        long count = Long.parseLong(result.toString());
        return count;
    }

    @Override
    public Customer findCustomerByID(int customerId) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("id",customerId));
        return (Customer) criteria.uniqueResult();
    }

    @Override
    public List<Customer> getNizhegorodskiiRegionCustomers() {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.like("location", "nizhegorodskiy"));
        return criteria.list();

    }
}
