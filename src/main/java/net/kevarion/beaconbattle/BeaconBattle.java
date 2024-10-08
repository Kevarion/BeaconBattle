package net.kevarion.beaconbattle;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.kevarion.beaconbattle.arena.Arena;
import net.kevarion.beaconbattle.arena.ArenaManager;
import net.kevarion.beaconbattle.command.MainCommand;
import net.kevarion.beaconbattle.event.BlockListener;
import net.kevarion.beaconbattle.event.JoinQuitListener;
import net.kevarion.beaconbattle.game.GameManager;
import net.kevarion.beaconbattle.game.countdown.CountdownManager;
import net.kevarion.beaconbattle.scoreboard.SBManager;
import net.kevarion.beaconbattle.stat.StatManager;
import net.kevarion.beaconbattle.stat.StatTracker;
import net.kevarion.beaconbattle.storage.ArenaStorage;
import net.kevarion.beaconbattle.storage.DataStorage;
import net.kevarion.beaconbattle.storage.StatsStorage;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class BeaconBattle extends JavaPlugin {

    @Getter private static BeaconBattle instance;

    private PaperCommandManager commandManager;

    private ArenaManager arenaManager;
    private StatManager statManager;

    private GameManager gameManager;
    private CountdownManager countdownManager;
    private SBManager sbManager;

    @Getter
    private StatsStorage statsStorage;
    private ArenaStorage arenaStorage;
    private DataStorage dataStorage;

    public static BeaconBattle getInstance() {
        return instance;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {

        ArenaStorage arenaStorage = new ArenaStorage(this);
        statsStorage = new StatsStorage(this);

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
        statsStorage.saveStatsConfig();
        arenaStorage.saveArenaConfig();
        dataStorage.saveDataConfig();
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new MainCommand());

    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinQuitListener(sbManager), this);
        pm.registerEvents(new BlockListener(), this);

        pm.registerEvents(new StatTracker(statManager), this);

    }

    private void registerManagers() {

        this.arenaManager = new ArenaManager(new File(getDataFolder(), "arenas.yml"));
        this.statManager = new StatManager(statsStorage);

        this.gameManager = new GameManager(this, new Arena("default_arena_name"));
        this.countdownManager = new CountdownManager(gameManager);

    }

}
