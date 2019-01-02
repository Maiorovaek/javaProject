package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.Bookss;
import com.mySampleApplication.client.SortedService;
import java.util.*;


public class SortedServiceImpl extends RemoteServiceServlet implements SortedService {


    @Override
    public ArrayList<Bookss> sortedBook(ArrayList<Bookss> books) {
        ArrayList<Bookss> sorted = new ArrayList<>();
        String[] name = new String[books.size()];
        int i = 0;
        for( Bookss b : books) {
            name[i] = b.getAuthor();
            i++;
        }


        Arrays.sort(name);
        for(String t : name) {
            for(Bookss b : books) {
                if(b.getAuthor().equals(t)){
                    sorted.add(b);
                }
            }
        }
        return sorted;
    }

}








