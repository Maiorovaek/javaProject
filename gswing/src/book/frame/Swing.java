package book.frame;
import book.model.BookModel;
import book.entity.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.StringTokenizer;


public class Swing extends JFrame {
      BookModel bookModel = new BookModel();

    private JButton
            b1 = new JButton("Add book"),
            b2 = new JButton("Delete book"),
            b3 = new JButton("Edit book"),
            b4 = new JButton("Output list"),
            b5 = new JButton("Show");

    public Swing() {
        super("Library");

        setSize(550, 250);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable table = new JTable(bookModel);
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);
        JPanel flow = new JPanel();

        JPanel grid4 = new JPanel(new GridLayout(5, 1));
        grid4.add(b1);
        grid4.add(b2);
        grid4.add(b3);
        grid4.add(b4);
        grid4.add(b5);
        flow.add(grid4);
        add(grid4, BorderLayout.EAST);

        JPanel grid = new JPanel(new GridLayout(1, 1));
        grid.add(jScrollPane);
        flow.add(grid);
        add(grid, BorderLayout.CENTER);

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  new AddBook(bookModel);
                    table.updateUI();
                }
            });


            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = table.getSelectedRow();
                    if(i>=0){
                        bookModel.deleteBook(i);

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"click on the row to delete\n");
                    }
                }
            });


        b3.addActionListener(new ChangeListen(table,bookModel));

            b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookModel.clean();
                try(FileInputStream file = new FileInputStream("Library.txt")) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(file));
                    String buffer;
                    buffer = reader.readLine();

                    while (buffer != null) {
                        StringTokenizer st = new StringTokenizer(buffer, ",");
                        Book book = new Book();
                        book.setName(st.nextToken().trim());
                        book.setAuthor(st.nextToken().trim());
                        book.setPrice(Integer.parseInt(st.nextToken().trim()));
                        book.setCount(Integer.parseInt(st.nextToken().trim()));
                        book.setData(Integer.parseInt(st.nextToken().trim()));

                        bookModel.addBook(book);
                        buffer = reader.readLine();

                    }
                    reader.close();
                  //  table.updateUI();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            });



        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int ret = chooser.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                File file = new File("Library.txt");
                try{
                    FileReader fileReader = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fileReader);
                    bookModel.clean();
                    String buffer = reader.readLine();
                    while ( buffer != null){
                        Book book = new Book();
                        bookModel.addBook(book);
                        buffer = reader.readLine();
                    }
                    fileReader.close();
                    table.updateUI();
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }}
        });
        setVisible(true);
    }
}