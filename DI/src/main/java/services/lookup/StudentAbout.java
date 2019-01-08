package services.lookup;

public class StudentAbout implements IStudent {
    private Student aboutStudent;



    public void setAboutStudent(Student aboutStudent) {
        this.aboutStudent = aboutStudent;
    }


    public Student lastNameStudent() {
        return this.aboutStudent;
    }

    public void about() {
        System.out.println("Info about student ");
        aboutStudent.printLastName();
    }
}
