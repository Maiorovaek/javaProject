package com.service;


import com.dao.IClientDAO;
import com.model.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("clientService")
public class ServiceClinentImpl implements IServiceClient {
    @Autowired
    IClientDAO clientDAO;


    @Override
    public void addClients(Clients clients) {
        clientDAO.addClients(clients);

    }

    @Override
    public List<Clients> listClients() {
        return clientDAO.listClients();
    }


    @Override
    public Clients getClientsById(int id) {
        return clientDAO.getClientsById(id);

    }

    @Override
    public boolean deleteClientsById(int id) {
        return clientDAO.deleteClientsById(id);
    }

    @Override
    public boolean deleteAllClients() {
        return clientDAO.deleteAllClients();
    }
}
