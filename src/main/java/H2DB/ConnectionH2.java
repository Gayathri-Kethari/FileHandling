package H2DB;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class ConnectionH2 {

    private static final Logger logger =
            LogManager.getLogger(ConnectionH2.class);

    public static void main(String[] args) {
    	Configurator.setRootLevel(Level.INFO);

        EmployeeDAO dao = new EmployeeDAO();

        
        logger.info("             EMPLOYEE MANAGEMENT SYSTEM           ");
        logger.info("-----------------------------------------------");

        try {

            // CREATE
            logger.info("");
            logger.info("---------------- CREATE OPERATION ----------------");

            Employeedto newEmployee = new Employeedto(
                    12, "Kavya", "kavya@gmail.com", 40000);

            int inserted = dao.insertEmployee(newEmployee);

            if (inserted > 0) {
                logger.info("[SUCCESS] Employee inserted successfully.");
            }

            // READ BY ID
            logger.info("");
            logger.info("---------------- READ OPERATION ------------------");

            Employeedto employee = dao.getEmployeeById(12);

            if (employee != null) {

                logger.info("[SUCCESS] Employee found.");
                logger.info("ID      : {}", employee.getId());
                logger.info("Name    : {}", employee.getName());
                logger.info("Email   : {}", employee.getEmail());
                logger.info("Salary  : {}", employee.getSalary());

            } else {
                logger.warn("[NOT FOUND] Employee does not exist.");
            }

            // UPDATE
            logger.info("");
            logger.info("---------------- UPDATE OPERATION ----------------");

            Employeedto updatedEmployee = new Employeedto(
                    12, "Kavya Sharma",
                    "kavya@gmail.com", 45000);

            int updated = dao.updateEmployee(updatedEmployee);

            if (updated > 0) {
                logger.info("[SUCCESS] Employee updated successfully.");
            } else {
                logger.warn("[NOT FOUND] Employee not available for update.");
            }

            // READ ALL
            logger.info("");
            logger.info("---------------- ALL EMPLOYEES -------------------");

            List<Employeedto> employees = dao.getAllEmployees();

            logger.info("Total Employees: {}", employees.size());

            logger.info(String.format(
                    "%-5s %-18s %-25s %12s",
                    "ID", "NAME", "EMAIL", "SALARY"));

            logger.info("--------------------------------------------------------------");

            for (Employeedto emp : employees) {

                logger.info(String.format(
                        "%-5d %-18s %-25s %12.2f",
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail(),
                        emp.getSalary()));
            }

            // DELETE
            logger.info("");
            logger.info("---------------- DELETE OPERATION ----------------");

            int deleted = dao.deleteEmployee(12);

            if (deleted > 0) {
                logger.info("[SUCCESS] Employee deleted successfully.");
            } else {
                logger.warn("[NOT FOUND] Employee not available for deletion.");
            }

        } catch (SQLException e) {

            logger.error("[ERROR] CRUD operation failed.", e);
        }

        logger.info("");
        logger.info("--------------------------------------------------");
        logger.info("              PROCESS COMPLETED                   ");
        logger.info("--------------------------------------------------");
    }
}
