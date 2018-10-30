package books;

public class Main {


    public static void main(String[] args) {
        Author author1 = new Author("Kay Horstman","k.horstman@gmail.com",'m');
        Author author2 = new Author("Herbert Schildt","h.Schildt@gmail.com",'m');
        Author author3 = new Author("Joshua Bloch","JoshuaBloch@gmail.com",'m');
        Author author4 = new Author("Patrick Niemeyer,","JoshuaBloch@gmail.com",'m');
        Author author5 = new Author("Jonathan Knudsen","JoshuaBloch@gmail.com",'m');


        Book book = new Book("Learning Java",new Author[]{author4,
                author5}, 100.0);
        System.out.println(book);


        Book book2 = new Book("Effective Java",new Author[]{author3}, 200.0, 100);
        System.out.println(book2);
    }

}
