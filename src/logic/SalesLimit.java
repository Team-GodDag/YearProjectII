package logic;

public class SalesLimit {
    String status;

    public String approval(double carPrice, double downPayment, String salesPerson){
        double price = carPrice - downPayment;
        if (carPrice<downPayment){
            return "-1";
        }
        if(price >= 1000000){
            return this.status = "Need Sales Manager";
        }
       return this.status = " " + salesPerson;
    }
}
