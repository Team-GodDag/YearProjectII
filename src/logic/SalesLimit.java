package logic;

public class SalesLimit {
    String status;

    public String approvel(double carPrice, double downPaymeny, String salesPerson){
        double price = carPrice - downPaymeny;
        if(price >= 1000000){
            return this.status = "Need Sales Manager";
        }
       return this.status = " " + salesPerson;
    }
}
