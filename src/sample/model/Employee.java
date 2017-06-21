package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by George G.
 */
public class Employee {
    // Declare Employees table columns
    private IntegerProperty employee_id;
    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty extension;
    private StringProperty email;
    private IntegerProperty officeCode;
    private IntegerProperty reportsTo;
    private StringProperty title;

    public Employee() {
        this.employee_id = new SimpleIntegerProperty();
        this.lastName = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.extension = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.officeCode = new SimpleIntegerProperty();
        this.reportsTo = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getExtension() {
        return extension.get();
    }

    public StringProperty extensionProperty() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension.set(extension);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getOfficeCode() {
        return officeCode.get();
    }

    public IntegerProperty officeCodeProperty() {
        return officeCode;
    }

    public void setOfficeCode(int officeCode) {
        this.officeCode.set(officeCode);
    }

    public int getReportsTo() {
        return reportsTo.get();
    }

    public IntegerProperty reportsToProperty() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo.set(reportsTo);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getEmployee_id() {

        return employee_id.get();
    }

    public IntegerProperty employee_idProperty() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id.set(employee_id);
    }
}
