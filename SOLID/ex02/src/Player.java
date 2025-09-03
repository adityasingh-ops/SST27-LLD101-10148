public class Player {
    private Decoder decoder;
    private Rendrer renderer;
    private CacheManager cacheManager;

    public Player(Decoder decoder, Rendrer renderer, CacheManager cacheManager) {
        this.decoder = decoder;
        this.renderer = renderer;
        this.cacheManager = cacheManager;
    }

    public void play(byte[] fileBytes){
        Frame frame = decoder.decode(fileBytes);
        renderer.drawUI(frame);
        cacheManager.cacheFrame(frame);
    }
}