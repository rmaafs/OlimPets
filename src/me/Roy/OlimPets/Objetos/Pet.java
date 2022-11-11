package me.Roy.OlimPets.Objetos;

import java.util.HashMap;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Extra.petsZombie;
import me.Roy.OlimPets.InventoryToBase64;
import static me.Roy.OlimPets.Lang.cpets;
import static me.Roy.OlimPets.Lang.initialElo;
import static me.Roy.OlimPets.Main.version;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Pet {

    Player p;
    String name;
    String colorName;
    ItemStack casco, peto, pantalones, botas, mano;
    boolean summon = false;
    HashMap<String, Integer> elo = new HashMap<>();
    HashMap<String, Integer> played = new HashMap<>();
    HashMap<String, Integer> wins = new HashMap<>();

    public Pet(Player o) {
        p = o;
        name = p.getName();
        colorName = "§b";
        casco = new ItemStack(Material.LEATHER_HELMET);
        peto = new ItemStack(Material.LEATHER_CHESTPLATE);
        pantalones = new ItemStack(Material.LEATHER_LEGGINGS);
        botas = new ItemStack(Material.LEATHER_BOOTS);
        mano = new ItemStack(Material.STICK);
    }

    public Player getPlayer() {
        return p;
    }

    public String getName() {
        return name;
    }

    public String getColorName() {
        return colorName;
    }

    public ItemStack getHelmet() {
        return casco;
    }

    public ItemStack getChestplate() {
        return peto;
    }

    public ItemStack getLeggings() {
        return pantalones;
    }

    public ItemStack getBoots() {
        return botas;
    }

    public ItemStack getMano() {
        return mano;
    }

    public boolean getSummon() {
        return summon;
    }

    public Integer getElo(String kit) {
        if (elo.containsKey(kit)) {
            return elo.get(kit);
        } else {
            return initialElo;
        }
    }

    public Integer getWins(String kit) {
        if (wins.containsKey(kit)) {
            return wins.get(kit);
        } else {
            return 0;
        }
    }

    public Integer getPlayed(String kit) {
        if (played.containsKey(kit)) {
            return played.get(kit);
        } else {
            return 0;
        }
    }

    public void setElo(String kit, Integer i) {
        elo.put(kit, i);
    }

    public void setWins(String kit, Integer i) {
        wins.put(kit, i);
    }

    public void setPlayed(String kit, Integer i) {
        played.put(kit, i);
    }

    public void addElo(String kit, Integer i) {
        elo.put(kit, getElo(kit) + i);
    }

    public void addWins(String kit, Integer i) {
        wins.put(kit, getWins(kit) + i);
    }

    public void addPlayed(String kit, Integer i) {
        played.put(kit, getPlayed(kit) + i);
    }

    public void removeElo(String kit, Integer i) {
        elo.put(kit, getElo(kit) - i);
    }

    public void removeWins(String kit, Integer i) {
        wins.put(kit, getWins(kit) - i);
    }

    public void removePlayed(String kit, Integer i) {
        played.put(kit, getPlayed(kit) - i);
    }

    public void setPlayer(Player o) {
        p = o;
    }

    public void setName(String o) {
        name = o;
        cpets.set("pets." + p.getUniqueId().toString() + ".name", name);
    }

    public void setColorName(String o) {
        colorName = o;
        cpets.set("pets." + p.getUniqueId().toString() + ".colorname", colorName);
    }

    public void setHelmet(ItemStack i) {
        casco = i;
        cpets.set("pets." + p.getUniqueId().toString() + ".helmet", InventoryToBase64.itemToBase64(casco));
    }

    public void setChestplate(ItemStack i) {
        peto = i;
        cpets.set("pets." + p.getUniqueId().toString() + ".chestplate", InventoryToBase64.itemToBase64(peto));
    }

    public void setLeggings(ItemStack i) {
        pantalones = i;
        cpets.set("pets." + p.getUniqueId().toString() + ".leggings", InventoryToBase64.itemToBase64(pantalones));
    }

    public void setBoots(ItemStack i) {
        botas = i;
        cpets.set("pets." + p.getUniqueId().toString() + ".boots", InventoryToBase64.itemToBase64(botas));
    }

    public void setMano(ItemStack i) {
        mano = i;
        cpets.set("pets." + p.getUniqueId().toString() + ".mano", InventoryToBase64.itemToBase64(mano));
    }

    public void setSummon(boolean i) {
        summon = i;
    }

    public void summon(boolean t, int ia) {//1 es normal
        version.generar(p, pets.get(p), ia);
        if (t) {
            petsZombie.get(p).setTarget(p);
        }
    }

    public void summon(boolean t, int ia, Location loc) {
        version.generar(p, pets.get(p), ia, loc);
        if (t) {
            petsZombie.get(p).setTarget(p);
        }
    }

    public void kill() {
        petsZombie.get(p).remove();
    }
}
