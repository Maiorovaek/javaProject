package book.frame;
import book.model.BookModel;
import book.entity.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBook extends AddBook {

    public EditBook(int row, BookModel bookModel) {
        super();
        super.setTitle("Edit book");
        super.bookModel = bookModel;

        bookName.setText(bookModel.getBooks().get(row).getName());
        bookAuthor.setText(bookModel.getBooks().get(row).getAuthor());
        bookPrice.setText(Integer.toString(bookModel.getBooks().get(row).getPrice()));
        bookCount.setText(Integer.toString(bookModel.getBooks().get(row).getCount()));
        bookData.setText(Integer.toString(bookModel.getBooks().get(row).getData()));
        create.setText("Edit book");


        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (EditBook.super.isValid(bookModel)) {
                    Book newBooks = new Book();
                    bookModel.getBooks().set(row, newBooks);
                    newBooks.setName(bookName.getText());
                    newBooks.setAuthor(bookAuthor.getText());
                    newBooks.setPrice(Integer.parseInt(bookPrice.getText()));
                    newBooks.setCount(Integer.parseInt(bookCount.getText()));
                    newBook.setData(Integer.parseInt(bookData.getText()));
                    saveToFile(newBooks);
                      setVisible(false);
                    dispose();
                }
            }
        });
    }
}