package net.kevarion.beaconbattle.team;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final String name;
    private final ChatColor color;
    private final Location islandLocation;
    private boolean beaconBroken;
    private final List<Player> players;
    private final Material woolType;

    public Team(String name, ChatColor color, Location islandLocation) {
        this.name = name;
        this.color = color;
        this.islandLocation = islandLocation;
        this.beaconBroken = false;
        this.players = new ArrayList<>();
        this.woolType = getWoolType(color); // Automatically set the wool type based on color
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public Location getIslandLocation() {
        return islandLocation;
    }

    public boolean isBeaconBroken() {
        return beaconBroken;
    }

    public void setBeaconBroken(boolean beaconBroken) {
        this.beaconBroken = beaconBroken;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Material getWoolType() {
        return woolType;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean isAlive() {
        return !beaconBroken;
    }

    public void spawnPlayer(Player player) {
        player.teleport(islandLocation); // Teleport the player to their island
        player.getInventory().addItem(new ItemStack(woolType, 128)); // Add wool
        player.getInventory().addItem(new ItemStack(Material.STONE_SWORD)); // Add sword
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_PICKAXE, 1));
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));

        ItemStack helmet = createColoredArmorPiece(Material.LEATHER_HELMET);
        player.getInventory().setHelmet(helmet);

        ItemStack chestplate = createColoredArmorPiece(Material.LEATHER_CHESTPLATE);
        player.getInventory().setChestplate(chestplate);

        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
    }

    private Color getColorFromChatColor(ChatColor chatColor) {
        switch (chatColor) {
            case RED: return Color.fromRGB(255, 0, 0);
            case BLUE: return Color.fromRGB(0, 0, 255);
            case GREEN: return Color.fromRGB(0, 255, 0);
            case YELLOW: return Color.fromRGB(255, 255, 0);
            case AQUA: return Color.fromRGB(0, 255, 255);
            case WHITE: return Color.fromRGB(255, 255, 255);
            case LIGHT_PURPLE: return Color.fromRGB(255, 182, 193); // Use a better representation for light purple
            case DARK_GRAY: return Color.fromRGB(169, 169, 169);
            default: return Color.fromRGB(255, 255, 255); // Default to white
        }
    }

    private Material getWoolType(ChatColor chatColor) {
        switch (chatColor) {
            case RED: return Material.RED_WOOL;
            case BLUE: return Material.BLUE_WOOL;
            case GREEN: return Material.GREEN_WOOL;
            case YELLOW: return Material.YELLOW_WOOL;
            case AQUA: return Material.CYAN_WOOL;
            case WHITE: return Material.WHITE_WOOL;
            case LIGHT_PURPLE: return Material.PINK_WOOL;
            case DARK_GRAY: return Material.GRAY_WOOL;
            default: return Material.WHITE_WOOL; // Default to white wool
        }
    }

    private ItemStack createColoredArmorPiece(Material material) {
        ItemStack armorPiece = new ItemStack(material);
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) armorPiece.getItemMeta();
        if (armorMeta != null) {
            armorMeta.setColor(getColorFromChatColor(color));
            armorPiece.setItemMeta(armorMeta);
        }
        return armorPiece;
    }
}