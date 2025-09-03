

public class Demo01 {
    public static void main(String[] args) {
        EmailClient emailClient = new EmailClient();
        OrderService orderService = new OrderService(emailClient); // just-in-time dependency injection
        orderService.checkout("a@shop.com", 100.0);
    }
}
