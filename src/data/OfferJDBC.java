package data;

import entities.Car;
import entities.Customer;
import entities.Offer;
import logic.AllCarModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OfferJDBC implements OfferDataAccess { // Henrik

    @Override
    public ArrayList<Offer> getAllOffers() {
        return getOffersByCondition("0 = 0");
    }

    @Override
    public boolean addOffer(Offer offer) {
        try {
            String sql = "INSERT INTO quotes VALUES ('" +
                    offer.getCustomer_id()     + "', '" +
                    offer.getCredit_rating()          + "', '" +
                    offer.getSeller_id()          + "', '" +
                    offer.getCar_model_id()          + "', '" +
                    offer.getCar_price()          + "', '" +
                    offer.getPayment()          + "', '" +
                    offer.getDateOfSale()          + "', '" +
                    offer.getDateOfPayStart()          + "', '" +
                    offer.getDateOfPayEnd()     +")";

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

    @Override
    public boolean updateOffer(Offer offer) {
        try {
            StringBuffer assignments = new StringBuffer();
            assignments.append("customer_id='"          + offer.getCustomer_id() + "', ");
            assignments.append("credit_rating='"        + offer.getCredit_rating() + "', ");
            assignments.append("seller_id='"            + offer.getSeller_id() + "', ");
            assignments.append("car_model_id='"         + offer.getCar_model_id() + "', ");
            assignments.append("car_price='"            + offer.getCar_price() + "', ");
            assignments.append("payment='"              + offer.getPayment() + "', ");
            assignments.append("dateofsale='"           + offer.getDateOfSale() + "', ");
            assignments.append("dateofpaystart='"       + offer.getDateOfPayStart() + "', ");
            assignments.append("dateofpayend='"         + offer.getDateOfPayEnd());

            String condition = "id=" + offer.getId();

            String sql = "UPDATE quotes SET " + assignments +
                    " WHERE " + condition;

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
                String customer_id      = resultSet.getString("customer_id");
                String credit_rating    = resultSet.getString("credit_rating");
                String seller_id        = resultSet.getString("seller_id");
                String car_model_id     = resultSet.getString("car_model_id");
                String car_price        = resultSet.getString("car_price");
                String payment          = resultSet.getString("payment");
                String dateofsale       = resultSet.getString("dateofsale");
                String dateofpaystart   = resultSet.getString("dateofpaystart");
                String dateofpayend     = resultSet.getString("dateofpayend");

                Offer offer = new Offer(id,customer_id, credit_rating, seller_id, car_model_id,car_price,payment,dateofsale, dateofpaystart,dateofpayend);
                offers.add(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

}
