package utils;

import java.time.Instant;

public class AuditLogger {
    public void log(String event, String details) {
        System.out.println(Instant.now() + " | " + event + " | " + details);
    }
}
