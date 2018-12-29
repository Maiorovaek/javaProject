package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.List;

public interface DeleteBookServiceAsync {


    void deleteBook( Bookss booksses, List<Bookss> list, AsyncCallback<List <Bookss>> async);
}
