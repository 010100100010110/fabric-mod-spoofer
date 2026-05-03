package com.modspoofer;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.modspoofer.util.ModLogger;

public class ModSpoofer implements ModInitializer {
    public static final String MOD_ID = "mod_spoofer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("========================================");
        LOGGER.info("Mod Spoofer initialized successfully!");
        LOGGER.info("========================================");
        ModLogger.init();
        LOGGER.info("Mod packet interception active.");
    }
}
