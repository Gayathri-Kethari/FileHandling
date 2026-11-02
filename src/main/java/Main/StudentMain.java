package Main;
import java.util.List;
import java.util.Scanner;

import DAO.StudentDAO;
import DAO.StudentImp;
import Model.Student;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class StudentMain {

    private static final Logger logger =
            LogManager.getLogger(StudentMain.class);

    public static void main(String[] args) {
    	Configurator.setRootLevel(Level.INFO);

        Scanner sc = new Scanner(System.in);

        StudentDAO dao = new StudentImp();

        while (true) {

            logger.info("\n===== STUDENT MANAGEMENT SYSTEM =====");
            logger.info("1. Insert Student");
            logger.info("2. View All Students");
            logger.info("3. Update Student");
            logger.info("4. Delete Student");
            logger.info("5. Exit");

            logger.info("Enter your choice:");

            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                logger.info("Enter Student ID:");
                int id = sc.nextInt();

                sc.nextLine();

                logger.info("Enter Student Name:");
                String name = sc.nextLine();

                logger.info("Enter Email:");
                String email = sc.nextLine();

                logger.info("Enter Course:");
                String course = sc.nextLine();

                logger.info("Enter Marks:");
                int marks = sc.nextInt();

                Student student = new Student(
                        id, name, email, course, marks);

                int insertResult = dao.insertStudent(student);

                if (insertResult > 0) {
                    logger.info("Student inserted successfully!");
                } else {
                    logger.warn("Student insertion failed!");
                }

                break;

            case 2:

                List<Student> students = dao.getAllStudents();

                for (Student s : students) {

                    logger.info(
                            "ID: {}, Name: {}, Email: {}, Course: {}, Marks: {}",
                            s.getId(),
                            s.getName(),
                            s.getEmail(),
                            s.getCourse(),
                            s.getMarks()
                    );
                }

                break;

            case 3:

                logger.info("Enter Student ID to update:");
                int updateId = sc.nextInt();

                sc.nextLine();

                logger.info("Enter New Name:");
                String newName = sc.nextLine();

                logger.info("Enter New Email:");
                String newEmail = sc.nextLine();

                logger.info("Enter New Course:");
                String newCourse = sc.nextLine();

                logger.info("Enter New Marks:");
                int newMarks = sc.nextInt();

                Student updateStudent = new Student(
                        updateId, newName, newEmail,
                        newCourse, newMarks);

                int updateResult = dao.updateStudent(updateStudent);

                if (updateResult > 0) {
                    logger.info("Student updated successfully!");
                } else {
                    logger.warn("Student update failed!");
                }

                break;

            case 4:

                logger.info("Enter Student ID to delete:");
                int deleteId = sc.nextInt();

                int deleteResult = dao.deleteStudent(deleteId);

                if (deleteResult > 0) {
                    logger.info("Student deleted successfully!");
                } else {
                    logger.warn("Student deletion failed!");
                }

                break;

            case 5:

                logger.info("Exiting Student Management System.");
                sc.close();
                return;

            default:

                logger.warn("Invalid choice! Try again.");
            }
        }
    }
}