package entities;       //måske POJOs?

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Car {

    public int id;
    public StringProperty name, horsepower;
    double price;

    public Car(int id, String name, double price, String horsepower) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.price = price;
        this.horsepower = new SimpleStringProperty(horsepower);
    }

    public Car(){
    }

    public void setName(String name){
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public StringProperty horsepowerProperty() {
        return horsepower;
    }

    public String getHorsepower() {
        return horsepower.get();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return name.get();
    }


}
