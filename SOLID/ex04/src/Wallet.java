public class Wallet extends Payment {
    Wallet(double a){
        super(a);
    }

    @Override
    public String pay() {
        return "Paid using Wallet: " + amount;
    }

}
