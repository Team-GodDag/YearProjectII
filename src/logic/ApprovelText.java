package logic;

public class ApprovelText {
    String status;

    public String approvel(double carPrice, String salesPerson){
        if(carPrice >= 1000000){
            return this.status = "Need Sales Manager";
        }
       return this.status = "" + salesPerson;
    }
}
