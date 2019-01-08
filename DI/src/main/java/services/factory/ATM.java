package services.factory;

public class ATM {
    private Book book;

    public ATM() {
        System.out.println("Entity ATM");
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
    public void  bookInformation(String nameBook){
        getBook().bookInformation(nameBook);
    }
}
