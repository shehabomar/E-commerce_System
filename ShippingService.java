import java.util.List;

public class ShippingService {
    private static final double RATE_PER_KG = 30.0;

    public static void processShipment(List<Shippable> items) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double weight = 0.0;

        for (Shippable item : items) {
            System.out.println(item.getName() + "\t\t" + (int) (item.getWeight()) + "g");
            weight += item.getWeight();
        }

        System.out.println("Total package weight " + (weight / 1000.0) + "kg");
        System.out.println();
    }

    public static double calculateShippingFee(List<Shippable> items) {
        if (items.isEmpty()) {
            return 0.0;
        }

        double weight = 0.0;
        for (Shippable item : items) {
            weight += item.getWeight();
        }

        double kg = weight / 1000.0;
        return kg * RATE_PER_KG;
    }
}