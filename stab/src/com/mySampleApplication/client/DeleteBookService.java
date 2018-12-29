package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import java.util.List;

@RemoteServiceRelativePath("deleteBook")
public interface DeleteBookService extends RemoteService {
   List <Bookss> deleteBook( Bookss booksses, List<Bookss> list);
}
