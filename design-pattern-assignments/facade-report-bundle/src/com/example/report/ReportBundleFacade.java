package com.example.report;

import java.nio.file.Path;
import java.util.Map;

public class ReportBundleFacade {
    private final JsonWriter jsonWriter;
    private final Zipper zipper;
    private final AuditLog auditLog;
    
    public ReportBundleFacade() {
        this.jsonWriter = new JsonWriter();
        this.zipper = new Zipper();
        this.auditLog = new AuditLog();
    }
    
    public ReportBundleFacade(JsonWriter jsonWriter, Zipper zipper, AuditLog auditLog) {
        this.jsonWriter = jsonWriter;
        this.zipper = zipper;
        this.auditLog = auditLog;
    }
    
    public Path export(Map<String, Object> data, Path outputDir, String reportName) {
        Path jsonFile = jsonWriter.write(data, outputDir, reportName);
        
        Path zipFile = zipper.zip(jsonFile, outputDir.resolve(reportName + ".zip"));
        
        auditLog.log("exported " + zipFile);
        
        return zipFile;
    }
}
