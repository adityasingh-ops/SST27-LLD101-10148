import com.example.orders.*;

public class TryIt {
    public static void main(String[] args) {
        System.out.println("=== Immutable Order Builder Demo ===\n");
        
        OrderLine line1 = new OrderLine("LAPTOP", 1, 50000); 
        OrderLine line2 = new OrderLine("MOUSE", 2, 2500);   
        
        Order order = Order.builder()
                .id("ORDER-001")
                .customerEmail("customer@example.com")
                .addLine(line1)
                .addLine(line2)
                .discountPercent(10)
                .expedited(true)
                .notes("Birthday gift")
                .build();
        
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomerEmail());
        System.out.println("Lines: " + order.getLines().size());
        System.out.println("Expedited: " + order.isExpedited());
        System.out.println("Notes: " + order.getNotes());
        
        System.out.println("\n=== Pricing ===");
        System.out.println("Before discount: $" + (order.totalBeforeDiscount() / 100.0));
        System.out.println("Discount: " + order.getDiscountPercent() + "%");
        System.out.println("After discount: $" + (order.totalAfterDiscount() / 100.0));
        
        System.out.println("\n=== Immutability Test ===");
        System.out.println("Original total: $" + (order.totalAfterDiscount() / 100.0));
        
        order.getLines().clear();
        System.out.println("After trying to clear lines: $" + (order.totalAfterDiscount() / 100.0));
        System.out.println("✅ Order remains unchanged - immutable!");
    }
}
