package logic;

import entities.Car;

public class SalesLimit {
    String status;

    public String approval(double carPrice, double downPayment, String salesPerson) {
        double price = carPrice - downPayment;
        if(price >= 1000000){
            return this.status = "Need Sales Manager";
        }
       return this.status = " " + salesPerson;
    }

    public boolean needsApproval(Car car, double downpayment) { //Rikkes addition
        if(car.getPrice() - downpayment >= 1000000) {
            return true;
        } else
            return false;
    }
}
