package services.lookup;

public class Student {
    private String lastname ="Ivanov";

    public void printLastName(){
        System.out.println("Lastname " + lastname);

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
