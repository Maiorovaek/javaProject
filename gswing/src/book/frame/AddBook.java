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
    JButton create = new JButton("Add book");

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

    public AddBook(BookModel bookModel) {
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
                    setVisible(false);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddBook.this, "Error " + status);
                }


            }
        });
    }

    public boolean isValid(BookModel bookModel) {
        boolean result = true;

        StringBuilder strb = new StringBuilder();

if(bookName.getText().isEmpty()){
    strb.append("name book \n");
    result = false;
}
        if (bookAuthor.getText().isEmpty()) {

            strb.append("name author \n");
            result = false;
        }

        if (bookPrice.getText().isEmpty()) {
            strb.append(" price\n");
            result = false;

        }

        if (bookCount.getText().isEmpty()) {
                    strb.append(" count\n");
                    result = false;

                }
                if (bookData.getText().isEmpty() ) {
                    strb.append(" year\n");
                    result = false;
                }

        try {
         int valData =    Integer.parseInt(bookData.getText());
          int valPrice =  Integer.parseInt(bookPrice.getText());
          int valCount =   Integer.parseInt(bookCount.getText());
            if ((valData > 2019 || valData<1300) || valPrice <= 0 || valCount <= 0) {

                result = false;
            }
        } catch (NumberFormatException e) {
            strb.append("Price and count  must be Integer >0, data >1300 and <2019");
            result = false;
        }

                String str = "You entered incorrect: \n ";
                status = str + strb.toString();

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

