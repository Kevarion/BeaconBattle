package net.kevarion.beaconbattle.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("beaconbattle|bb")
@CommandPermission("kerivax.developer")
public class MainCommand extends BaseCommand {

    @Default
    public void main(Player player) {
        player.sendMessage(ChatColor.RED + "Error: Use /beaconbattle [argument]");
    }

    @Subcommand("setlobby")
    public void setLobby(Player player) {

    }

    @Subcommand("create-arena")
    public void createArena(Player player) {

    }

    @Subcommand("delete-arena")
    public void deleteArena(Player player) {

    }

    @Subcommand("list-arenas")
    public void listArenas(Player player) {

    }

    @Subcommand("spectate")
    public void spectate(Player player) {

    }

    @Subcommand("online-arenas")
    public void onlineArenas(Player player) {

    }

    @Subcommand("offline-arenas")
    public void offlineArenas() {

    }

    @Subcommand("force-start")
    public void forceStart(Player player) {

    }

    @Subcommand("force-join")
    public void forceJoin(Player player) {

    }

}
