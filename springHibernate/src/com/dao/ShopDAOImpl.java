package com.dao;

import com.model.Shop;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("shopDAO")
@Transactional
public class ShopDAOImpl extends AppDAO implements IShopDAO {
    @Override
    public void shopByIdDelete(int id) {
        Query query = getSession().createQuery("delete from Shop where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public Shop updateShopById(int id, String name) {
        Criteria criteria = getSession().createCriteria(Shop.class);
        criteria.add(Restrictions.eq("id",id));
        Shop shop = (Shop) criteria.uniqueResult();
        shop.setNameShop(name);
        update(shop);
        return shop;
    }

    @Override
    public void addShop(Shop shop) {
        persist(shop);
    }

    @Override
    public List<Shop> allShops() {
        Criteria criteria = getSession().createCriteria(Shop.class);
        criteria.setFetchMode("purchase", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public long getShopsCount() {
        Object result = getSession().createCriteria(Shop.class).setProjection(Projections.rowCount()).uniqueResult();
        long count = Long.parseLong(result.toString());
        return count;
    }

    @Override
    public Shop findShopID(int shopId) {
        Criteria criteria = getSession().createCriteria(Shop.class);
        criteria.add(Restrictions.eq("id",shopId));
        return (Shop) criteria.uniqueResult();
    }

    @Override
    public List<String> getLocationShopName() {
        String sql = "SELECT name_shop FROM shop WHERE location_shop='sovetskiy' OR location_shop='sormovskiy'";
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Criteria.ROOT_ENTITY);
        List<String> results = query.list();

        return results;
    }

    @Override
    public List<String> getShopNoAutozavod() {
        String sql = "select name_shop FROM shop where idshop IN\n" +
                "(select p.seller\n" +
                "from purchase p\n" +
                "WHERE p.seller IN (SELECT idshop FROM shop WHERE name_shop <> 'avtozavod') AND p.customer IN (SELECT id FROM customer WHERE discount BETWEEN 0.01 AND 0.15)); ";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Criteria.ROOT_ENTITY);
        List<String> results = query.list();

        return results;
    }
}
