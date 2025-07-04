import java.util.ArrayList;
import java.util.List;

public class ECommerceSystem {

    public static void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty");
        }

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (!product.isAvailable(item.getQuantity())) {
                if (product.isExpired()) {
                    throw new Exception("Product " + product.getName() + " is expired");
                } else {
                    throw new Exception("Product " + product.getName() + " is out of stock");
                }
            }
        }

        double sub = cart.getSubtotal();

        // collect items that need shipping
        List<Shippable> shipItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.requiresShipping()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shipItems.add((Shippable) product);
                }
            }
        }

        double shipFee = ShippingService.calculateShippingFee(shipItems);
        double total = sub + shipFee;

        if (!customer.hasEnoughBalance(total)) {
            throw new Exception(
                    "Customer's balance is insufficient. Required: " + total + ", Available: " + customer.getBalance());
        }

        if (!shipItems.isEmpty()) {
            ShippingService.processShipment(shipItems);
        }

        customer.deductBalance(total);

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }

        printCheckoutReceipt(cart, sub, shipFee, total, customer.getBalance());

        cart.clear();
    }

    private static void printCheckoutReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount,
            double remainingBalance) {
        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getItems()) {
            System.out.println(
                    item.getQuantity() + "x " + item.getProduct().getName() + "\t\t" + (int) item.getTotalPrice());
        }

        System.out.println("--------------------");
        System.out.println("Subtotal\t\t" + (int) subtotal);
        System.out.println("Shipping\t\t" + (int) shippingFee);
        System.out.println("Amount\t\t\t" + (int) totalAmount);
        System.out.println();
        System.out.println("Customer balance after payment: " + (int) remainingBalance);
    }
}