package XMLFile.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import XMLFile.model.EmployeeDTO;

public class EmployeeXmlDao implements EmployeeDao {

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeDTO> employees = new ArrayList<>();

        try {
            File f = new File("src/main/resources/employee.xml");

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder db = factory.newDocumentBuilder();

            Document doc = db.parse(f);

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("employee");

            for (int i = 0; i < list.getLength(); i++) {

                Element employee = (Element) list.item(i);

                int id = Integer.parseInt(
                        employee.getElementsByTagName("id")
                                .item(0).getTextContent());

                String name = employee.getElementsByTagName("name")
                        .item(0).getTextContent();

                int age = Integer.parseInt(
                        employee.getElementsByTagName("age")
                                .item(0).getTextContent());

                String department = employee
                        .getElementsByTagName("department")
                        .item(0).getTextContent();

                EmployeeDTO dto =
                        new EmployeeDTO(id, name, age, department);

                employees.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}