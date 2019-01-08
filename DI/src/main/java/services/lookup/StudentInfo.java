package services.lookup;

public abstract class StudentInfo implements IStudent {
 public abstract Student getLastname();

    public void about() {
        System.out.println("Группа 446, НГТУ" );
        getLastname().printLastName();
    }
}
