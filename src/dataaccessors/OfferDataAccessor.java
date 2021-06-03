package dataaccessors;

import data.OfferDataAccess;
import data.OfferJDBC;


public class OfferDataAccessor {
    // Lavet af Rikke

    public static OfferDataAccess getOfferDataAccess() {
        return new OfferJDBC();
    }
}
