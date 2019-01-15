package com.service;


import com.model.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestControllerMain {

    @Autowired
    IServiceClient clientService;


    @GetMapping(value = "/buyer", produces = "application/json")
    public ResponseEntity<List<Clients>> listClients() {
        List<Clients> clientslist = clientService.listClients();
        if(clientslist.isEmpty()) {
            return new ResponseEntity<List<Clients>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Clients>>(clientslist, HttpStatus.OK);
    }



    @GetMapping(value = "/buyer/{id}",produces ="application/json" )
    public ResponseEntity<Clients> getClientsByID(@PathVariable("id") int id){

        Clients clients = clientService.getClientsById(id);

        if (clients.equals(null))
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Clients>(clients, HttpStatus.OK);
    }

    @PostMapping(value = "/buyer",consumes = "application/json")
    public ResponseEntity<Clients> addClient(@RequestBody Clients client){
        clientService.addClients(client);
        return new ResponseEntity<Clients>(client, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/buyer")
    public ResponseEntity<Clients> deleteAllClients(){
        boolean result = clientService.deleteAllClients();

        if (!result)
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Clients>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value="/buyer/{id}")
    public ResponseEntity<Clients> deleteClientByID(@PathVariable("id") int id){
        boolean result = clientService.deleteClientsById(id);

        if (!result)
            return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Clients>(HttpStatus.NO_CONTENT);
    }


}
