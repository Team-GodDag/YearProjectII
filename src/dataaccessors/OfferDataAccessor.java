package dataaccessors;

import data.OfferDataAccess;
import data.OfferJDBC;
import entities.Offer;

import java.util.ArrayList;

public class OfferDataAccessor {

    public static ArrayList<Offer> createOfferList() {
        return new OfferJDBC().getAllOffers();
    }

    public static void addOffer(Offer offer) {
        new OfferJDBC().addOffer(offer);
    }

    public static OfferDataAccess getOfferDataAccess() {
        return new OfferJDBC();
    }
}
