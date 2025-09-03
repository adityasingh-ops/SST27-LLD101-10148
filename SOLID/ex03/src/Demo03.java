public class Demo03 {
    static void printCost(ICostManger s) {
        System.out.println(s.cost());
    }
    public static void main(String[] args) {
        printCost(new StandardCostCalculator(2.0));
        printCost(new ExpressCostCalculator(2.0));
        printCost(new OvernightCostCalculator(2.0));
    }
}
