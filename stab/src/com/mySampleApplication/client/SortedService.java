package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

@RemoteServiceRelativePath("sorted")
public interface SortedService extends RemoteService {
    ArrayList<Bookss> sortedBook(ArrayList<Bookss> books);
}
