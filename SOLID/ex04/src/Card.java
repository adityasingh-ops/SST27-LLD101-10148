public class Card extends Payment {
    Card(double a) {
        super(a);
    }

    @Override
    public String pay() {
        return "Charged card: " + amount;
    }
}
