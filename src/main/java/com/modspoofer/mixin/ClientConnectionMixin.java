package com.modspoofer.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.modspoofer.util.ModLogger;
import com.modspoofer.util.PacketInterceptor;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void onPacketSend(Packet<?> packet, CallbackInfo ci) {
        PacketInterceptor.interceptPacket(packet, ci);
    }

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"))
    public void onChannelRead(ChannelHandlerContext ctx, Packet<?> packet, CallbackInfo ci) {
        ModLogger.logActivity("Packet received: " + packet.getClass().getSimpleName());
    }
}
