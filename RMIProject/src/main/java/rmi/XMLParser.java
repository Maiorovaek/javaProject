package rmi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import rmi.model.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {


    DocumentBuilder d;
    Document document;
    File file = new File(".\\src\\data.xml");
    DocumentBuilderFactory f;

    public XMLParser() {
        f = DocumentBuilderFactory.newInstance();
        try {
            d = f.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        try {
            document = d.parse(file);

        } catch (SAXException e) {
            e.printStackTrace();

        } catch (IOException e) {

            System.out.println("Файл не найден");
        }
    }

    public ArrayList<Student> readListStudent() {
        // int ind = 1;
        NodeList studentNodeList = document.getElementsByTagName("student");
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < studentNodeList.getLength(); i++) {
            if (studentNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element studentElement = (Element) studentNodeList.item(i);
                Student student = new Student(11, "dd", "fdvdfv", Student.Department.AppliedMathematics, 4.9);
                NodeList childNodes = studentElement.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);

                        switch (childElement.getNodeName()) {
                            case "id": {

                                student.setGradebookNumber(Long.parseLong(childElement.getTextContent()));
                                //                       ind++;

                            }
                            case "name": {
                                student.setName(childElement.getTextContent());

                            }
                            break;
                            case "surname": {
                                student.setSurname(childElement.getTextContent());
                            }
                            break;
                            case "department": {
                                student.setDepartmet(Student.Department.valueOf(childElement.getTextContent()));
                            }
                            break;
                            case "averageScore": {
                                student.setAverageScore(Double.parseDouble(childElement.getTextContent()));
                            }
                            break;
                        }
                    }

                }
                studentList.add(student);
            }
        }
        ArrayList<Student> b1 = new ArrayList<>(studentList);
        return b1;
    }


    public void addStudent(Student newStudent) {

        Element root = document.getDocumentElement();
        Element student = document.createElement("student");
        root.appendChild(student);

        Element id = document.createElement("id");
        Element name = document.createElement("name");
        Element surname = document.createElement("surname");
        Element department = document.createElement("department");
        Element averageScore = document.createElement("averageScore");

        id.appendChild(document.createTextNode(String.valueOf(newStudent.getGradebookNumber())));
        name.appendChild(document.createTextNode(newStudent.getName()));
        surname.appendChild(document.createTextNode(newStudent.getSurname()));
        department.appendChild(document.createTextNode(String.valueOf(newStudent.getDepartmet())));
        averageScore.appendChild(document.createTextNode(String.valueOf(newStudent.getAverageScore())));


        student.appendChild(id);
        student.appendChild(name);
        student.appendChild(surname);
        student.appendChild(department);
        student.appendChild(averageScore);


        transform();
    }


    public void removeStudent(long idDelStudent) {
        NodeList studentList = document.getElementsByTagName("student");

        for (int i = 0; i < studentList.getLength(); i++) {
            Element studentNode = (Element) studentList.item(i);

            Element idB = (Element) studentNode.getElementsByTagName("id").item(0);
            long pId = Long.parseLong(idB.getTextContent());


            if (pId == idDelStudent) {
                studentNode.getParentNode().removeChild(studentNode);
            }
        }


        transform();
    }


    public void updateStudent(long idUpdStudent, String surname/*double averageNewScore*/) {
        NodeList studentList = document.getElementsByTagName("student");
        for (int i = 0; i < studentList.getLength(); i++) {
            Element studentNode = (Element) studentList.item(i);
            Element idB = (Element) studentNode.getElementsByTagName("id").item(0);
            Long pId = Long.parseLong(idB.getTextContent());

            if (pId == idUpdStudent) {
                // studentNode.getElementsByTagName("averageScore").item(0).setTextContent(String.valueOf(averageNewScore));

                studentNode.getElementsByTagName("surname").item(0).setTextContent(surname);
            }
        }
        transform();
    }


    public void updateStudentAv(long idUpdStudent, double averageNewScore) {
        NodeList studentList = document.getElementsByTagName("student");
        for (int i = 0; i < studentList.getLength(); i++) {
            Element studentNode = (Element) studentList.item(i);
            Element idB = (Element) studentNode.getElementsByTagName("id").item(0);
            Long pId = Long.parseLong(idB.getTextContent());

            if (pId == idUpdStudent) {
                 studentNode.getElementsByTagName("averageScore").item(0).setTextContent(String.valueOf(averageNewScore));
            }
        }
        transform();
    }





    public void transform() {
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(String.valueOf(file)));

        try {
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}