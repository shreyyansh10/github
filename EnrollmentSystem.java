import java.util.Scanner;

class Course {
    String id;
    String name;
    int credits;

    Course(String id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }
}

class Enrollment {
    String studentId;
    Course course;
    boolean enrolled;

    Enrollment(String studentId, Course course) {
        this.studentId = studentId;
        this.course = course;
        this.enrolled = false;
    }

    void enroll() {
        enrolled = true;
    }

    void drop() {
        enrolled = false;
    }

    boolean isEnrolled() {
        return enrolled;
    }
}

public class EnrollmentSystem {
    private Enrollment[] enrollments;
    private Course[] courses;

    public EnrollmentSystem() {
        enrollments = new Enrollment[10];
        courses = new Course[10];
    }

    public void addCourse(String id, String name, int credits) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = new Course(id, name, credits);
                System.out.println("Course added: " + id);
                return;
            }
        }
        System.out.println("Course list is full.");
    }

    public void enrollStudent(String studentId, String courseId) {
        Course course = findCourse(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        for (int i = 0; i < enrollments.length; i++) {
            if (enrollments[i] == null) {
                enrollments[i] = new Enrollment(studentId, course);
                enrollments[i].enroll();
                System.out.println("Student enrolled in course: " + courseId);
                return;
            }
        }
        System.out.println("Enrollment list is full.");
    }

    public void dropCourse(String studentId, String courseId) {
        Enrollment enrollment = findEnrollment(studentId, courseId);
        if (enrollment == null) {
            System.out.println("Student or course not found.");
            return;
        }
        enrollment.drop();
        System.out.println("Course dropped: " + courseId);
    }

    public void viewStudentCourses(String studentId) {
        System.out.println("Courses for student " + studentId + ":");
        for (Enrollment enrollment : enrollments) {
            if (enrollment!= null && enrollment.studentId.equals(studentId) && enrollment.isEnrolled()) {
                System.out.println(enrollment.course.id + ": " + enrollment.course.name);
            }
        }
    }

    private Course findCourse(String courseId) {
        for (Course course : courses) {
            if (course!= null && course.id.equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    private Enrollment findEnrollment(String studentId, String courseId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment!= null && enrollment.studentId.equals(studentId) && enrollment.course.id.equals(courseId)) {
                return enrollment;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnrollmentSystem system = new EnrollmentSystem();

        system.addCourse("DSA", "ABOUT DSA", 3);
        system.addCourse("MATH", "CALCULATION", 4);

        while (true) {
            System.out.println("\nEnrollment System");
            System.out.println("1. Enroll in Course");
            System.out.println("2. Drop Course");
            System.out.println("3. View Student Courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course ID: ");
                    String courseId = scanner.nextLine();
                    system.enrollStudent(studentId, courseId);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextLine();
                    system.dropCourse(studentId, courseId);
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    system.viewStudentCourses(studentId);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}