package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class Car {
    // Lavet af Rikke

    public int id;
    public String name, horsepower;
    BigDecimal price;

    public Car(int id, String name, BigDecimal price, String horsepower) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.horsepower = horsepower;
    }

    public Car(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
