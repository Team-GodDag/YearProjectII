package factories;

import data.CarJDBC;
import data.JDBC;
import data.OfferJDBC;
import entities.Car;
import entities.Offer;

import java.util.ArrayList;

public class OfferListFactory {

    public static ArrayList<Offer> createOfferList() {
        return new OfferJDBC().getAllOffers();
    }

    public static void addOffer(Offer offer) {
        new OfferJDBC().addOffer(offer);
    }
}
