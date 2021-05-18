package entities;       //måske POJOs?

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Offer { // Henrik

    public int id;
    public StringProperty customer_id, credit_rating, seller_id, car_model_id, car_price, payment, dateOfSale, dateOfPayStart, dateOfPayEnd;

    public Offer(int id, String customer_id, String credit_rating, String seller_id, String car_model_id,String car_price,String payment, String dateOfSale, String dateOfPayStart, String dateOfPayEnd) {
        this.id = id;
        this.customer_id = new SimpleStringProperty(customer_id);
        this.credit_rating = new SimpleStringProperty(credit_rating);
        this.seller_id = new SimpleStringProperty(seller_id);
        this.car_model_id = new SimpleStringProperty(car_model_id);
        this.car_price = new SimpleStringProperty(car_price);
        this.payment = new SimpleStringProperty(payment);
        this.dateOfSale = new SimpleStringProperty(dateOfSale);
        this.dateOfPayStart = new SimpleStringProperty(dateOfPayStart);
        this.dateOfPayEnd = new SimpleStringProperty(dateOfPayEnd);

    }

    public Offer(){
    }

    public String getCustomer_id() {
        return customer_id.get();
    }

    public StringProperty customer_idProperty() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id.set(customer_id);
    }

    public String getCredit_rating() {
        return credit_rating.get();
    }

    public StringProperty credit_ratingProperty() {
        return credit_rating;
    }

    public void setCredit_rating(String credit_rating) {
        this.credit_rating.set(credit_rating);
    }

    public String getSeller_id() {
        return seller_id.get();
    }

    public StringProperty seller_idProperty() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id.set(seller_id);
    }

    public String getCar_model_id() {
        return car_model_id.get();
    }

    public StringProperty car_model_idProperty() {
        return car_model_id;
    }

    public void setCar_model_id(String car_model_id) {
        this.car_model_id.set(car_model_id);
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

    public String getPayment() {
        return payment.get();
    }

    public StringProperty paymentProperty() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment.set(payment);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
