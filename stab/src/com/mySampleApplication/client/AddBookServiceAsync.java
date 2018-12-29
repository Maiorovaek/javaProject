package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddBookServiceAsync {
    void addBook(Bookss newBook, AsyncCallback callback);
}
