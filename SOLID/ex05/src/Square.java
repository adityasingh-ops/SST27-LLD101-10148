class Square implements Shape {
    private int side;
    
    public Square(int side) {
        this.side = side;
    }
    
    @Override
    public int area() {
        return side * side;
    }
    
    @Override
    public void resize(int width, int height) {
        if (width != height) {
            throw new IllegalArgumentException("Square must have equal dimensions");
        }
        this.side = width;
    }
}