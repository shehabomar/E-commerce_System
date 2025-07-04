import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product, int quantity) throws Exception {
        if (!product.isAvailable(quantity)) {
            if (product.isExpired()) {
                throw new Exception("Product " + product.getName() + " is expired");
            } else {
                throw new Exception(
                        "Product " + product.getName() + " is out of stock. Available: " + product.getQuantity());
            }
        }

        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                int newQty = item.getQuantity() + quantity;
                if (!product.isAvailable(newQty)) {
                    throw new Exception(
                            "Not enough stock for " + product.getName() + ". Available: " + product.getQuantity());
                }
                item.setQuantity(newQty);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() {
        items.clear();
    }
}