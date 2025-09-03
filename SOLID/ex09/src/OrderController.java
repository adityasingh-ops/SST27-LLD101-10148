public class OrderController {
    void create(String id, OrderRepository repo){// hard dependency
        repo.save(id);
        System.out.println("Created order: " + id);
    }
}