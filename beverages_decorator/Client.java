package beverages_decorator;

public class Client {

public static void main(String[] args) {
        Beverage coffee = new Cappuccino();
        System.out.println("Coffee");
        System.out.println(coffee.cost());

        Beverage latte = new Latte();
        System.out.println("Latte");
        System.out.println(latte.cost());

        System.out.println("\n=== Chocolate Decorated Beverages ===");
        
        // Cappuccino with chocolate
        Beverage chocolateCappuccino = new ChocolateDecorator(new Cappuccino());
        System.out.println("Chocolate Cappuccino: " + chocolateCappuccino.cost());
        
        // Latte with chocolate
        Beverage chocolateLatte = new ChocolateDecorator(new Latte());
        System.out.println("Chocolate Latte: " + chocolateLatte.cost());
        
        // Double chocolate cappuccino
        Beverage doubleChocolateCappuccino = new ChocolateDecorator(
            new ChocolateDecorator(new Cappuccino())
        );
        System.out.println("Double Chocolate Cappuccino: " + doubleChocolateCappuccino.cost());
    }

}