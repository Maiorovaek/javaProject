package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

public interface BookssServiceAsync {
    void listBooks(AsyncCallback<ArrayList<Bookss>> async);
}
