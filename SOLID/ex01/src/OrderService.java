public class OrderService {
    private double taxRate = 0.18;  //TODO: we can make it static final too
    private EmailClient email ;  // as its tightly coupled previously now we can inject it as we learn in solid 2 class

    public OrderService(EmailClient email) {
        this.email = email;
    }

    public double totalWithTax(double subtotal) {
        return subtotal + subtotal * taxRate;
    }
    public void checkout(String customerEmail, double subtotal) {
        double total = totalWithTax(subtotal);
        email.send(customerEmail, "Thanks! Your total is " + total);
        System.out.println("Order stored (pretend DB).");
    }
}