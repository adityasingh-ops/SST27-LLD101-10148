public abstract class Payment {
    double amount;
    Payment(double a){ amount=a; }
    public abstract String pay();

    public void setAmount(double a) {
        amount = a;
    }   
}