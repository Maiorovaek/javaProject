package com.service;

import com.model.Clients;

import java.util.List;

public interface IServiceClient {
    void addClients(Clients clients);
    List<Clients> listClients();
    Clients getClientsById(int id);
    boolean deleteClientsById(int id);
    boolean deleteAllClients();
}
