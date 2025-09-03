public class OvernightCostCalculator implements ICostManger {
    double weightKg;
    OvernightCostCalculator(double w){
        this.weightKg=w; 
    }
    @Override
    public double cost() {
        return 120 + 10* weightKg;
    }
    
}
