import beverages_decorator.Beverage;

public abstract class BeverageDecorator extends Beverage {
    protected Beverage beverage;
    
    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
    
    @Override
    public abstract int cost();
}
