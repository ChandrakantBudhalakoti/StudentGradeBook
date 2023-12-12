import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private String id;
    private ArrayList<Subject> subjects;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.subjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void addMarks(Subject subject) {
        subjects.add(subject);
    }

    public float calculateAverage() {
        float totalMarks = 0;
        for (Subject subject : subjects) {
            totalMarks += subject.getMarks();
        }
        return totalMarks / subjects.size();
    }

    public String calculateGrade() {
        float averageMarks = calculateAverage();

        if (averageMarks >= 60) {
            return "A";
        } else if (averageMarks >= 50) {
            return "B";
        } else if (averageMarks >= 33) {
            return "C";
        } else {
            return "Fail";
        }
    }
}

class Subject {
    private String name;
    private float marks;

    public Subject(String name, float marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }
}

public class Gradebook {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");   
            System.out.println("2. Add Subject to Student");
            System.out.println("3. Display Average");
            System.out.println("4. Change Marks");
            System.out.println("5. Display Grades");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    System.out.println("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = new Student(studentName, studentId);
                    students.add(student);
                    break;
                case 2:
                    System.out.println("Enter Student Name: ");
                    String studentName1 = scanner.nextLine();
                    Student foundStudent = getStudentByName(students, studentName1);
                    if (foundStudent != null) {
                        System.out.println("Enter Subject Name: ");
                        String subjectName = scanner.nextLine();
                        System.out.println("Enter Subject Marks: ");
                        float subjectMarks = scanner.nextFloat();
                        Subject subject = new Subject(subjectName, subjectMarks);
                        foundStudent.addSubject(subject);
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students found. Add students first.");
                    } else {
                        for (Student currentStudent : students) {
                            ArrayList<Subject> subjects = currentStudent.getSubjects();
                            if (!subjects.isEmpty()) {
                                float average = currentStudent.calculateAverage();
                                System.out.println("Average marks for " + currentStudent.getName() + ": " + average);
                            } else {
                                System.out.println("No subjects found for " + currentStudent.getName());
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter Student Name: ");
                    String studentName2 = scanner.nextLine();
                    Student student2 = getStudentByName(students, studentName2);
                    if (student2 != null) {
                        System.out.println("Enter Subject Name: ");
                        String subjectName2 = scanner.nextLine();
                        Subject subject = getSubjectByName(student2, subjectName2);
                        if (subject != null) {
                            System.out.println("Enter New Marks: ");
                            float newMarks = scanner.nextFloat();
                            subject.setMarks(newMarks);
                        } else {
                            System.out.println("Subject not found");
                        }
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 5:
                    if (students.isEmpty()) {
                        System.out.println("No students found. Add students first.");
                    } else {
                        for (Student currentStudent : students) {
                            String grade = currentStudent.calculateGrade();
                            System.out.println("Grade for " + currentStudent.getName() + ": " + grade);
                        }
                    }
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
                    break;
            }
        }
    }

    private static Student getStudentByName(ArrayList<Student> students, String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    private static Subject getSubjectByName(Student student, String name) {
        for (Subject subject : student.getSubjects()) {
            if (subject.getName().equals(name)) {
                return subject;
            }
        }
        return null;
    }
}
