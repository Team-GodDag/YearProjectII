package entities;

import java.util.ArrayList;

public class CustomerEntity {     //lavet af Rikke    Skal den bare hedde Customer?

    private int id;
    private String cpr, firstName, lastName, email, address, phone; //skal vel bare være en String, og så kan den blive en Property i logik?

    public CustomerEntity(int id, String cpr, String firstname, String lastname, String email, String address, String phone) {
        this.id = id;
        this.cpr = cpr;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpr() {
        return cpr;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    //SETTERS
    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
