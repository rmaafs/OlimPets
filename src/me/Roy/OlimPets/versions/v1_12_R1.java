package me.Roy.OlimPets.versions;

import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Extra.petsZombie;
import static me.Roy.OlimPets.Extra.petsZombieDueño;
import me.Roy.OlimPets.Objetos.Pet;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityEquipment;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

public class v1_12_R1 implements Packets {

    @Override
    public Integer getPing(Player player) {
        return ((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer) player).getHandle().ping;
    }

    @Override //El 0 es inteligente, 1 no inteligente
    public void noAI(Entity bukkitEntity, int i) {
        net.minecraft.server.v1_12_R1.Entity nmsVillager = ((CraftEntity) bukkitEntity).getHandle();
        NBTTagCompound tag = new NBTTagCompound();
        nmsVillager.c(tag);
        tag.setInt("NoAI", i); //1 es menso
        nmsVillager.f(tag);
    }
    
    @Override
    public void generar(Player p, Pet pet, int i, Location loc) {
        Zombie zombie = ((Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE));
        armar(p, zombie, pet, i);
    }

    @Override
    public void generar(Player p, Pet pet, int i) {
        Zombie zombie = ((Zombie) p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
        armar(p, zombie, pet, i);
    }
    
    @Override
    public Zombie generar(Location loc) {
        Zombie zombie = ((Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE));
        CraftZombie craftZombie = ((CraftZombie) zombie);
        craftZombie.setBaby(true);
        craftZombie.setVillager(false);
        return zombie;
    }
    
    @Override
    public void armar(Player p, Zombie zombie, Pet pet, int i){
        noAI(zombie, i);
        zombie.setCustomNameVisible(true);
        zombie.setCustomName(pet.getName());
        CraftZombie craftZombie = ((CraftZombie) zombie);
        craftZombie.getEquipment().setItemInHand(pet.getMano());
        //craftZombie.getEquipment().setArmorContents(p.getInventory().getArmorContents());
        craftZombie.setBaby(true);
        craftZombie.setVillager(false);
        
        craftZombie.getEquipment().setHelmet(pet.getHelmet());
        craftZombie.getEquipment().setChestplate(pet.getChestplate());
        craftZombie.getEquipment().setLeggings(pet.getLeggings());
        craftZombie.getEquipment().setBoots(pet.getBoots());
        petsZombie.put(p, zombie);
        petsZombieDueño.put(zombie, p);
    }

    @Override
    public void ponerFakeArmor(Player p, Integer entityid) {
        Pet pet = pets.get(p);
        PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(entityid, EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(pet.getHelmet()));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        
        packet = new PacketPlayOutEntityEquipment(entityid, EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(pet.getChestplate()));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        
        packet = new PacketPlayOutEntityEquipment(entityid, EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(pet.getLeggings()));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        
        packet = new PacketPlayOutEntityEquipment(entityid, EnumItemSlot.FEET, CraftItemStack.asNMSCopy(pet.getBoots()));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        
        packet = new PacketPlayOutEntityEquipment(entityid, EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(pet.getMano()));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }
    
    @Override
    public void ponerMano(Zombie z, ItemStack i){
        CraftZombie cz = ((CraftZombie) z);
        cz.getEquipment().setItemInHand(i);
    }
}
