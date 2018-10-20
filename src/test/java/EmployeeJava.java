import javatokotlin.Department;
import javatokotlin.Employee;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EmployeeJava {
    private int number;
    private int age;
    private Department department;
    private String name;

    public EmployeeJava(int number, int age, Department department, String name) {
        this.number = number;
        this.age = age;
        this.department = department;
        this.name = name;
    }

    public EmployeeJava(int number, int age, Department department) {
        this.number = number;
        this.age = age;
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //other methods that the Kotlin compiler automatically adds to data classes:
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Employee) {
            Employee employee = (Employee) other;
            return number == employee.getNumber()
                    && age == employee.getAge()
                    && department.equals(employee.getDepartment())
                    && name.equals(employee.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, age, department, name);
    }

    public String toString() {
        return "javatokotlin.Employee(number=" + this.number
                + ", age=" + this.age
                + ", department=" + this.department
                + ", name=" + this.name + ")";
    }

    public final EmployeeJava copy(EmployeeJava employee) {
        return new EmployeeJava(employee.number, employee.age, employee.department, employee.name);
    }
}

enum DepartmentJava {DEV, NOT_DEV}