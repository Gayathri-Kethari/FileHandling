package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DBConnection;
import Model.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentImp implements StudentDAO {

    private static final Logger logger =
            LogManager.getLogger(StudentImp.class);

    // CREATE - Insert student
    @Override
    public int insertStudent(Student student) {

        String query = "INSERT INTO Student(id, name, email, course, marks) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getCourse());
            stmt.setInt(5, student.getMarks());

            int result = stmt.executeUpdate();

            logger.info("Student inserted successfully!");

            return result;

        } catch (SQLException e) {
            logger.error("Unable to insert student", e);
            return 0;
        }
    }

    // READ - Get all students
    @Override
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM Student";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getInt("marks")
                );

                students.add(student);
            }

            logger.info("Student records retrieved successfully.");

        } catch (SQLException e) {
            logger.error("Unable to retrieve students", e);
        }

        return students;
    }

    // UPDATE - Update student
    @Override
    public int updateStudent(Student student) {

        String query = "UPDATE Student SET name=?, email=?, course=?, marks=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getMarks());
            stmt.setInt(5, student.getId());

            int result = stmt.executeUpdate();

            logger.info("Student update operation completed.");

            return result;

        } catch (SQLException e) {
            logger.error("Unable to update student", e);
            return 0;
        }
    }

    // DELETE - Delete student
    @Override
    public int deleteStudent(int id) {

        String query = "DELETE FROM Student WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);

            int result = stmt.executeUpdate();

            logger.info("Student delete operation completed.");

            return result;

        } catch (SQLException e) {
            logger.error("Unable to delete student", e);
            return 0;
        }
    }
}