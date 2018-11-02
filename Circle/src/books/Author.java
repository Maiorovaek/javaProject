package books;

import java.util.Objects;

public class Author {
    private String name;
    private String email;
    private char gender;
    public Author() {
    }

    public Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public int hashCode() {
        int result = 17;
        result = 31*result + gender;
        result = 31*result + name.hashCode();
        result = 31*result + email.hashCode();
        return result;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Author)) return false;
        Author author = (Author) obj;
        return author.equals(author.name) && author.equals(author.email) &&  gender == author.gender;
    }




    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
