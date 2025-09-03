public class CacheManager {
    private Frame last;
    public void cacheFrame(Frame frame) {
        last = frame;
        System.out.println("Cached last frame? " + (last!=null));   
    }
}
