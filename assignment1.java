import java.util.Scanner;

class Student {
    private String studentID;
    private String name;
    private int age;
    private String department;

    public Student(String studentID, String name, int age, String department) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "ID: " + studentID + ", Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}

class StudentSystem {
    private java.util.Map<String, Student> studentRecords;

    public StudentSystem() {
        studentRecords = new java.util.HashMap<>();
    }

    public void addStudent(String studentID, String name, int age, String department) {
        if (studentRecords.containsKey(studentID)) {
            System.out.println("Student with ID " + studentID + " already exists.");
        } else {
            Student student = new Student(studentID, name, age, department);
            studentRecords.put(studentID, student);
            System.out.println("Student added successfully.");
        }
    }

    public void viewAllStudents() {
        if (studentRecords.isEmpty()) {
            System.out.println("No student records available.");
        } else {
            for (Student student : studentRecords.values()) {
                System.out.println(student);
            }
        }
    }

    public void searchStudentByID(String studentID) {
        Student student = studentRecords.get(studentID);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }
}

public class StudentRecordSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();

        while (true) {
            System.out.println("\nStudent Record System");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    studentSystem.addStudent(studentID, name, age, department);
                    break;

                case 2:
                    studentSystem.viewAllStudents();
                    break;

                case 3:
                    System.out.print("Enter student ID to search: ");
                    studentID = scanner.nextLine();
                    studentSystem.searchStudentByID(studentID);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
