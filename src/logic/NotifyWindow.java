package logic;


import javax.swing.*;   //javafx alertbox flyttes til view


public class NotifyWindow {
    public void notifySalesManager(String salesPerson){
        JOptionPane.showMessageDialog(null,     //SWING???!
                "Sales Manager Has Been Notified" + "\n" +
                "By:" + salesPerson);
    }
    public void acceptOffer(String salesPerson){
        JOptionPane.showMessageDialog(null,
                "Offer Has Been Accepted And Archived" + "\n" +
                        "By:  " + salesPerson);
    }
}