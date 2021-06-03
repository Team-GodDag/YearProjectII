package entities;       //måske POJOs?

import logic.PaymentCalculator;

import java.time.LocalDate;
import java.util.Date;

public class Offer { // Henrik

    private int id, customerId, salesPersonId, carId;
    private double downpayment, carPrice, todaysBankRate; //downpaymentIntRate, paymentPeriodIntRate
    private String creditRating, status;
    private Date dateOfSale, dateOfPayStart, dateOfPayEnd;


    public Offer(Customer customer, Car car, SalesPerson salesPerson, String creditRating, PaymentCalculator paymentCalculator, LocalDate dateOfSale, LocalDate startPayDate, LocalDate endPayDate, String status) { //boolean isApproved,
        this.id             = 0;
        this.customerId     = customer.getId();
        this.salesPersonId  = salesPerson.getId();
        this.creditRating   = creditRating;
        this.carId          = car.getId();
        this.carPrice       = car.getPrice();
        this.downpayment    = paymentCalculator.getDownPayment();
        this.todaysBankRate = paymentCalculator.getBaseBankInterestRate();
        this.status         = status;
        this.dateOfSale     = java.sql.Date.valueOf(dateOfSale);
        this.dateOfPayStart = java.sql.Date.valueOf(startPayDate);
        this.dateOfPayEnd   = java.sql.Date.valueOf(endPayDate);
    }

//Constructor til DB-entry
    public Offer(int id, int customerId, int salesPersonId, int carId, String creditRating, String status,  double carPrice, double downpayment, double todaysBankRate, Date dateOfSale, Date dateOfPayStart, Date dateOfPayEnd) {
        this.id             = id;
        this.customerId     = customerId;
        this.salesPersonId  = salesPersonId;
        this.carId          = carId;
        this.creditRating   = creditRating;
        this.status         = status;
        this.carPrice       = carPrice;
        this.downpayment    = downpayment;
        this.todaysBankRate = todaysBankRate;
        this.dateOfSale     = dateOfSale;
        this.dateOfPayStart = dateOfPayStart;
        this.dateOfPayEnd   = dateOfPayEnd;
    }

    public Offer() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTodaysBankRate() {
        return todaysBankRate;
    }

    public void setTodaysBankRate(double todaysBankRate) {
        this.todaysBankRate = todaysBankRate;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
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

    public double getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(double downpayment) {
        this.downpayment = downpayment;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public Date getDateOfPayStart() {
        return dateOfPayStart;
    }

    public void setDateOfPayStart(Date dateOfPayStart) {
        this.dateOfPayStart = dateOfPayStart;
    }

    public Date getDateOfPayEnd() {
        return dateOfPayEnd;
    }

    public void setDateOfPayEnd(Date dateOfPayEnd) {
        this.dateOfPayEnd = dateOfPayEnd;
    }

}
