package XMLFile.Main;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import XMLFile.dao.StudentXMLDAO;
import XMLFile.model.StudentDTO;

public class StudentDetails {

    private static final Logger logger =
            LogManager.getLogger(StudentDetails.class);

    public static void main(String[] args) {
    	Configurator.setRootLevel(Level.INFO);

        StudentXMLDAO dao = new StudentXMLDAO();

        List<StudentDTO> allStudent = dao.getAllStudent();

        for (StudentDTO student : allStudent) {

            logger.info("Student ID: {}", student.getId());

            logger.info("Student Name: {}", student.getName());

            logger.info("Student Email: {}", student.getEmail());

            logger.info("-----------------------------");
        }
    }
}