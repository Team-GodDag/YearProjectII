package logic;

import entities.Car;
import entities.Offer;

import java.util.ArrayList;

public class AllOffers {
    public static ArrayList<Offer> allOffers = new ArrayList<>();

    public void addOffer(Offer offer){
        allOffers.add(offer);
    }
}
