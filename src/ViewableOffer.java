import entities.Offer;

public class ViewableOffer {

    private Offer offer;
    private String viewableCustomerFirstName;

    public ViewableOffer(Offer offer) {
        this.viewableCustomerFirstName = String.valueOf(offer.getCustomerId());

    }
}
