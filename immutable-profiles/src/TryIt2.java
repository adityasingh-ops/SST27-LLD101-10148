import com.example.profiles.*;

public class TryIt2 {
    public static void main(String[] args) {
        ProfileService svc = new ProfileService();
        UserProfile p = svc.createMinimal("u1", "a@b.com");
        System.out.println("Before: " + p.getEmail());
        p = svc.updateDisplayName(p, "User One");
        System.out.println("After:  " + p.getEmail());
        System.out.println("=> In the solution, this setter disappears and object becomes immutable.");
    }
}
