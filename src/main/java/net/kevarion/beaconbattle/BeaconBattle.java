package net.kevarion.beaconbattle;

import co.aikar.commands.PaperCommandManager;
import jdk.tools.jmod.Main;
import lombok.Getter;
import net.kevarion.beaconbattle.command.MainCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class BeaconBattle extends JavaPlugin {

    @Getter private static BeaconBattle instance;
    private PaperCommandManager commandManager;

    public static BeaconBattle getInstance() {
        return instance;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {

        instance = this;
        getLogger().info("Enabled.");

        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        getLogger().severe("Disabled.");
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new MainCommand());

    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
    }

}
