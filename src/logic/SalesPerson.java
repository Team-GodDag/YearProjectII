package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesPerson {
    public int id;
    public StringProperty firstname, lastname, email, address, phonenumber, limit;

    public SalesPerson(int id, String firstname, String lastname, String email,String address, String phonenumber, String limit) {
        this.id = id;
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.phonenumber = new SimpleStringProperty(phonenumber);
        this.limit = new SimpleStringProperty(limit);
    }

    public SalesPerson(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname.get();
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
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

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhonenumber() {
        return phonenumber.get();
    }

    public StringProperty phonenumberProperty() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber.set(phonenumber);
    }

    public String getLimit() {
        return limit.get();
    }

    public StringProperty limitProperty() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit.set(limit);
    }
}
