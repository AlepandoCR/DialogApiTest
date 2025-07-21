package alepando.dev.dialogApiTest

import alepando.dev.dialogapi.body.types.PlainMessageDialogBody
import alepando.dev.dialogapi.body.types.builders.ItemDialogBodyBuilder
import alepando.dev.dialogapi.executor.PlayerOpener.openDialog
import alepando.dev.dialogapi.factory.button.Button
import alepando.dev.dialogapi.factory.button.data.ButtonDataBuilder
import alepando.dev.dialogapi.factory.button.data.KeyedAction
import alepando.dev.dialogapi.factory.data.DialogDataBuilder
import alepando.dev.dialogapi.factory.data.ResourceLocation
import alepando.dev.dialogapi.factory.input.options.MultilineOptions
import alepando.dev.dialogapi.factory.input.options.RangeInfo
import alepando.dev.dialogapi.factory.input.options.builders.EntryBuilder
import alepando.dev.dialogapi.factory.input.types.builders.BooleanInputBuilder
import alepando.dev.dialogapi.factory.input.types.builders.NumberRangeInputBuilder
import alepando.dev.dialogapi.factory.input.types.builders.SingleOptionInputBuilder
import alepando.dev.dialogapi.factory.input.types.builders.TextInputBuilder
import alepando.dev.dialogapi.types.builders.MultiActionDialogBuilder
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

object Test {
    fun createAndOpenDialog(player: Player) {
        val killPlayerNamespace = "dialog"
        val killPlayerPath = "damage_player"
        val killPlayerKey = ResourceLocation(killPlayerNamespace, killPlayerPath)

        val buttonData = ButtonDataBuilder()
            .label(Component.text("test button"))
            .width(100)
            .build()

        val exitButtonData = ButtonDataBuilder()
            .label(Component.text("exit button"))
            .width(80)
            .build()

        val action = KeyedAction(killPlayerKey, Optional.empty())

        val testButton = Button(buttonData, Optional.of(action))

        val exitButton = Button(exitButtonData)

        val booleanInput = BooleanInputBuilder()
            .label(Component.text("boolean test"))
            .key("boolean_test")
            .initial(false)
            .build()

        val singleOptionInput = SingleOptionInputBuilder()
            .label(Component.text("option"))
            .key("single_input")
            .width(100)
            .labelVisible(true)
            .addEntry(
                EntryBuilder()
                .initial(true) // No more than one initial
                .id("test_entry")
                .display(Component.text("test_entry"))
                .build()
            )
            .addEntry(
                EntryBuilder()
                    .id("entry_test")
                    .display(Component.text("entry_test"))
                    .build()
            )
            .build()

        val numberRangeInput = NumberRangeInputBuilder()
            .label(Component.text("Input"))
            .key("number_input")
            .width(150)
            .rangeInfo(RangeInfo(1.0f,10.0f))
            .labelFormat("")
            .build()

        val stringInput = TextInputBuilder()
            .label(Component.text("Input"))
            .width(256)
            .key("text_test")
            .initial("")
            .labelVisible(true)
            .maxLength(300)
            .multiline(MultilineOptions(10,20))
            .build()

        val itemBody =  ItemDialogBodyBuilder()
            .item(ItemStack(Material.LAPIS_LAZULI))
            .height(20)
            .width(20)
            .description("item_test",100)
            .showDecorations(true)
            .showTooltip(false)
            .build()

        val dialogBody = PlainMessageDialogBody(100, Component.text("body"))

        val dialogData = DialogDataBuilder()
            .title(Component.text("Test Menu"))
            .externalTitle(Component.text("Menu Test"))
            .canCloseWithEscape(true)
            .addBody(dialogBody)
            .addBody(itemBody)
            .addBody(dialogBody)
            .addInput(numberRangeInput)
            .addInput(stringInput)
            .addInput(booleanInput)
            .addInput(singleOptionInput)
            .build()

        val confirmationDialog = MultiActionDialogBuilder()
            .data(dialogData)
            .columns(1)
            .exitButton(exitButton)
            .addButton(testButton)
            .addButton(testButton)
            .build()

        player.openDialog(confirmationDialog)
    }
}