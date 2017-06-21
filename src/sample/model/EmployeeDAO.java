package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DButil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by George G.
 */
public class EmployeeDAO {

    // Select an Employee
    public static Employee searchEmployee (String empID) throws SQLException, ClassNotFoundException {
        // Declare a SELECT statement
        String selectStmt = "SELECT * FROM employees WHERE employeeNumber = " + empID;

        // Execute rsEmp statement
        try {
            // Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DButil.dbExcecuteQuery(selectStmt);

            // Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Employee employee = getEmployeeFromResultSet(rsEmp);
            
            //Return Employee object
            return employee;
            
        } catch (SQLException e) {
            System.out.println("SQL operation failed: "+ e);
            // Return exception
            throw e;
        }
    }

    // Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object
    public static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee emp = null;
        if (rs.next()) {
            emp = new Employee();
            emp.setEmployee_id(rs.getInt("employeeNumber"));
            emp.setFirstName(rs.getString("firstName"));
            emp.setLastName(rs.getString("lastName"));
            emp.setEmail(rs.getString("email"));
            emp.setTitle(rs.getString("jobTitle"));
            emp.setOfficeCode(rs.getInt("officeCode"));
        }
        return emp;
    }

    // SELECT Employees
    public static ObservableList<Employee> searchEmployees() throws SQLException, ClassNotFoundException {
        // Declare a SELECT statement
        String selectStmt = "SELECT * FROM employees";
        // Execute SELECT statement
        try {
            // Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DButil.dbExcecuteQuery(selectStmt);

            // Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Employee> empList = getEmployeeList(rsEmps);

            // Return Employee object
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL SELECT operation failed: " + e);
            throw e;
        }
    }

    // SELECT * from employees operation
    private static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException {
        // Declare an observable List which comprises of Employee objects
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        while (rs.next()) {
            Employee emp = new Employee();
            emp.setEmployee_id(rs.getInt("employeeNumber"));
            emp.setFirstName(rs.getString("firstName"));
            emp.setLastName(rs.getString("lastName"));
            emp.setEmail(rs.getString("email"));
            emp.setTitle(rs.getString("jobTitle"));
            emp.setOfficeCode(rs.getInt("officeCode"));
            // Add Employee to the observableList
            empList.add(emp);
        }
        return empList;
    }

    // UPDATE an employee's email address
     public static void updateEmpEmail (String empId, String empEmail) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "UPDATE employees \n" +
                "SET email = '" + empEmail +"' \n" +
                "WHERE employees.employeeNumber = " + empId +" \n";

        try {
            DButil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
     }

     //DELETE an employee
    public static void deleteEmpWithID (String empId) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "DELETE FROM employees \n" +
                "WHERE employees.employeeNumber = " + empId;

        try {
            DButil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    // INSERT an employee
    public static void insertEmp (String empNumber, String name, String lastname, String email, String extension, String officeCode, String reportsTo, String jobTitle) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "INSERT INTO employees \n" +
                        "(`employeeNumber`, `lastName`, `firstName`, `extension`, `email`, `officeCode`, `reportsTo`, `jobTitle`)" +
                        "VALUES '" + empNumber + "', '" + lastname + "', '" + name + "', '" + extension + "', '" + officeCode + "', '" + reportsTo + "', '" + jobTitle + "');";

        try {
            DButil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}
