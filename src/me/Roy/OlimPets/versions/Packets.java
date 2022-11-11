package me.Roy.OlimPets.versions;

import me.Roy.OlimPets.Objetos.Pet;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public interface Packets {
    public Integer getPing(Player player);
    public void generar(Player p, Pet pet, int i, Location loc);
    public void generar(Player p, Pet pet, int i);
    public Zombie generar(Location loc);
    public void noAI(Entity bukkitEntity, int i);
    public void armar(Player p, Zombie zombie, Pet pet, int i);
    public void ponerFakeArmor(Player p, Integer entityid);
    public void ponerMano(Zombie z, ItemStack i);
}
