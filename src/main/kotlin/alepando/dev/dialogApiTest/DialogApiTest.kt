package alepando.dev.dialogApiTest

import alepando.dev.dialogApiTest.commands.TestCommand
import alepando.dev.dialogapi.DialogAPI
import org.bukkit.plugin.java.JavaPlugin

class DialogApiTest : JavaPlugin() {

    override fun onEnable() {
        getCommand("test")!!.setExecutor(TestCommand())
        DialogAPI.initialize(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
