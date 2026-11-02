package XMLFile.Main;

import java.util.List;

import XMLFile.dao.EmployeeXmlDao;
import XMLFile.model.EmployeeDTO;

public class EmployeeDetails {

    public static void main(String[] args) {

        EmployeeXmlDao dao = new EmployeeXmlDao();

        List<EmployeeDTO> employees = dao.getAllEmployees();

        for (EmployeeDTO employee : employees) {

            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Employee Age: " + employee.getAge());
            System.out.println("Department: " + employee.getDepartment());

            System.out.println("------------------------");
        }
    }
}