package services.factory;

public class BookFactory {
    private static BookFactory serviceFactory = new BookFactory();
    private ATM atm;

    public static Book getBook() {
        return new Book();
    }

    private BookFactory() {
    }

    public static BookFactory createInstance() {
        return serviceFactory;
    }


    public  ATM createATMService() {
        return atm ;
    }

}
