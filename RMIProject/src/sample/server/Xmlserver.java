package sample.server;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.client.IClient;
import sample.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Xmlserver extends UnicastRemoteObject implements IServer {
    public ArrayList<IClient> clients = new ArrayList<>();
    static File file = new File("src/sample/baze.xml");

    public Xmlserver() throws RemoteException {
        super();
    }


    public static ArrayList<Student> Domread() throws Exception {


        DOMParser parser = new DOMParser();
        parser.parse(String.valueOf(file));
        Document document = parser.getDocument();

        NodeList studentNodeList = document.getElementsByTagName("student");
        ArrayList<Student> studentlist = new ArrayList<>();


        for (int i = 0; i < studentNodeList.getLength(); i++) {

            if (studentNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element studentElement = (Element) studentNodeList.item(i);
                Student student = new Student(55, "dcsc", "ff", Student.Department.AppliedMathematics, 4.5);
                NodeList childNodes = studentElement.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);

                        switch (childElement.getNodeName()) {
                            case "id": {

                                student.setGradebookNumber(Long.parseLong(childElement.getTextContent()));

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
                studentlist.add(student);
            }
        }

        return studentlist;
    }

    public static void Domwrite(Student student) throws Exception {
        DOMParser parser = new DOMParser();
        parser.parse(String.valueOf(file));
        Document doc = parser.getDocument();
        Element root = doc.getDocumentElement();
        Element students = doc.createElement("student");
        root.appendChild(students);

        Element id = doc.createElement("id");
        id.setTextContent(String.valueOf(student.getGradebookNumber()));
        students.appendChild(id);

        Element name = doc.createElement("name");
        name.setTextContent(student.getName());
        students.appendChild(name);

        Element surname = doc.createElement("surname");
        surname.setTextContent(student.getSurname());
        students.appendChild(surname);

        Element dep = doc.createElement("department");
        dep.setTextContent(String.valueOf(student.getDepartmet()));
        students.appendChild(dep);

        Element avSc = doc.createElement("averageScore");
        avSc.setTextContent(String.valueOf(student.getAverageScore()));
        students.appendChild(avSc);


        doc = parser.getDocument();
        OutputFormat format = new OutputFormat(doc);
        format.setIndenting(true);
        XMLSerializer serializer = new XMLSerializer(new FileOutputStream(new File(String.valueOf(file))), format);
        serializer.serialize(doc);


    }

    public static void Domdelete(long idDelStudent) throws Exception {
        DOMParser parser = new DOMParser();
        parser.parse(String.valueOf(file));
        Document doc = parser.getDocument();
        NodeList studentList = doc.getElementsByTagName("student");
        for (int i = 0; i < studentList.getLength(); i++) {
            Element studentNode = (Element) studentList.item(i);

            Element idB = (Element) studentNode.getElementsByTagName("id").item(0);
            long pId = Long.parseLong(idB.getTextContent());


            if (pId == idDelStudent) {
                studentNode.getParentNode().removeChild(studentNode);
            }
                doc = parser.getDocument();
                OutputFormat format = new OutputFormat(doc);
                format.setIndenting(true);
                XMLSerializer serializer = new XMLSerializer(new FileOutputStream(new File(String.valueOf(file))), format);
                serializer.serialize(doc);

        }
    }

    public static void editStudentXML(long id, Student student) throws IOException, SAXException {

        DOMParser parser = new DOMParser();
        parser.parse(String.valueOf(file));
        Document doc = parser.getDocument();
        NodeList studentList = doc.getElementsByTagName("student");
        System.out.println(id);


        for (int i = 0; i < studentList.getLength(); i++) {
            Element studentNode = (Element) studentList.item(i);

            Element idB = (Element) studentNode.getElementsByTagName("id").item(0);
            long pId = Long.parseLong(idB.getTextContent());
            if (pId == id) {
                idB.setTextContent(String.valueOf(student.getGradebookNumber()));
                studentNode.getElementsByTagName("surname").item(0).setTextContent(student.getSurname());
                studentNode.getElementsByTagName("name").item(0).setTextContent(student.getName());
                studentNode.getElementsByTagName("department").item(0).setTextContent(String.valueOf(student.getDepartmet()));
                studentNode.getElementsByTagName("averageScore").item(0).setTextContent(String.valueOf(student.getAverageScore()));
            }
            doc = parser.getDocument();
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true);
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(new File(String.valueOf(file))), format);
            serializer.serialize(doc);
        }

    }
    public void addStudent(Student ex) throws RemoteException {
        System.out.println("Object get");
        try {
            Domwrite(ex);
            for (IClient c : clients)
                c.Update();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Object sucsseccfuly writed");
    }


    public ArrayList<Student> print() throws RemoteException {
        try {
            System.out.println("Object sucsseccfuly sent");
            ArrayList<Student> s = Domread();
            for (Student st : s) {
                System.out.println(st);
            }
            return Domread();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteStudent(long kol) {
        try {
            Domread();
            Domdelete(kol);


        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Object sucsseccfuly deleted");
        try {
            for (IClient c : clients)
                c.Update();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void registry(IClient client) {
        clients.add(client);
        System.out.println(this.clients.toString());
    }

    @Override
    public void unregistry(IClient client) throws RemoteException {
        clients.remove(client);
        System.out.println(this.clients.toString());
    }



    public void editStudent(long id, Student student) {
        try {
            editStudentXML(id, student);
            for (IClient c : clients)
                c.Update();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    @Override
    public ArrayList<Student> search(String ser, int mode) throws RemoteException {
        return null;
    }


    public static void main(String[] args) throws Exception {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("server", new Xmlserver());
            System.out.println("Server started");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}