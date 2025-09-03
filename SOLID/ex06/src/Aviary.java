class Aviary {
    public void release(Flyable flyablebird) {
        flyablebird.fly();
        System.out.println("Released");
    }
    public void release(Bird bird) {
        bird.flap();
        System.out.println("Released");
    }
}