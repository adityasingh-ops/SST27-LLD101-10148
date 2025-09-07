package com.example.video;

public class ProcessingOptions {
    private boolean grayscaleEnabled = true;
    private double scaleFactor = 0.5;
    private int sharpenStrength = 0;
    
    public ProcessingOptions() {}
    
    public ProcessingOptions(boolean grayscaleEnabled, double scaleFactor, int sharpenStrength) {
        this.grayscaleEnabled = grayscaleEnabled;
        this.scaleFactor = scaleFactor;
        this.sharpenStrength = sharpenStrength;
    }
    
    public boolean isGrayscaleEnabled() { return grayscaleEnabled; }
    public double getScaleFactor() { return scaleFactor; }
    public int getSharpenStrength() { return sharpenStrength; }
    
    public ProcessingOptions withGrayscale(boolean enabled) {
        return new ProcessingOptions(enabled, scaleFactor, sharpenStrength);
    }
    
    public ProcessingOptions withScale(double factor) {
        return new ProcessingOptions(grayscaleEnabled, factor, sharpenStrength);
    }
    
    public ProcessingOptions withSharpen(int strength) {
        return new ProcessingOptions(grayscaleEnabled, scaleFactor, strength);
    }
}
