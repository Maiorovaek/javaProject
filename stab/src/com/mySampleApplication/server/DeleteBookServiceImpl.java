package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.Bookss;
import com.mySampleApplication.client.DeleteBookService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DeleteBookServiceImpl extends RemoteServiceServlet implements DeleteBookService {
    @Override
    public void deleteBook(Bookss bookDel) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance("com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl", this.getClass().getClassLoader());
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = db.parse(new FileInputStream("C:\\Users\\AlexKate\\Desktop\\examples\\stab\\data.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Integer idBook = bookDel.getId();
        String authorBook = bookDel.getAuthor();
        String nameBook = bookDel.getNameBook();
        //  String pageBook = bookDel.getNameBook();
        //  String yearBook = bookDel.getNameBook();


            NodeList bookList = document.getElementsByTagName("book");

            for (int i = 0; i < bookList.getLength(); i++) {

                Element bookNode = (Element) bookList.item(i);
//id , authorB, nameB, pageB, yearB, dateB
                Element idB = (Element) bookNode.getElementsByTagName("id").item(0);
                Element authorB = (Element) bookNode.getElementsByTagName("author").item(0);
                Element nameB = (Element) bookNode.getElementsByTagName("nameBook").item(0);
            //    Element pageB = (Element) bookNode.getElementsByTagName("page").item(0);
              //  Element yearB = (Element) bookNode.getElementsByTagName("year").item(0);
          //      Element dateB = (Element) bookNode.getElementsByTagName("date").item(0);

                Integer pId = Integer.valueOf(idB.getTextContent());
                String pAuthor = authorB.getTextContent();
                String pName = nameB.getTextContent();
           //     Integer pPages = Integer.valueOf(pageB.getTextContent());
            //    Integer pYear = Integer.valueOf(yearB.getTextContent());

                if (/*pId.equals(idBook) &&*/ pAuthor.equals(authorBook) && pName.equals(nameBook)/*&& pPages.equals(pageBook) && pYear.equals(yearBook)*/) {
                    bookNode.getParentNode().removeChild(bookNode);
                }
            }

        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("C:\\Users\\AlexKate\\Desktop\\examples\\stab\\data.xml"));

        try {
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }

}