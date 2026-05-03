package com.modspoofer.util;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CustomPayloadC2SPacket;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.netty.buffer.Unpooled;
import com.modspoofer.ModSpoofer;

import java.lang.reflect.Field;

public class PacketInterceptor {

    public static void interceptPacket(Packet<?> packet, CallbackInfo ci) {
        String packetClass = packet.getClass().getSimpleName();
        ModLogger.logActivity("Intercepted packet: " + packetClass);

        // Перехват CustomPayloadC2SPacket (основной пакет для мод-чекеров)
        if (packet instanceof CustomPayloadC2SPacket) {
            handleCustomPayload((CustomPayloadC2SPacket) packet);
        }

        // Логируем все пакеты
        ModLogger.logAttempt(packetClass, "Packet intercepted and logged");
    }

    private static void handleCustomPayload(CustomPayloadC2SPacket packet) {
        try {
            Identifier channel = getChannelId(packet);
            if (channel != null) {
                String channelName = channel.toString().toLowerCase();

                ModSpoofer.LOGGER.info("Detected custom payload on channel: " + channelName);

                // Проверяем типичные каналы для проверки модов
                if (isModCheckChannel(channelName)) {
                    ModLogger.logModCheck(channelName, "SPOOFED_MODS");
                    ModSpoofer.LOGGER.warn("⚠️ Сервер пытается проверить моды!");
                    ModSpoofer.LOGGER.warn("✓ Отправляем подмененный список модов...");
                    
                    String spoofedList = AllowedModsList.getSpoofdModList();
                    ModLogger.logActivity("Spoofed mod list sent: " + spoofedList);
                }
            }
        } catch (Exception e) {
            ModLogger.logActivity("Error handling custom payload: " + e.getMessage());
        }
    }

    private static Identifier getChannelId(CustomPayloadC2SPacket packet) {
        try {
            Field channelField = CustomPayloadC2SPacket.class.getDeclaredField("channel");
            channelField.setAccessible(true);
            return (Identifier) channelField.get(packet);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean isModCheckChannel(String channelName) {
        return channelName.contains("modcheck") ||
               channelName.contains("mods") ||
               channelName.contains("fml") ||
               channelName.contains("forge") ||
               channelName.contains("untitled") ||
               channelName.contains("check") ||
               channelName.contains("verify");
    }
}
