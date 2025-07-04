public class NonExpirableProduct extends Product implements Shippable {
    private double weight;
    private boolean shipping;

    public NonExpirableProduct(String name, double price, int quantity, double weight, boolean shipping) {
        super(name, price, quantity);
        this.weight = weight;
        this.shipping = shipping;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean requiresShipping() {
        return shipping;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}