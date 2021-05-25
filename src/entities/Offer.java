package entities;       //måske POJOs?

import logic.PaymentCalc;

import java.util.Date;

public class Offer { // Henrik

    public int id, customerId, salesPersonId, carId, approvedBy;
    public double downpayment, carPrice, downpaymentIntRate, todaysBankRate, paymentPeriodIntRate;
    public String creditRating;
    public Date dateOfSale, dateOfPayStart, dateOfPayEnd;

    public Offer(int id, int customerId, String creditRating, int salesPersonId, int carId, double carPrice, double downpayment, int approvedBy, double todaysBankRate, double downpaymentIntRate, double paymentPeriodIntRate, Date dateOfSale, Date dateOfPayStart, Date dateOfPayEnd) {
        this.id = id;
        this.customerId = customerId;
        this.creditRating = creditRating;
        this.salesPersonId = salesPersonId;
        this.carId = carId;
        this.carPrice = carPrice;
        this.downpayment = downpayment;
        this.dateOfSale = dateOfSale;
        this.dateOfPayStart = dateOfPayStart;
        this.dateOfPayEnd = dateOfPayEnd;
        this.approvedBy = approvedBy;
        this.todaysBankRate = todaysBankRate;
//        this.downpaymentIntRate = downpaymentIntRate;             //SKAL BEREGNES VED LOADING og ikke gemmes direkte i DB
//        this.paymentPeriodIntRate = paymentPeriodIntRate;

    }

    public Offer(Customer customer, Car car, SalesPerson salesPerson, String creditRating, PaymentCalc paymentCalc, Date dateOfSale, Date startPayDate, Date endPayDate, SalesPerson approvedBy) {
        this.id = 0;
        this.customerId = customer.getId();
        this.salesPersonId = salesPerson.getId();
        this.creditRating = creditRating;
        this.carId = car.getId();
        this.carPrice = car.getPrice();
        this.downpayment = paymentCalc.getDownPayment();
        this.approvedBy = approvedBy.getId();
        this.todaysBankRate = paymentCalc.getBankInterestRate();
        this.downpaymentIntRate = paymentCalc.getDownPaymentInterestRate();
        this.paymentPeriodIntRate = paymentCalc.getPaymentPeriodInterestRate();
        this.dateOfSale = dateOfSale;
        this.dateOfPayStart = startPayDate;
        this.dateOfPayEnd = endPayDate;
    }

    public double getDownpaymentIntRate() {
        return downpaymentIntRate;
    }

    public void setDownpaymentIntRate(double downpaymentIntRate) {
        this.downpaymentIntRate = downpaymentIntRate;
    }

    public double getTodaysBankRate() {
        return todaysBankRate;
    }

    public void setTodaysBankRate(double todaysBankRate) {
        this.todaysBankRate = todaysBankRate;
    }

    public double getPaymentPeriodIntRate() {
        return paymentPeriodIntRate;
    }

    public void setPaymentPeriodIntRate(double paymentPeriodIntRate) {
        this.paymentPeriodIntRate = paymentPeriodIntRate;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public Offer() {
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

    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }
}
