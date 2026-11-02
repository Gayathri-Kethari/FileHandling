package H2DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeDAO {

    private static final Logger logger =
            LogManager.getLogger(EmployeeDAO.class);

    // CREATE
    public int insertEmployee(Employeedto employee) throws SQLException {

        String query = "INSERT INTO Employee (id, name, email, salary) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getEmail());
            ps.setDouble(4, employee.getSalary());

            int rows = ps.executeUpdate();

            logger.info("{} employee record inserted successfully",
                    rows);

            return rows;
        }
    }

    // READ ALL
    public List<Employeedto> getAllEmployees() throws SQLException {

        List<Employeedto> employees = new ArrayList<>();

        String query = "SELECT * FROM Employee";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Employeedto employee = new Employeedto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDouble("salary")
                );

                employees.add(employee);
            }

            logger.info("Total employees retrieved: {}",
                    employees.size());
        }

        return employees;
    }

    // READ BY ID
    public Employeedto getEmployeeById(int id) throws SQLException {

        String query = "SELECT * FROM Employee WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    logger.info("Employee found with ID: {}", id);

                    return new Employeedto(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getDouble("salary")
                    );
                }
            }
        }

        logger.warn("Employee not found with ID: {}", id);

        return null;
    }

    // UPDATE
    public int updateEmployee(Employeedto employee) throws SQLException {

        String query = "UPDATE Employee SET name = ?, email = ?, salary = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setDouble(3, employee.getSalary());
            ps.setInt(4, employee.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                logger.info("Employee updated successfully. ID: {}",
                        employee.getId());
            } else {
                logger.warn("No employee found to update. ID: {}",
                        employee.getId());
            }

            return rows;
        }
    }

    // DELETE
    public int deleteEmployee(int id) throws SQLException {

        String query = "DELETE FROM Employee WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                logger.info("Employee deleted successfully. ID: {}", id);
            } else {
                logger.warn("No employee found to delete. ID: {}", id);
            }

            return rows;
        }
    }
}
