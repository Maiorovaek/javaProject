package com.mySampleApplication.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.BookssService;
import com.mySampleApplication.client.Bookss;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//на стороне сервера представляет СЕРВЛЕТ
//необходимо определить методы интерфейса BookssService и заносим в web.xml и маппим
@SuppressWarnings("serial")
public class BookssServiceImpl extends RemoteServiceServlet implements BookssService {

    DocumentBuilder builder = null;
    Document document = null;


    public ArrayList<Bookss> listBooks() {
        File file = new File("C:\\Users\\AlexKate\\Desktop\\examples\\stab\\data.xml");


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            builder = factory.newDocumentBuilder();
        } catch (
                ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            document = builder.parse(file);
        } catch (
                SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

int ind =1;
        NodeList booksNodeList = document.getElementsByTagName("book");
        List<Bookss> bookssList = new ArrayList<>();
        for (int i = 0; i < booksNodeList.getLength(); i++) {
            if (booksNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element bookElement = (Element) booksNodeList.item(i);
                Bookss bookss = new Bookss(2, "j", "j", 20, 2001, new Date(20, 11, 2010));
                NodeList childNodes = bookElement.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);

                        switch (childElement.getNodeName()) {
                            case "id": {

                                bookss.setId(ind);
                                ind++;

                            }
                            case "author": {
                                bookss.setAuthor(childElement.getTextContent());

                            }
                            break;
                            case "nameBook": {
                                bookss.setNameBook(childElement.getTextContent());
                            }
                            break;
                            case "page": {
                                bookss.setNumberPages(Integer.parseInt(childElement.getTextContent()));
                            }
                            break;
                            case "year": {
                                bookss.setYear(Integer.valueOf(childElement.getTextContent()));
                            }
                            break;
                            case "date": {
                                bookss.setDateCreate(new Date());
                            }
                            break;
                        }
                    }
                }
                bookssList.add(bookss);
            }
        }
        ArrayList<Bookss> b1 = new ArrayList<>(bookssList);
        return b1;
    }
}