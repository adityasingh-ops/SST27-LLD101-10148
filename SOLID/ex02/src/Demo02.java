public class Demo02 {
    
    public static void main(String[] args) { // i think in this we are voilating single responsibility principle
        Decoder decoder = new Decoder();
        Rendrer renderer = new Rendrer();
        CacheManager cacheManager = new CacheManager();
        Player player = new Player(decoder, renderer, cacheManager);
        player.play(new byte[]{1,2,3,4});
    }
}
