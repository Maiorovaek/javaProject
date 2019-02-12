package sample;

import java.io.Serializable;
import java.util.Objects;


public class Student implements Serializable {

    private long gradebookNumber;
    private String name;
    private String surname;
    private Department departmet;
    private double averageScore;


    public enum Department {
        AppliedMathematics,
        InformationalRadiosystems,
        Chemistry,
        ForeignLanguages;
    }


//    public Student() {
//    }

    public long getGradebookNumber() {
        return gradebookNumber;
    }

    public void setGradebookNumber(long gradebookNumber) {
        this.gradebookNumber = gradebookNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Department getDepartmet() {
        return departmet;
    }

    public void setDepartmet(Department departmet) {
        this.departmet = departmet;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return gradebookNumber == student.gradebookNumber &&
//                Double.compare(student.averageScore, averageScore) == 0 &&
//                Objects.equals(name, student.name) &&
//                Objects.equals(surname, student.surname) &&
//                departmet == student.departmet;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(gradebookNumber, name, surname, departmet, averageScore);
//    }

    public Student(long gradebookNumber, String name, String surname, Department departmet, double averageScore) {
        this.gradebookNumber = gradebookNumber;
        this.name = name;
        this.surname = surname;
        this.departmet = departmet;
        this.averageScore = averageScore;
    }


    @Override
    public String toString() {
        return "Student{" +
                "gradebookNumber=" + gradebookNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", departmet=" + departmet +
                ", averageScore=" + averageScore +
                '}';
    }
}
