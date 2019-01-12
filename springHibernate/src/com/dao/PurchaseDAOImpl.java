package com.dao;

import com.model.Purchase;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository("purchaseDAO")
@Transactional
public class PurchaseDAOImpl extends AppDAO implements IPurchaseDAO {
    @Override
    public void purchaseByIdDelete(int id) {
        Query query = getSession().createQuery("delete from purchase where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public Purchase updatePurchaseById(int id, int quantity) {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.add(Restrictions.eq("id",id));
        Purchase purchase = (Purchase) criteria.uniqueResult();
        purchase.setQuantity(quantity);
        update(purchase);
        return purchase;
    }

    @Override
    public void addPurchase(Purchase purchase) {
persist(purchase);
    }

    @Override
    public List<Purchase> allPurchase() {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        //criteria.setFetchMode("shop", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public long getPurchaseCount() {
        return 0;
    }

    @Override
    public Purchase findPurchaseByID(int purchaseId) {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.add(Restrictions.eq("id",purchaseId));
        return (Purchase) criteria.uniqueResult();
    }

    @Override
    public List<String> getPurchaseInfo() {
        String sql = "SELECT p.number_order, c.lastname, c.discount, b.name_book, p.quantity\n" +
                "FROM purchase p, customer c, books b\n" +
                "WHERE p.customer = c.id AND p.book = b.id_book;";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<String> results = query.list();

        return results;
    }

    @Override
    public List<Purchase> getPurchaseoOver() {
        Criteria criteria = getSession().createCriteria(Purchase.class);
        criteria.add(Restrictions.gt("sum", 60000));
        return criteria.list();
    }

    @Override
    public List getPurchaseInLocation() {
        Query query = getSession().createQuery("select c.lastname, c.location, p.date from Purchase p join p.seller s join p.customer c where c.location=s.location_shop");
        List list = query.list();
        return list;
    }
}
