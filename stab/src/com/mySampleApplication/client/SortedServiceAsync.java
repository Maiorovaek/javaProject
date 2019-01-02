package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

public interface SortedServiceAsync {
    void sortedBook(ArrayList<Bookss> books, AsyncCallback<ArrayList<Bookss>> callback);
}
