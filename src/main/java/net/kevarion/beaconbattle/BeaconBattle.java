package net.kevarion.beaconbattle;

import co.aikar.commands.PaperCommandManager;
import jdk.tools.jmod.Main;
import lombok.Getter;
import net.kevarion.beaconbattle.arena.ArenaManager;
import net.kevarion.beaconbattle.command.MainCommand;
import net.kevarion.beaconbattle.stat.StatManager;
import net.kevarion.beaconbattle.storage.ArenaStorage;
import net.kevarion.beaconbattle.storage.StatsStorage;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class BeaconBattle extends JavaPlugin {

    @Getter private static BeaconBattle instance;

    private PaperCommandManager commandManager;

    private ArenaManager arenaManager;
    private StatManager statManager;

    public static BeaconBattle getInstance() {
        return instance;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {

        ArenaStorage arenaStorage = new ArenaStorage(this);
        StatsStorage statsStorage = new StatsStorage(this);

        instance = this;
        getLogger().info("Enabled.");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerCommands();
        registerEvents();
        registerManagers();

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

    private void registerManagers() {

        this.arenaManager = new ArenaManager();
        this.statManager = new StatManager();

    }

}
