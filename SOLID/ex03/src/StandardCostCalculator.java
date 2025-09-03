public class StandardCostCalculator implements ICostManger {
    double weightKg;
    StandardCostCalculator(double w){
        this.weightKg=w; 
    }
    @Override
    public double cost() {
        return 100 + 5* weightKg;
    }
}
