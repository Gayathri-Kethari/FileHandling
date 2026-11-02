package XMLFile.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import XMLFile.model.StudentDTO;

public class StudentXMLDAO implements StudentDAO {

    private static final Logger logger =
            LogManager.getLogger(StudentXMLDAO.class);

    @Override
    public List<StudentDTO> getAllStudent() {

        List<StudentDTO> allStudent = new ArrayList<>();

        try {
            File f = new File("src/main/resources/student.xml");

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder db = factory.newDocumentBuilder();

            Document d = db.parse(f);

            d.getDocumentElement().normalize();

            NodeList list = d.getElementsByTagName("student");

            for (int i = 0; i < list.getLength(); i++) {

                Element student = (Element) list.item(i);

                int id = Integer.parseInt(
                        student.getElementsByTagName("id")
                                .item(0).getTextContent());

                String name = student.getElementsByTagName("name")
                        .item(0).getTextContent();

                String email = student.getElementsByTagName("email")
                        .item(0).getTextContent();

                StudentDTO studentDTO =
                        new StudentDTO(id, name, email);

                allStudent.add(studentDTO);
            }

        } catch (Exception e) {
            logger.error("Error while reading student XML file", e);
        }

        return allStudent;
    }
}