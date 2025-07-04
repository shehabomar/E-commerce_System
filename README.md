# E-Commerce System

A Java-based e-commerce system that demonstrates product management, cart functionality, and checkout processes with shipping integration.

## How to Run

### Clone from github
```bash 
git clone E-commerce_System
cd E-commerce_System
```

### Compile the code:
```bash
javac *.java
```

### Run the main example:
```bash
java Main
```

## Expected Output

The program will output:
```
=== Example Checkout ===
** Shipment notice **
Cheese		200g
Cheese		200g
Biscuits		700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese		200
1x Biscuits		150
1x Mobile scratch cards		50
--------------------
Subtotal		400
Shipping		33
Amount			433

Customer balance after payment: 567

=== Testing Error Cases ===
Error: Cart is empty
Error: Customer's balance is insufficient. Required: 1000.0, Available: 100.0
Error: Product Cheese is out of stock. Available: 8
Error: Product Expired Milk is expired
```

## Classes

- `Product` - base product class
- `ExpirableProduct` - products with expiry
- `NonExpirableProduct` - non-expiring products  
- `Shippable` - shipping interface
- `Cart` - shopping cart
- `Customer` - customer with balance
- `ECommerceSystem` - checkout logic
- `ShippingService` - shipping handling 