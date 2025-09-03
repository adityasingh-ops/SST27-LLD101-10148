class Rectangle implements Shape {
    private int w, h;
    
    public Rectangle(int width, int height) {
        this.w = width;
        this.h = height;
    }
    
    @Override
    public int area() {
        return w * h;
    }
    
    @Override
    public void resize(int width, int height) {
        this.w = width;
        this.h = height;
    }
}
