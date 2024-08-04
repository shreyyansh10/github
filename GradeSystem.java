import java.util.Scanner;

public class GradeSystem {
    private Student[] students;
    private Grade[] grades;

    public GradeSystem() {
        students = new Student[10];
        grades = new Grade[10];
    }

    public void addStudent(String id, String name) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = new Student(id, name);
                System.out.println("Student added: " + name);
                return;
            }
        }
        System.out.println("Student list is full.");
    }

    public void addGrade(String id, String course, double grade) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == null) {
                grades[i] = new Grade(id, course, grade);
                System.out.println("Grade added for " + id + " in course " + course);
                return;
            }
        }
        System.out.println("Grade list is full.");
    }

    public void calculateGPA(String id) {
        double total = 0;
        int count = 0;
        for (Grade g : grades) {
            if (g!= null && g.studentID.equals(id)) {
                total += g.grade;
                count++;
            }
        }
        if (count > 0) {
            System.out.printf("GPA for student ID %s: %.2f%n", id, total / count);
        } else {
            System.out.println("No grades found for student ID " + id);
        }
    }

    public void generateReport(String id) {
        Student student = findStudent(id);
        if (student!= null) {
            System.out.println("Grade Report for " + student.getName());
            System.out.println("Courses and Grades:");
            boolean hasGrades = false;
            for (Grade g : grades) {
                if (g!= null && g.studentID.equals(id)) {
                    System.out.println("Course ID: " + g.courseID + ", Grade: " + g.grade);
                    hasGrades = true;
                }
            }
            if (!hasGrades) {
                System.out.println("No grades recorded.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudent(String id) {
        for (Student s : students) {
            if (s!= null && s.studentID.equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeSystem system = new GradeSystem();

        while (true) {
            System.out.println("\nGrade System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Calculate GPA");
            System.out.println("4. Generate Grade Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    system.addStudent(id, name);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter course ID: ");
                    String course = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine();
                    system.addGrade(id, course, grade);
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    id = scanner.nextLine();
                    system.calculateGPA(id);
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    id = scanner.nextLine();
                    system.generateReport(id);
                    break;

                case 5:
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

class Student {
    String studentID;
    String name;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    String studentID;
    String courseID;
    double grade;

    Grade(String studentID, String courseID, double grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }
}