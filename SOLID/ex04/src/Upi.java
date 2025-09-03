public class Upi extends Payment {
    Upi(double a) {
        super(a);
    }

    @Override
    public String pay() {
        return "Paid using UPI: " + amount;
    }
}
