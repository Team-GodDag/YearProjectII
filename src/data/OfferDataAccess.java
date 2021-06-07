package data;


import entities.Customer;
import entities.Offer;
import java.util.ArrayList;

public interface OfferDataAccess {
    //Lavet af Rikke
    ArrayList<Offer> getAllOffers();

    boolean addOffer(Offer offer);

    boolean deleteOffer(Offer offer);

    ArrayList<Offer> getOffersByCustomer(Customer customer);
}
