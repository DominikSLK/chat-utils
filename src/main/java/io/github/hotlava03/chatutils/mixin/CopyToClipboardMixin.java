package io.github.hotlava03.chatutils.mixin;

import io.github.hotlava03.chatutils.events.EventHandler;
import io.github.hotlava03.chatutils.events.types.SendCommandEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.message.ChatMessageSigner;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class CopyToClipboardMixin {
    @Inject(method = "sendCommand(Lnet/minecraft/network/message/ChatMessageSigner;Ljava/lang/String;Lnet/minecraft/text/Text;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void onSendCommand(ChatMessageSigner signer, String command, Text preview, CallbackInfo info) {
        var event = new SendCommandEvent(info, signer, command, preview);
        EventHandler.getInstance().fire(EventHandler.EventType.SEND_COMMAND, event);
    }
}
