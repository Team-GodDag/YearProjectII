package entities;       //måske POJOs?

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.PaymentCalc;

public class Offer { // Henrik

    public int id, customerId, salesPersonId, carId;
    public double rkiInterestRate, payment;
    public StringProperty car_price, dateOfSale, dateOfPayStart, dateOfPayEnd, approvedBy;

    public Offer(int id, int customer_id, double rkiInterestRate, int seller_id, int car_model_id, String car_price, double payment, String dateOfSale, String dateOfPayStart, String dateOfPayEnd) {
        this.id = id;
        this.customerId = customer_id;
        this.rkiInterestRate = rkiInterestRate;
        this.salesPersonId = seller_id;
        this.carId = car_model_id;
        this.car_price = new SimpleStringProperty(car_price);
        this.payment = payment;
        this.dateOfSale = new SimpleStringProperty(dateOfSale);
        this.dateOfPayStart = new SimpleStringProperty(dateOfPayStart);
        this.dateOfPayEnd = new SimpleStringProperty(dateOfPayEnd);

    }

    public Offer(Customer customer, Car car, SalesPerson salesPerson, PaymentCalc paymentCalc, String dateOfSale, String approvedBy) {
        this.customerId = customer.getId();
        this.rkiInterestRate = paymentCalc.getInterestRate();
        this.salesPersonId = salesPerson.getId();
        this.carId = car.getId();
        this.car_price = car.priceProperty();
        this.payment = paymentCalc.getDownPayment();
        this.dateOfSale = new SimpleStringProperty(dateOfSale);
        this.approvedBy = new SimpleStringProperty(approvedBy);
    }

    public Offer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(int salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getRkiInterestRate() {
        return rkiInterestRate;
    }

    public void setRkiInterestRate(double rkiInterestRate) {
        this.rkiInterestRate = rkiInterestRate;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getCar_price() {
        return car_price.get();
    }

    public StringProperty car_priceProperty() {
        return car_price;
    }

    public void setCar_price(String car_price) {
        this.car_price.set(car_price);
    }

    public String getDateOfSale() {
        return dateOfSale.get();
    }

    public StringProperty dateOfSaleProperty() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale.set(dateOfSale);
    }

    public String getDateOfPayStart() {
        return dateOfPayStart.get();
    }

    public StringProperty dateOfPayStartProperty() {
        return dateOfPayStart;
    }

    public void setDateOfPayStart(String dateOfPayStart) {
        this.dateOfPayStart.set(dateOfPayStart);
    }

    public String getDateOfPayEnd() {
        return dateOfPayEnd.get();
    }

    public StringProperty dateOfPayEndProperty() {
        return dateOfPayEnd;
    }

    public void setDateOfPayEnd(String dateOfPayEnd) {
        this.dateOfPayEnd.set(dateOfPayEnd);
    }

    public String getApprovedBy() {
        return approvedBy.get();
    }

    public StringProperty approvedByProperty() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy.set(approvedBy);
    }
}
