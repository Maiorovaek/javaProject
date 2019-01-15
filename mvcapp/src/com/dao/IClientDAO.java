package com.dao;

import com.model.Clients;


import java.util.List;

public interface IClientDAO {
    void addClients(Clients clients);
    List<Clients> listClients();
    Clients getClientsById(int id);
    boolean deleteClientsById(int id);
    boolean deleteAllClients();
}
