package entities;


public class Customer {
    //Lavet af Rikke

    private int id;
    private String cpr, firstName, lastName, email, address, phone;
    private boolean isGoodGuy;

    public Customer(int id, String cpr, String firstname, String lastname, String email, String address, String phone, boolean isGoodGuy) {
        this.id = id;
        this.cpr = cpr;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.isGoodGuy = isGoodGuy;
    }

    public Customer(String cpr, String firstName, String lastName, String email, String address, String phone) {
        this(0, cpr, firstName, lastName, email, address, phone, true);
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

    public int isGoodGuyBit() {     //må den ligge her? det er vel en form for funktionalitet?
        if(!isGoodGuy) {
            return 0;
        } else
            return 1;
    }

    public boolean isGoodGuy() {
        return isGoodGuy;
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

    public void setGoodGuy(boolean goodGuy) {
        isGoodGuy = goodGuy;
    }

}
