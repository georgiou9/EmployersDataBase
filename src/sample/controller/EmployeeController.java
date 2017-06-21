package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import sample.model.Employee;
import sample.model.EmployeeDAO;

import javafx.event.ActionEvent;
import java.sql.SQLException;


public class EmployeeController {
    @FXML
    private TextField empIdText;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField newEmailText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField IDText;
    @FXML
    private TextField extensionText;
    @FXML
    private TextField officeText;
    @FXML
    private TextField reportsToText;
    @FXML
    private TextField jobTitleText;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> empIdColumn;
    @FXML
    private TableColumn<Employee, String> empNameColumn;
    @FXML
    private TableColumn<Employee, String> empLastNameColumn;
    @FXML
    private TableColumn<Employee, String> empEmailColumn;
    @FXML
    private TableColumn<Employee, String> empJobTitleColumn;
    @FXML
    private TableColumn<Employee, Integer> empOfficeNoColumn;


    // Search an Employee
    @FXML
    private void searchEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            // Get Employee information
            Employee emp = EmployeeDAO.searchEmployee(empIdText.getText());
            // Populate Employee on TableView and Display on TextArea
            populateAndShowEmployee(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting employee information from DB. \n" + e);
            throw e;
        }
    }


    // Search all Employees
    @FXML
    private void searchEmployees(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            // Get * Employees information
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();
            populateEmployees(empData);
        } catch (SQLException e) {
            System.out.println("Error occured while getting employees information from DB. \n" + e);
            throw e;
        }
    }


    // Initializing the controller class
    // This method is automatically called after the fxml file has been loaded
    private void initialize() {
        empIdColumn.setCellValueFactory(cellData -> cellData.getValue().employee_idProperty().asObject());
        empNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        empLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        empEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        empJobTitleColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        empOfficeNoColumn.setCellValueFactory(cellData -> cellData.getValue().officeCodeProperty().asObject());
    }

    // Populate Employee
    @FXML
    private void populateEmployee (Employee emp) {
        // Declare ObservableList for table view
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        // Add Employee to the Observable List
        empData.add(emp);
        // Set items to the employeeTable
        employeeTable.setItems(empData);
    }

    // Set Employee Information to Area text
    @FXML
    private void setEmpInfoToTextArea (Employee emp) {
        resultArea.setText("First Name: " + emp.getFirstName() + " /n Last Name: " + emp.getLastName());
    }

    //Populate Employee for TableView and Display Employee on TextArea
    @FXML
    private void populateAndShowEmployee (Employee emp) {
        if (emp != null) {
            populateEmployee(emp);
            setEmpInfoToTextArea(emp);
        }
    }

    // Populate Employees for TebleView
    @FXML
    private void populateEmployees (ObservableList<Employee> empData) {
        // Set items to the employeeTable
        employeeTable.setItems(empData);
    }

    // Updates an Employee's email
    @FXML
    private void updateEmployeeEmail (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.updateEmpEmail(empIdText.getText(), empEmailColumn.getText());
            resultArea.setText("Email has been updated for employee id: " + empIdText.getText() +" \n ");
        } catch(SQLException e) {
            resultArea.setText("Problem occurred while updating the Email: " + e);
        }
    }

    // Insert an Employee to DB
    @FXML
    private void insertEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.insertEmp(empIdText.getText(), empNameColumn.getText(), empLastNameColumn.getText(),
                    empEmailColumn.getText(), extensionText.getText(), empOfficeNoColumn.getText(), reportsToText.getText(), jobTitleText.getText());
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting employee: " + e);
            throw e;
        }
    }
    // Delete an Employee with given ID
    @FXML
    private void deleteEmployee (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            EmployeeDAO.deleteEmpWithID(empIdText.getText());
            resultArea.setText("Employee deleted: Employee Id: " + empIdText.getText());
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting Employee: " + e);
            throw e;
        }
    }
}
