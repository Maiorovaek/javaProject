package book.frame;

import book.model.BookModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeListen implements ActionListener {
    private final JTable table;
    private final BookModel bookModel;


    public ChangeListen(JTable table, BookModel bookModel) {
        this.table = table;
        this.bookModel = bookModel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row != -1) {
            new EditBook(row, bookModel);


        }

        else{
            JOptionPane.showMessageDialog(null,"click on the row to edit\n");
        }

    }
}
