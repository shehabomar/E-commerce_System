import java.time.LocalDate;

public class ExpirableProduct extends Product implements Shippable {
    private LocalDate expiry;
    private double weight;
    private boolean shipping;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiry, double weight,
            boolean shipping) {
        super(name, price, quantity);
        this.expiry = expiry;
        this.weight = weight;
        this.shipping = shipping;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiry);
    }

    @Override
    public boolean requiresShipping() {
        return shipping;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpiry() {
        return expiry;
    }
}