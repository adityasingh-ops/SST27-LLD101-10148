package com.example.imports;

import java.nio.file.Path;
import java.util.List;

public class CsvProfileImporter implements ProfileImporter {
    private final NaiveCsvReader csvReader;
    private final ProfileService profileService;
    
    public CsvProfileImporter(NaiveCsvReader csvReader, ProfileService profileService) {
        this.csvReader = csvReader;
        this.profileService = profileService;
    }
    
    @Override
    public int importFrom(Path csvFile) {
        List<String[]> rows = csvReader.read(csvFile);
        int importedCount = 0;
        
       // skepping the header row
        for (int i = 0; i < rows.size(); i++) {
            String[] row = rows.get(i);
            
            // validate row length
            if (row.length >= 3) {
                try {
                    String id = row[0].trim();
                    String email = row[1].trim();
                    String displayName = row[2].trim();
                    
                    if (!id.isEmpty() && !email.isEmpty()) {
                        profileService.createProfile(id, email, displayName);
                        importedCount++;
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid row: " + String.join(",", row) + " - " + e.getMessage());
                }
            }
        }
        
        return importedCount;
    }
}
