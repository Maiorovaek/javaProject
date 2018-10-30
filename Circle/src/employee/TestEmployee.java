package employee;

public class TestEmployee {
    public static void main(String[] args) {
        Employee emp = new Employee(1, "Kate", "Maiorova", 50000);
        System.out.println(emp.getName());
        System.out.println(emp);
        System.out.println("Annual = " + emp.getAnnualSalary());
        System.out.println(emp.raiseSalary(10));
    }
}
