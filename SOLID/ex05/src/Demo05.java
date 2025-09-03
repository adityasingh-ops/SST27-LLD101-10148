
public class Demo05 {
    static int areaAfterResize(Rectangle r){
        r.resize(5, 4);
        return r.area();
    }
    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2,3);
        System.out.println(areaAfterResize(rc));

        Shape sq = new Square(5);
        System.out.println(areaAfterResize((Rectangle)sq));
    }
}
