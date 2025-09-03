
public class Demo04 {
    static void pay(Payment p) {
        System.out.println(p.pay());
    }
    public static void main(String[] args) { // violation of Open-Closed Principle like a class should be open for extension but closed for modification and also liskov substitution principle of like super class should be replaceable with subclass
        pay(new Card(100));
        pay(new Upi(200));
        pay(new Wallet(300));
    }
}
