package book.frame;
import book.model.BookModel;
import book.entity.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddBook extends JFrame {
     Book newBook = new Book();
    String status;
    BookModel bookModel;
    JTextField bookAuthor = new JTextField("", 40);
    JTextField bookName = new JTextField("", 40);
    JTextField bookPrice = new JTextField("", 20);
    JTextField bookCount = new JTextField("", 10);
    JTextField bookData = new JTextField("", 5);
    JButton create = new JButton("Add book") ;

    public AddBook() {
        super("Add Book");
        setSize(550, 220);
        setLocation(300, 300);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        jPanel.add(new JLabel("Name book"));
        jPanel.add(bookName);
        jPanel.add(new JLabel("Book author"));
        jPanel.add(bookAuthor);
        jPanel.add(new JLabel("Price"));
        jPanel.add(bookPrice);
        jPanel.add(new JLabel("Count"));
        jPanel.add(bookCount);
        jPanel.add(new JLabel("Year"));
        jPanel.add(bookData);

        jPanel.add(create);
        setContentPane(jPanel);

        setVisible(false);

        dispose();
        setVisible(true);

    }

    public AddBook(BookModel bookModel){
            this();

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValid(bookModel)) {
                    newBook.setName(bookName.getText());
                    newBook.setAuthor(bookAuthor.getText());
                    newBook.setPrice(Integer.parseInt(bookPrice.getText()));
                    newBook.setCount(Integer.parseInt(bookCount.getText()));
                    newBook.setData(Integer.parseInt(bookData.getText()));
                    saveToFile(newBook);
                    bookModel.addBook(newBook);

                } else {
                    JOptionPane.showMessageDialog(AddBook.this, "Error " + status);
                }
                setVisible(true);
                dispose();

            }
        });
    }
            public boolean isValid(BookModel bookModel) {
                boolean result = true;
                String regExpStr = "^[a-zA-Z\\\\s]{1,}$";
                String regExpInt = "^[0-9]{1,}$";

                StringBuilder strb = new StringBuilder();


                if (!(bookAuthor.getText().matches(regExpStr))) {
                    strb.append("You entered  incorrect name author");
                    result = false;
                }

                if (!(bookPrice.getText().matches(regExpInt) & (Integer.parseInt(bookPrice.getText()) > 0))) {
                    strb.append("You entered incorrect price.");
                    result = false;
                }
                if (!(Integer.parseInt(bookCount.getText()) > 0)) {
                    strb.append("You entered incorrect count");
                    result = false;

                }

                if (!((Integer.parseInt(bookData.getText()) > 1300) && (Integer.parseInt(bookData.getText()) < 2019))) {
                    strb.append("You entered incorrect year");
                    result = false;

                }
                status = strb.toString();

                return result;
            }

            public void saveToFile(Book b) {
                try (FileWriter writer = new FileWriter("Library.txt", true)) {
                    b = new Book(
                            bookName.getText(),
                            bookAuthor.getText(),
                            Integer.parseInt(bookPrice.getText()),
                            Integer.parseInt(bookCount.getText()),
                            Integer.parseInt(bookData.getText()));
                    writer.write(b.toString());
                    writer.append('\n');
                    writer.flush();

                } catch (IOException e) {
                    e.fillInStackTrace();
                }
            }
        }

