package entities;

import logic.PaymentCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Offer {
    // Lavet af Rikke

    private int id, customerId, salesPersonId, carId;
    private BigDecimal downpayment, carPrice, todaysBankRate;
    private String creditRating, status;
    private Date dateOfSale, dateOfPayStart, dateOfPayEnd;
//    private Customer customer;              //skal disse tre med?
//    private SalesPerson salesPerson;        //skal disse tre med?
//    private Car car;                        //skal disse tre med?

//Calculation constructor
//    public Offer() {
//        this.id = 0;
//        this.dateOfSale = java.sql.Date.valueOf(LocalDate.now());   //viser kun dato, så nok OK
//        this.
//    }

//Constructor til DB-entry - ville være fordel m. Builder
    public Offer(int id, int customerId, int salesPersonId, int carId, String creditRating, String status,  BigDecimal carPrice, BigDecimal downpayment, BigDecimal todaysBankRate, Date dateOfSale, Date dateOfPayStart, Date dateOfPayEnd) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTodaysBankRate() {
        return todaysBankRate;
    }

    public void setTodaysBankRate(BigDecimal todaysBankRate) {
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

    public BigDecimal getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(BigDecimal downpayment) {
        this.downpayment = downpayment;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
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
