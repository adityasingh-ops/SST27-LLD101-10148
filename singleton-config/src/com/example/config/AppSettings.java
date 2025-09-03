package com.example.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * FAULTY "Singleton": public constructor, getInstance() returns a NEW instance each time,
 * not thread-safe, reload allowed anytime, mutable global state, reflection+serialization-friendly.
 */
public class AppSettings implements Serializable {
    private final Properties props = new Properties();

    private AppSettings() { } // should not be public for true singleton // made it private so not accesible

    // public static AppSettings getInstance() {
    //     return new AppSettings(); // returns a fresh instance (bug)
    // }
    public static volatile AppSettings instance;

    public static AppSettings getInstance() {
        if (instance == null) {
            synchronized (AppSettings.class) {
                if (instance == null) {
                    instance = new AppSettings();
                }
            }
        }
        return instance;
    }

    public synchronized void loadFromFile(Path file) {
        try (InputStream in = Files.newInputStream(file)) {
            props.load(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}
// just do this as we can make a singleton  class through enum

// public enum AppSettings {
//     INSTANCE; 
    
//     private final Properties props = new Properties();
    
//     // Methods work exactly the same as before
//     public synchronized void loadFromFile(Path file) {
//         try (InputStream in = Files.newInputStream(file)) {
//             props.load(in);
//         } catch (IOException e) {
//             throw new UncheckedIOException(e);
//         }
//     }
    
//     public String get(String key) {
//         return props.getProperty(key);
//     }
// }

//usage

// AppSettings.INSTANCE.loadFromFile(Path.of("config.properties"));
// String value = AppSettings.INSTANCE.get("someKey");