package alepando.dev.dialogApiTest.listeners

import alepando.dev.dialogapi.executor.events.PlayerDialogInteractionEvent
import alepando.dev.dialogapi.factory.actions.KillPlayerAction
import alepando.dev.dialogapi.packets.reader.types.PlayerReturnValueReader
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object DialogListener: Listener {
    @EventHandler
    fun checkDialog(event: PlayerDialogInteractionEvent){
        event.read(PlayerReturnValueReader)
        event.action(KillPlayerAction)
    }
}