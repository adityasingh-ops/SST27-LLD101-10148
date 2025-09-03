public class SqlOrderRepository implements OrderRepository {
    public void save(String order) {
        System.out.println("Saved order " + order + " to SQL");
    }
}
