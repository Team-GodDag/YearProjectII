package data;


import entities.Customer;
import entities.Offer;
import java.util.ArrayList;

public interface OfferDataAccess {
    //Laver af Rikke
    ArrayList<Offer> getAllOffers();

    boolean addOffer(Offer offer);

    boolean deleteOffer(Offer offer);

//    boolean updateOffer(Offer offer);

//    ArrayList<Offer> getOffersByCondition(String condition);

    ArrayList<Offer> getOffersByCustomer(Customer customer);
}
