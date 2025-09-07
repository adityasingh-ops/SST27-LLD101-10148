package com.example.render;

import java.util.HashMap;
import java.util.Map;

public class TextStyleFactory {
    private static final Map<String, TextStyle> styles = new HashMap<>();
    
    public static TextStyle getStyle(String font, int size, boolean bold) {
        String key = font + "_" + size + "_" + bold;
        
        return styles.computeIfAbsent(key, k -> {
            System.out.println("Creating new TextStyle: " + key); // Debug
            return new TextStyle(font, size, bold);
        });
    }
    
    public static int getCreatedStylesCount() {
        return styles.size();
    }
    
    public static void clearCache() {
        styles.clear();
    }
}
