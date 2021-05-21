package entities;       //måske POJOs?

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logic.PaymentCalc;

import java.time.LocalDate;
import java.util.Date;

public class Offer { // Henrik

    public int id, customerId, salesPersonId, carId, approvedBy;
    public double payment, carPrice, downpaymentIntRate, todaysBankRate, paymentPeriodIntRate;
    public String creditRating;
    public Date dateOfSale, dateOfPayStart, dateOfPayEnd;

    public Offer(int id, int customerId, String creditRating, int salesPersonId, int carId, double carPrice, double payment, Date dateOfSale, Date dateOfPayStart, Date dateOfPayEnd, int approvedBy, double todaysBankRate, double downpaymentIntRate, double paymentPeriodIntRate) {
        this.id = id;
        this.customerId = customerId;
        this.creditRating = creditRating;
        this.salesPersonId = salesPersonId;
        this.carId = carId;
        this.carPrice = carPrice;
        this.payment = payment;
        this.dateOfSale = dateOfSale;
        this.dateOfPayStart = dateOfPayStart;
        this.dateOfPayEnd = dateOfPayEnd;
        this.approvedBy = approvedBy;
        this.todaysBankRate = todaysBankRate;
        this.downpaymentIntRate = downpaymentIntRate;
        this.paymentPeriodIntRate = paymentPeriodIntRate;

    }

    public Offer(Customer customer, Car car, SalesPerson salesPerson, String creditRating, PaymentCalc paymentCalc, Date dateOfSale, Date startPayDate, Date endPayDate, SalesPerson approvedBy) {
        this.id = 0;
        this.customerId = customer.getId();
        this.salesPersonId = salesPerson.getId();
        this.creditRating = creditRating;
        this.carId = car.getId();
        this.carPrice = car.getPrice();
        this.payment = paymentCalc.getDownPayment();
        this.todaysBankRate = paymentCalc.getBankInterestRate();
        this.dateOfSale = dateOfSale;
        this.dateOfPayStart = startPayDate;
        this.dateOfPayEnd = endPayDate;
        this.approvedBy = approvedBy.getId();
        this.paymentPeriodIntRate = paymentCalc.getPaymentPeriodInterestRate();
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

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
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
