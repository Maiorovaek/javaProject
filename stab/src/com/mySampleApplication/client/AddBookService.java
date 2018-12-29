package com.mySampleApplication.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("addbook")
public interface AddBookService extends RemoteService {
    void addBook(Bookss newBook);

}
