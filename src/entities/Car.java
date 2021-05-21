package entities;       //måske POJOs?

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Car {

    public int id;
    public StringProperty model_name, horsepower;
    double price;

    public Car(int id, String model_name, double price, String horsepower) {
        this.id = id;
        this.model_name = new SimpleStringProperty(model_name);
        this.price = price;
        this.horsepower = new SimpleStringProperty(horsepower);
    }

    public Car(){
    }

    public void setModel_name(String model_name){
        this.model_name.set(model_name);
    }

    public StringProperty model_nameProperty() {
        return model_name;
    }

    public StringProperty getModel_name() {
        return model_name;
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
        return model_name.get();
    }


}
