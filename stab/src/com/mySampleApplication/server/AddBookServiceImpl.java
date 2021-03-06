package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.AddBookService;
import com.mySampleApplication.client.Bookss;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AddBookServiceImpl extends RemoteServiceServlet implements AddBookService {
    @Override
    public void addBook(Bookss newBook) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance("com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl", this.getClass().getClassLoader());
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse(new FileInputStream("C:\\Users\\AlexKate\\Desktop\\examples\\stab\\data.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Element root = document.getDocumentElement();
        Element books = document.createElement("book");
        root.appendChild(books);

        Element id = document.createElement("id");
        Element author = document.createElement("author");
        Element nameBook = document.createElement("nameBook");
        Element page = document.createElement("page");
        Element year = document.createElement("year");
        Element date = document.createElement("date");
        id.appendChild(document.createTextNode(String.valueOf(newBook.getId())));
        author.appendChild(document.createTextNode(newBook.getAuthor()));
        nameBook.appendChild(document.createTextNode(newBook.getNameBook()));
        page.appendChild(document.createTextNode(String.valueOf(newBook.getNumberPages())));
        year.appendChild(document.createTextNode(String.valueOf(newBook.getYear())));
        date.appendChild(document.createTextNode(String.valueOf(newBook.getDateCreate())));


        books.appendChild(id);
        books.appendChild(author);
        books.appendChild(nameBook);
        books.appendChild(page);
        books.appendChild(year);
        books.appendChild(date);


        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("C:\\Users\\AlexKate\\Desktop\\examples\\stab\\data.xml"));

        try {
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
}