package net.kevarion.beaconbattle.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.kevarion.beaconbattle.arena.ArenaManager;
import net.kevarion.beaconbattle.arena.Arena;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("beaconbattle|bb")
@CommandPermission("kerivax.developer")
public class MainCommand extends BaseCommand {

    @Default
    public void main(Player player) {
        player.sendMessage(ChatColor.RED + "Error: Use /beaconbattle [argument]");
    }

    // Command: /beaconbattle create-arena <ArenaName>
    @Subcommand("create-arena")
    public void createArena(Player player, String arenaName) {
        if (ArenaManager.getInstance().arenaExists(arenaName)) {
            player.sendMessage(ChatColor.RED + "Arena " + arenaName + " already exists!");
            return;
        }

        Arena arena = new Arena(arenaName);
        ArenaManager.getInstance().addArena(arena);
        player.sendMessage(ChatColor.GREEN + "Arena " + arenaName + " created successfully!");
    }

    // Command: /beaconbattle teleport-arena <ArenaName>
    @Subcommand("teleport-arena")
    public void teleportArena(Player player, String arenaName) {
        Arena arena = ArenaManager.getInstance().getArena(arenaName);
        if (arena == null) {
            player.sendMessage(ChatColor.RED + "Arena " + arenaName + " not found!");
            return;
        }

        player.teleport(arena.getCenterLocation());
        player.sendMessage(ChatColor.GREEN + "Teleported to " + arenaName + " arena.");
    }

    // Command: /beaconbattle arena <ArenaName> team <TeamColor> setspawn
    @Subcommand("arena")
    public void setTeamSpawn(Player player, String arenaName, String teamColor, String action) {
        if (!action.equalsIgnoreCase("setspawn")) {
            player.sendMessage(ChatColor.RED + "Invalid action! Use 'setspawn'.");
            return;
        }

        Arena arena = ArenaManager.getInstance().getArena(arenaName);
        if (arena == null) {
            player.sendMessage(ChatColor.RED + "Arena " + arenaName + " not found!");
            return;
        }

        if (teamColor.equalsIgnoreCase("red")) {
            arena.setTeamSpawn("red", player.getLocation());
            player.sendMessage(ChatColor.GREEN + "Red team's spawn point set!");
        } else if (teamColor.equalsIgnoreCase("blue")) {
            arena.setTeamSpawn("blue", player.getLocation());
            player.sendMessage(ChatColor.GREEN + "Blue team's spawn point set!");
        } else {
            player.sendMessage(ChatColor.RED + "Invalid team color! Use 'red' or 'blue'.");
        }
    }

    // Command: /beaconbattle delete-arena <ArenaName>
    @Subcommand("delete-arena")
    public void deleteArena(Player player, String arenaName) {
        if (!ArenaManager.getInstance().arenaExists(arenaName)) {
            player.sendMessage(ChatColor.RED + "Arena " + arenaName + " not found!");
            return;
        }

        ArenaManager.getInstance().removeArena(arenaName);
        player.sendMessage(ChatColor.GREEN + "Arena " + arenaName + " deleted successfully.");
    }

    // Command: /beaconbattle list-arenas
    @Subcommand("list-arenas")
    public void listArenas(Player player) {
        player.sendMessage(ChatColor.GREEN + "Arenas: " + String.join(", ", ArenaManager.getInstance().getArenaNames()));
    }

}
