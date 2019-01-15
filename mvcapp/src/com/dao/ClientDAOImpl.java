package com.dao;

import com.model.Clients;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository("clientDAO")
@Transactional
public class ClientDAOImpl extends BasicDAO implements IClientDAO {

    @Override
    public void addClients(Clients clients) {
        persist(clients);
    }

    @Override
    public List<Clients> listClients() {
        Query query = getSession().createQuery("FROM Clients");
        return query.list();
    }


    @Override
    public Clients getClientsById(int id) {
        Query query = getSession().createQuery("FROM Clients WHERE id = :id");
        query.setInteger("id",id);
        return (Clients) query.uniqueResult();

    }

    @Override
    public boolean deleteClientsById(int id) {
        Query query = getSession().createQuery("DELETE FROM Clients WHERE id = :id");
        query.setInteger("id",id);
        boolean res = (query.executeUpdate() == 1) ? true : false;
        return res;
    }

    @Override
    public boolean deleteAllClients() {
        Query query = getSession().createQuery("DELETE FROM Clients");
        boolean res = (query.executeUpdate() == 1) ? true : false;
        return res;
    }

}
