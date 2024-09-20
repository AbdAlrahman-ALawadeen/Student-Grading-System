package org.example.server;

import org.example.Authentication;
import org.example.DAO.DAO;
import org.example.Models.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.Objects;


public class Server {


    static DAO dao = new DAO();

    public static void main(String[] args) {
        System.out.println("Server has started.");
        try {
            ServerSocket serverSocket = new ServerSocket(5647);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    public static class ClientHandler extends Thread{
        private Socket socket;
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;
        String type;

        public ClientHandler(Socket socket){
            try {
                this.socket = socket;
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        public void run(){
            try {
                System.out.println("Client" + socket.getInetAddress().getHostAddress() + "connected");
                while(true){
                    Login();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        public void Login(){
            try {
                outputToClient.writeUTF("Enter 1: Admin, Enter 2: Teacher, Enter 3: Student");
                type = inputFromClient.readUTF();
                outputToClient.writeUTF("Enter your email: ");
                String email = inputFromClient.readUTF();
                outputToClient.writeUTF("Enter your password");
                String password = inputFromClient.readUTF();

                Long id = Authentication.authenticationUser(email, password, type);
                if(id != -1L){
                    switch (type) {
                        case "1" -> showAdminPage(id);
                        case "2" -> showTeacherPage(id);
                        case "3" -> showStudentPage(id);
                        default -> {
                            outputToClient.writeUTF("Please enter number from choices!");
                            Login();
                        }
                    }
                }
                else {
                    outputToClient.writeUTF("Login Failed!. Please, entered the correct email and password.");
                    outputToClient.flush();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        private void showAdminPage(Long id) throws IOException {
            outputToClient.writeUTF("Select an option: ");
            outputToClient.writeUTF("1: Show Courses.");
            outputToClient.writeUTF("2: Show Teachers");
            outputToClient.writeUTF("3: Show Admins");
            outputToClient.writeUTF("4: Show Students");
            outputToClient.writeUTF("5: Add Admin");
            outputToClient.writeUTF("6: Add Teacher");
            outputToClient.writeUTF("7: Add Student");
            outputToClient.writeUTF("8: Add Course");
            outputToClient.writeUTF("9: Add Student to Course");
            outputToClient.writeUTF("10: Sign out");

            String option = inputFromClient.readUTF();

            switch (option) {
                case "1" -> printCourses(dao.getCourses(), id);
                case "2" -> printTeachers(dao.getTeachers(), id);
                case "3" -> printAdmins(dao.getADMINS(), id);
                case "4" -> printStudents(dao.getStudents(), id);
                case "5" -> addAdmin(id);
                case "6" -> addTeacher(id);
                case "7" -> addStudent(id);
                case "8" -> addCourse(id);
                case "9" -> addStudentToCourse(id);
                case "10" -> {
                    outputToClient.writeUTF("See you soon, Good bye!");
                    Login();
                }
                default -> {
                    outputToClient.writeUTF("Please choose one of choices: ");
                    showAdminPage(id);
                }
            }
        }

        private void showTeacherPage(Long id) throws IOException {
            outputToClient.writeUTF("Select an option: ");
            outputToClient.writeUTF("1: Show Courses.");
            outputToClient.writeUTF("2: Add Grades");
            outputToClient.writeUTF("3: Sign out");

            String option = inputFromClient.readUTF();

            switch (option) {
                case "1" -> printCourses(dao.getTeacherCourses(id), id);
                case "2" -> addGrades(id);
                case "3" -> {
                    outputToClient.writeUTF("See you soon, Good bye!");
                    Login();
                }
                default -> {
                    outputToClient.writeUTF("Please choose one of choices: ");
                    showTeacherPage(id);
                }
            }
        }

        private void showStudentPage(Long id) throws IOException {
            outputToClient.writeUTF("Select an option: ");
            outputToClient.writeUTF("1: Show Courses.");
            outputToClient.writeUTF("2: Show Grades");
            outputToClient.writeUTF("3: Sign out");

            String option = inputFromClient.readUTF();

            switch (option) {
                case "1" -> printCourses(dao.getCourses(), id);
                case "2" -> printGrades(dao.getGrades(id), id);
                case "3" -> {
                    outputToClient.writeUTF("See you soon, Good bye!");
                    Login();
                }
                default -> {
                    outputToClient.writeUTF("Please choose one of choices: ");
                    showStudentPage(id);
                }
            }
        }

        private void addAdmin(Long id) throws IOException {
            outputToClient.writeUTF("Enter admin first name: ");
            String firstName = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter admin last name: ");
            String lastName = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter admin Email: ");
            String email = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter admin password: ");
            String password = inputFromClient.readUTF();

            dao.addADMIN(new Admin(firstName, lastName, email, password));
            outputToClient.writeUTF("Done!");

            showAdminPage(id);
        }

        private void addTeacher(Long id) throws IOException {
            outputToClient.writeUTF("Enter teacher first name: ");
            String firstName = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter teacher last name: ");
            String lastName = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter teacher Email: ");
            String email = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter teacher password: ");
            String password = inputFromClient.readUTF();

            dao.addTeacher(new Teacher(firstName, lastName, email, password));
            outputToClient.writeUTF("Done!");

            showAdminPage(id);
        }

        private void addStudent(Long id) throws IOException {
            outputToClient.writeUTF("Enter student first name: ");
            String firstName = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter student last name: ");
            String lastName = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter student Email: ");
            String email = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter student password: ");
            String password = inputFromClient.readUTF();

            dao.addStudent(new Student(firstName, lastName, email, password));
            outputToClient.writeUTF("Done!");

            showAdminPage(id);
        }

        private void addCourse(Long id) throws IOException {
            outputToClient.writeUTF("Enter course name: ");
            String name = inputFromClient.readUTF();

            outputToClient.writeUTF("Enter course teacher ID's: ");
            String temp = inputFromClient.readUTF();
            Long ID = Long.parseLong(temp);
            outputToClient.writeUTF("Press Enter to confirm");
            dao.addCourse(new Course(name, ID));
            outputToClient.writeUTF("Done!");

            showAdminPage(id);
        }

        private void addGrades(Long id) throws IOException {
            List<Course> courses = dao.getTeacherCourses(id);
            if(courses.isEmpty()){
                outputToClient.writeUTF("No courses found!");
            }
            else{
                outputToClient.writeUTF("Enter course ID: ");
                String tempID = inputFromClient.readUTF();
                if (!tempID.matches("[0-9]+")) {
                    outputToClient.writeUTF("Please enter a valid course id");
                    outputToClient.flush();
                    addGrades(id);
                }
                Long courseID = Long.parseLong(tempID);
                boolean flag = false;
                for(Course course : courses){
                    if (Objects.equals(course.getId(), courseID)) {
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    outputToClient.writeUTF("Course not Exist, Verify the course ID is correct.");
                }
                else{
                    List<Student> student = dao.getStudentInCourse(courseID);
                    outputToClient.writeUTF("Enter Student ID: ");
                    tempID = inputFromClient.readUTF();
                    if (!tempID.matches("[0-9]+")) {
                        outputToClient.writeUTF("Please enter a valid student id");
                        outputToClient.flush();
                        addGrades(id);
                    }
                    Long studentID = Long.parseLong(tempID);
                    flag = false;
                    for(Student student1 : student){
                        if (Objects.equals(student1.getId(), studentID)) {
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        outputToClient.writeUTF("Student not Register, Verify the student ID is correct.");
                        outputToClient.flush();
                    }
                    else{
                        outputToClient.writeUTF("Enter grade: ");
                        tempID = inputFromClient.readUTF();
                        if (!tempID.matches("[0-9]+")) {
                            outputToClient.writeUTF("Please enter a valid grade");
                            outputToClient.flush();
                            addGrades(id);
                        }
                        int grade = Integer.parseInt(tempID);

                        dao.addGrade(studentID, courseID, grade);
                        outputToClient.writeUTF("Done!");
                    }
                }
            }
            outputToClient.flush();
            showTeacherPage(id);
        }

        private void addStudentToCourse(Long id) throws IOException {
            List<Course> courses = dao.getCourses();
            if(courses.isEmpty()){
                outputToClient.writeUTF("No courses found!");
                outputToClient.flush();
                showAdminPage(id);
            }
            else{
                outputToClient.writeUTF("Enter course ID: ");
                String tempID = inputFromClient.readUTF();
                if (!tempID.matches("^[0-9]")) {
                    outputToClient.writeUTF("Please enter a valid course id");
                    addGrades(id);
                }
                Long courseID = Long.parseLong(tempID);
                boolean flag = false;
                for(Course course : courses){
                    if (Objects.equals(course.getId(), courseID)) {
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    outputToClient.writeUTF("Course not Exist, Verify the course ID is correct.");
                    showAdminPage(id);
                }
                else{
                    List<Student> student = dao.getStudents();
                    outputToClient.writeUTF("Enter Student ID: ");
                    tempID = inputFromClient.readUTF();
                    if (!tempID.matches("^[0-9]")) {
                        outputToClient.writeUTF("Please enter a valid student id");
                        addGrades(id);
                    }
                    Long studentID = Long.parseLong(tempID);
                    flag = false;
                    for(Student student1 : student){
                        if (Objects.equals(student1.getId(), studentID)) {
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        outputToClient.writeUTF("Student not Register, Verify the student ID is correct.");
                        showAdminPage(id);
                    }
                    else{
                        dao.enrollStudent(studentID, courseID);
                        outputToClient.writeUTF("Done!");
                        showAdminPage(id);
                    }
                }
            }
        }

        private void printGrades(List<Grade> grades, Long id) throws IOException {
            try {
                if (grades.isEmpty()) {
                    outputToClient.writeUTF("No courses found");
                } else {
                    outputToClient.writeUTF("Press enter to show the results");
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    outputToClient.writeUTF(String.format("%-10s %-20s\n", "COURSE ID", "GRADE"));
                    for (Grade grade : grades) {
                        outputToClient.writeUTF(String.format("%-10s %-20s\n", grade.getCourseID(), grade.getGrade()));
                    }
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                }
                outputToClient.flush();
                String read = inputFromClient.readUTF();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            showStudentPage(id);
        }

        private void printCourses(List<Course> courses, Long id) throws IOException {
            try {
                if (courses.isEmpty()) {
                    outputToClient.writeUTF("No courses found");
                } else {
                    outputToClient.writeUTF("Press enter to show the results");
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    outputToClient.writeUTF(String.format("%-10s %-20s\n", "ID", "Name"));
                    for (Course course : courses) {
                        outputToClient.writeUTF(String.format("%-10s %-20s\n", course.getId(), course.getName()));
                    }
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                }
                outputToClient.flush();
                String read = inputFromClient.readUTF();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if(type.equals("1")){
                showAdminPage(id);
            }
            else if(type.equals("2")){
                showTeacherPage(id);
            }
            else{
                showStudentPage(id);
            }
        }

        private void printTeachers(List<Teacher> teachers, Long id) throws IOException {
            try {
                if (teachers.isEmpty()) {
                    outputToClient.writeUTF("No teachers found");
                } else {
                    outputToClient.writeUTF("Press enter to show the results");
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    outputToClient.writeUTF(String.format("%-10s %-20s\n", "ID", "Name"));
                    for (Teacher teacher : teachers) {
                        outputToClient.writeUTF(String.format("%-10s %-20s\n", teacher.getId(), teacher.getFullName()));
                    }
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                outputToClient.flush();
                String read = inputFromClient.readUTF();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            showAdminPage(id);
        }

        private void printStudents(List<Student> students, Long id) throws IOException {
            try {
                if (students.isEmpty()) {
                    outputToClient.writeUTF("No students found");
                } else {
                    outputToClient.writeUTF("Press enter to show the results");
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    outputToClient.writeUTF(String.format("%-10s %-20s\n", "ID", "Name"));
                    for (Student student : students) {
                        outputToClient.writeUTF(String.format("%-10s %-20s\n", student.getId(), student.getFullName()));
                    }
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                }
                outputToClient.flush();
                String read = inputFromClient.readUTF();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            showAdminPage(id);
        }

        private void printAdmins(List<Admin> admins, Long id) throws IOException {
            try {
                if (admins.isEmpty()) {
                    outputToClient.writeUTF("No admins found");
                }
                else {
                    outputToClient.writeUTF("Press enter to show the results");
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    outputToClient.writeUTF(String.format("%-10s %-20s\n", "ID", "Name"));
                    for (Admin admin: admins) {
                        outputToClient.writeUTF(String.format("%-10s %-20s\n", admin.getId(), admin.getFullName()));
                    }
                    outputToClient.writeUTF("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                }
                outputToClient.flush();
                String read = inputFromClient.readUTF();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            showAdminPage(id);
        }
    }
}
