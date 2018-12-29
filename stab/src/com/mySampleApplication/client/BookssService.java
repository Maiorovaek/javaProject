package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.List;

 //Относительный путь сервлета
@RemoteServiceRelativePath("book")
public interface BookssService extends RemoteService {

     ArrayList<Bookss> listBooks();

}
