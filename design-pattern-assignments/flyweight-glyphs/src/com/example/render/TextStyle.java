package com.example.render;

public class TextStyle {
    private final String font;
    private final int size;
    private final boolean bold;
    
    public TextStyle(String font, int size, boolean bold) {
        this.font = font;
        this.size = size;
        this.bold = bold;
    }
    
    public int getDrawCost() {
        return size + (bold ? 10 : 0);
    }
    
    public String getFont() { return font; }
    public int getSize() { return size; }
    public boolean isBold() { return bold; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TextStyle style = (TextStyle) obj;
        return size == style.size && 
               bold == style.bold && 
               font.equals(style.font);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(font, size, bold);
    }
}
