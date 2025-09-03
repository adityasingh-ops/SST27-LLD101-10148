public class ExpressCostCalculator implements ICostManger {
    double weightKg;
    ExpressCostCalculator(double w){
        this.weightKg=w; 
    }
    @Override
    public double cost() {
        return 150 + 15* weightKg;
    }
}
