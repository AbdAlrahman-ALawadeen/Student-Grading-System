package org.example.DAO;

import org.example.Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/grading?useSSL=false&serverTimezone=UTC";

    private static final String user = "root";
    private static final String password = "abdawadeen";

    private Connection connection;

    private static final String INSERT_STUDENT = "INSERT INTO STUDENT(firstName, lastName, email, password) VALUES(?, ?, ?, ?)";
    private static final String INSERT_TEACHER = "INSERT INTO TEACHER(firstName, lastName, email, password) VALUES(?, ?, ?, ?)";
    private static final String INSERT_ADMIN = "INSERT INTO ADMIN(firstName, lastName, email, password) VALUES(?, ?, ?, ?)";
    private static final String INSERT_COURSE = "INSERT INTO COURSES(name, teacherID) VALUES(?, ?)";
    private static final String ADD_GRADE = "INSERT INTO GRADES(grade, studentID, courseID) VALUES(?, ?, ?)";
    private static final String ENROLL_STUDENT = "INSERT INTO ENROLLMENTS(studentID, courseID) VALUES(?, ?)";
    private static final String SHOW_STUDENTS = "SELECT * FROM STUDENT";
    private static final String SHOW_ADMINS = "SELECT * FROM ADMIN";
    private static final String SHOW_TEACHERS = "SELECT * FROM TEACHER";
    private static final String SHOW_COURSES = "SELECT * FROM COURSES";
    private static final String SHOW_TEACHER_COURSES = "SELECT * FROM COURSES WHERE teacherID = (?)";
    private static final String SHOW_GRADES = "SELECT * FROM GRADES WHERE studentID = (?)";
    private static final String STUDENT_IN_COURSE = "SELECT * FROM ENROLLMENTS WHERE courseID = (?)";

    public DAO(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, user, password);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void addStudent(Student student){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)){
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher teacher){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEACHER)){
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getEmail());
            preparedStatement.setString(4, teacher.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addADMIN(Admin admin){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN)){
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addCourse(Course course){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE)){
            preparedStatement.setString(1, course.getName());
            preparedStatement.setLong(2, course.getTeacherID());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addGrade(Long studentID, Long courseID, int grade){
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_GRADE)){
            preparedStatement.setLong(1, grade);
            preparedStatement.setLong(2, studentID);
            preparedStatement.setLong(3, courseID);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void enrollStudent(Long studentID, Long courseID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ENROLL_STUDENT)){
            preparedStatement.setLong(1, studentID);
            preparedStatement.setLong(2, courseID);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Student> getStudents(){
        List<Student> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SHOW_STUDENTS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Student student = new Student(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                list.add(student);
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return list;
    }

    public List<Admin> getADMINS(){
        List<Admin> list = new ArrayList<>();
        System.out.println("Try");
        try (PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ADMINS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Admin admin = new Admin(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                list.add(admin);
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return list;
    }

    public List<Teacher> getTeachers(){
        List<Teacher> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SHOW_TEACHERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Teacher teacher = new Teacher(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                list.add(teacher);
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return list;
    }

    public List<Course> getCourses() {
        List<Course> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SHOW_COURSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString("name"),
                        resultSet.getLong("id"),
                        resultSet.getLong("teacherID")
                );
                list.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Student> getStudentInCourse(Long id){
        List<Student> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(STUDENT_IN_COURSE)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getLong("studentID")
                );
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Course> getTeacherCourses(Long id) {
        List<Course> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SHOW_TEACHER_COURSES)) {
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString("name"),
                        resultSet.getLong("id"),
                        resultSet.getLong("teacherID")
                );
                list.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Grade> getGrades(Long id) {
        List<Grade> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SHOW_GRADES)){
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Grade grade = new Grade(
                        resultSet.getInt("grade"),
                        resultSet.getLong("studentID"),
                        resultSet.getLong("courseID")
                );
                list.add(grade);
            }
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return list;
    }


    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
