package data;

import entities.Offer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class OfferJDBC implements OfferDataAccess { // Henrik

    public ArrayList<Offer> getAllOffers() {
        return getOffersByCondition("0 = 0");
    }

    @Override
    public boolean addOffer(Offer offer) {
        try {
            String sql = "INSERT INTO quotes VALUES ('" +
                    offer.getCustomerId()           + "', '" +
                    offer.getCreditRating()         + "', '" +
                    offer.getSalesPersonId()        + "', '" +
                    offer.getCarId()                + "', '" +
                    offer.getCarPrice()             + "', '" +
                    offer.getDownpayment()          + "', '" +
                    offer.getStatus()               + "', '" +
                    offer.getTodaysBankRate()       + "', '" +
                    offer.getDateOfSale()           + "', '" +
                    offer.getDateOfPayStart()       + "', " +
                    offer.getDateOfPayEnd()         + ")";

            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()){
                int autoKey = resultSet.getInt(1);
                offer.setId(autoKey);
            }
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOffer(Offer offer) {
        try {
            String condition = "id=" + offer.getId();
            String sql = "DELETE FROM quotes WHERE " + condition;
            System.out.println(sql);
            Statement statement = JDBC.instance.connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);

            return (affectedRows == 1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    @Override
//    public boolean updateOffer(Offer offer) {
//        try {
//            StringBuffer assignments = new StringBuffer();
//            assignments.append("customer_id='"          + offer.customerId + "', ");
//            assignments.append("credit_rating='"        + offer.rkiInterestRate + "', ");
//            assignments.append("seller_id='"            + offer.salesPersonId + "', ");
//            assignments.append("car_model_id='"         + offer.carId + "', ");
//            assignments.append("car_price='"            + offer.car_price + "', ");
//            assignments.append("payment='"              + offer.payment + "', ");
//            assignments.append("dateofsale='"           + offer.getDateOfSale() + "', ");
//            assignments.append("dateofpaystart='"       + offer.getDateOfPayStart() + "', ");
//            assignments.append("dateofpayend='"         + offer.getDateOfPayEnd());
//
//            String condition = "id=" + offer.getId();
//
//            String sql = "UPDATE quotes SET " + assignments +
//                    " WHERE " + condition;
//
//            System.out.println(sql);
//            Statement statement = JDBC.instance.connection.createStatement();
//            int affectedRows = statement.executeUpdate(sql);
//            return (affectedRows == 1);
//
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    @Override
    public ArrayList<Offer> getOffersByCondition(String condition) {
        ArrayList<Offer> offers = new ArrayList<Offer>();
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM quotes WHERE " + condition;
            Statement statement = JDBC.instance.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {// iteration starter 'before first'
                int id                  = resultSet.getInt("quote_id");
                int customerId         = resultSet.getInt("customer_id");
                
                int sellerId           = resultSet.getInt("seller_id");
                int carId        = resultSet.getInt("car_model_id");
                String creditRating    = resultSet.getString("credit_rating");
                String status           = resultSet.getString("status");
                double carPrice        = resultSet.getDouble("car_price");
                double payment          = resultSet.getDouble("payment");
                double bankratingoftheday = resultSet.getDouble("bankratingoftheday");
                Date dateofsale         = resultSet.getDate("dateofsale");
                Date dateofpaystart     = resultSet.getDate("dateofpaystart");
                Date dateofpayend       = resultSet.getDate("dateofpayend");


                Offer offer = new Offer(id, customerId, sellerId, carId, creditRating, status, carPrice, payment, bankratingoftheday, dateofsale, dateofpaystart, dateofpayend); //downpaymentinterest, periodinterest,
                offers.add(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

}
