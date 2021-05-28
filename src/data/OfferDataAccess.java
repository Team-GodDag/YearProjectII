package data;


import entities.Offer;
import java.util.ArrayList;

public interface OfferDataAccess {
//    ArrayList<Offer> getAllOffers();

    boolean addOffer(Offer offer);

    boolean deleteOffer(Offer offer);

//    boolean updateOffer(Offer offer);

    ArrayList<Offer> getOffersByCondition(String condition);
}
