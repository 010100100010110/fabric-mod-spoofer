package com.modspoofer.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModLogger {
    private static final File LOG_DIR = new File("mods/.modspoofer");
    private static final File ATTEMPTS_LOG = new File(LOG_DIR, "mod_check_attempts.log");
    private static final File ACTIVITY_LOG = new File(LOG_DIR, "activity.log");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void init() {
        if (!LOG_DIR.exists()) {
            LOG_DIR.mkdirs();
        }
        if (!ATTEMPTS_LOG.exists()) {
            try {
                ATTEMPTS_LOG.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!ACTIVITY_LOG.exists()) {
            try {
                ACTIVITY_LOG.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void logAttempt(String packetName, String details) {
        String timestamp = DATE_FORMAT.format(new Date());
        String logEntry = String.format("[%s] PACKET_INTERCEPT - %s | %s\n", timestamp, packetName, details);
        try (FileWriter writer = new FileWriter(ATTEMPTS_LOG, true)) {
            writer.write(logEntry);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logActivity(String message) {
        String timestamp = DATE_FORMAT.format(new Date());
        String logEntry = String.format("[%s] %s\n", timestamp, message);
        try (FileWriter writer = new FileWriter(ACTIVITY_LOG, true)) {
            writer.write(logEntry);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logModCheck(String serverPacket, String spoofedData) {
        logAttempt("MOD_CHECK", "Server: " + serverPacket + " | Spoofed: " + spoofedData);
    }
}
