package com.example.video;

import java.nio.file.Path;

public class VideoPipelineFacade {
    private final Decoder decoder;
    private final FilterEngine filterEngine;
    private final Encoder encoder;
    private final LegacySharpen legacySharpen;
    
    public VideoPipelineFacade() {
        this.decoder = new Decoder();
        this.filterEngine = new FilterEngine();
        this.encoder = new Encoder();
        this.legacySharpen = new LegacySharpen();
    }
    
    public VideoPipelineFacade(Decoder decoder, FilterEngine filterEngine, 
                              Encoder encoder, LegacySharpen legacySharpen) {
        this.decoder = decoder;
        this.filterEngine = filterEngine;
        this.encoder = encoder;
        this.legacySharpen = legacySharpen;
    }
    
    public Path process(Path inputPath, Path outputPath) {
        return process(inputPath, outputPath, new ProcessingOptions());
    }
    
    public Path process(Path inputPath, Path outputPath, ProcessingOptions options) {
        
        Frame[] frames = decoder.decode(inputPath);
        
        if (options.isGrayscaleEnabled()) {
            frames = filterEngine.grayscale(frames);
        }
        
        if (options.getScaleFactor() != 1.0) {
            frames = filterEngine.scale(frames, options.getScaleFactor());
        }
        
        if (options.getSharpenStrength() > 0) {
            frames = applyLegacySharpen(frames, options.getSharpenStrength());
        }
        
        return encoder.encode(frames, outputPath);
    }
    
    private Frame[] applyLegacySharpen(Frame[] frames, int strength) {
        String handle = "FRAMES_HANDLE"; // Convert frames to handle
        String newHandle = legacySharpen.applySharpen(handle, strength);
        return frames; 
    }
}
