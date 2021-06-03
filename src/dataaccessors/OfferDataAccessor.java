package dataaccessors;

import data.OfferDataAccess;
import data.OfferJDBC;
import entities.Offer;

import java.util.ArrayList;

public class OfferDataAccessor {

    public static OfferDataAccess getOfferDataAccess() {
        return new OfferJDBC();
    }
}
