import java.util.*;

// ---------- Abstract Base Class ----------
abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public String getName() { return name; }
    public int getId() { return id; }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", Salary=" + calculateSalary() + "]";
    }
}

// ---------- Full-Time Employee ----------
class FullTimeEmployee extends Employee {
    private double monthlySalary;
    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }
    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

// ---------- Part-Time Employee ----------
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;
    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

// ---------- Payroll System ----------
class PayrollSystem {
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Employee added successfully.");
    }

    public void removeEmployee(int id) {
        Employee toRemove = null;
        for (Employee e : employeeList) {
            if (e.getId() == id) { toRemove = e; break; }
        }
        if (toRemove != null) {
            employeeList.remove(toRemove);
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        System.out.println("\n--- Employee Details ---");
        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }
}

// ---------- Main (Menu-Driven) ----------
public class EmployeePayrollMainC {
    private static final Scanner sc = new Scanner(System.in);
    private static final PayrollSystem payroll = new PayrollSystem();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": addEmployee(); break;
                case "2": removeEmployee(); break;
                case "3": payroll.displayEmployees(); 
                case "0": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== EMPLOYEE PAYROLL MENU =====");
        System.out.println("1) Add Employee");
        System.out.println("2) Remove Employee");
        System.out.println("3) Display All Employees");
        System.out.println("0) Exit");
        System.out.print("Enter choice: ");
    }

    private static void addEmployee() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Employee Type (F=FullTime, P=PartTime): ");
            String type = sc.nextLine().trim().toUpperCase();

            if (type.equals("F")) {
                System.out.print("Monthly Salary: ");
                double salary = Double.parseDouble(sc.nextLine());
                payroll.addEmployee(new FullTimeEmployee(name, id, salary));
            } else if (type.equals("P")) {
                System.out.print("Hours Worked: ");
                int hours = Integer.parseInt(sc.nextLine());
                System.out.print("Hourly Rate: ");
                double rate = Double.parseDouble(sc.nextLine());
                payroll.addEmployee(new PartTimeEmployee(name, id, hours, rate));
            } else {
                System.out.println("Invalid type. Employee not added.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers for ID, salary, etc.");
        }
    }

    private static void removeEmployee() {
        try {
            System.out.print("Enter Employee ID to remove: ");
            int id = Integer.parseInt(sc.nextLine());
            payroll.removeEmployee(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }
}
