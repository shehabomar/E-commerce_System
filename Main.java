import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            ExpirableProduct cheese = new ExpirableProduct("Cheese", 100.0, 10, LocalDate.now().plusDays(30), 200.0,
                    true);
            NonExpirableProduct tv = new NonExpirableProduct("TV", 500.0, 5, 2000.0, true);
            NonExpirableProduct cards = new NonExpirableProduct("Mobile scratch cards", 50.0, 20, 0.0, false);
            ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150.0, 8, LocalDate.now().plusDays(15), 700.0,
                    true);

            Customer customer = new Customer("John Doe", 1000.0);
            Cart cart = new Cart();

            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(cards, 1);

            System.out.println("=== Example Checkout ===");
            ECommerceSystem.checkout(customer, cart);

            System.out.println("\n=== Testing Error Cases ===");

            try {
                Cart cart1 = new Cart();
                ECommerceSystem.checkout(customer, cart1);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            try {
                Cart cart2 = new Cart();
                cart2.add(tv, 2);
                Customer customer2 = new Customer("Poor Customer", 100.0);
                ECommerceSystem.checkout(customer2, cart2);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // only 8 cheese left after first purchase
            try {
                Cart cart3 = new Cart();
                cart3.add(cheese, 15);
                ECommerceSystem.checkout(customer, cart3);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            try {
                ExpirableProduct expired = new ExpirableProduct("Expired Milk", 50.0, 5,
                        LocalDate.now().minusDays(1), 1000.0, true);
                Cart cart4 = new Cart();
                cart4.add(expired, 1);
                ECommerceSystem.checkout(customer, cart4);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}