package com.example.video;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        VideoPipelineFacade pipeline = new VideoPipelineFacade();
        Path result = pipeline.process(Path.of("in.mp4"), Path.of("out.mp4"));
        System.out.println("Wrote " + result);

        ProcessingOptions options = new ProcessingOptions()
                .withGrayscale(true)
                .withScale(0.75)
                .withSharpen(5);
        
        Path customResult = pipeline.process(Path.of("in2.mp4"), Path.of("out2.mp4"), options);
        System.out.println("Custom processing wrote " + customResult);
    }
}
