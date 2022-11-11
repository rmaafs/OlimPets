package me.Roy.OlimPets;

import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Lang.cpets;
import static me.Roy.OlimPets.Lang.cstats;
import static me.Roy.OlimPets.Lang.filePets;
import static me.Roy.OlimPets.Lang.initialElo;
import static me.Roy.OlimPets.Main.listKits;
import me.Roy.OlimPets.Objetos.Pet;
import org.bukkit.entity.Player;

public class PetControl {

    public static void comprobarPet(Player p) {
        if (!pets.containsKey(p)) {
            Pet pet;
            if (cpets.contains("pets." + p.getUniqueId().toString())) {
                pet = PetControl.loadPet(p);
            } else {
                pet = PetControl.createPet(p);
            }
            pets.put(p, pet);
        }
    }

    public static Pet loadPet(Player p) {
        Pet pet = new Pet(p);
        pet.setName(cpets.getString("pets." + p.getUniqueId().toString() + ".name"));
        pet.setColorName(cpets.getString("pets." + p.getUniqueId().toString() + ".colorname"));
        pet.setHelmet(InventoryToBase64.itemFromBase64(cpets.getString("pets." + p.getUniqueId().toString() + ".helmet")));
        pet.setChestplate(InventoryToBase64.itemFromBase64(cpets.getString("pets." + p.getUniqueId().toString() + ".chestplate")));
        pet.setLeggings(InventoryToBase64.itemFromBase64(cpets.getString("pets." + p.getUniqueId().toString() + ".leggings")));
        pet.setBoots(InventoryToBase64.itemFromBase64(cpets.getString("pets." + p.getUniqueId().toString() + ".boots")));
        pet.setMano(InventoryToBase64.itemFromBase64(cpets.getString("pets." + p.getUniqueId().toString() + ".mano")));

        for (String kit : listKits) {
            if (cstats.contains(p.getUniqueId().toString() + "." + kit + ".elo")) {
                pet.setElo(kit, cstats.getInt(p.getUniqueId().toString() + "." + kit + ".elo"));
            } else {
                pet.setElo(kit, initialElo);
            }
            if (cstats.contains(p.getUniqueId().toString() + "." + kit + ".played")) {
                pet.setPlayed(kit, cstats.getInt(p.getUniqueId().toString() + "." + kit + ".played"));
            } else {
                pet.setPlayed(kit, 0);
            }
            if (cstats.contains(p.getUniqueId().toString() + "." + kit + ".wins")) {
                pet.setWins(kit, cstats.getInt(p.getUniqueId().toString() + "." + kit + ".wins"));
            } else {
                pet.setWins(kit, 0);
            }
        }
        return pet;
    }

    public static Pet createPet(Player p) {
        Pet pet = new Pet(p);
        savePet(p, pet);
        return pet;
    }

    public static void savePet(Player p, Pet pet) {
        cpets.set("pets." + p.getUniqueId().toString() + ".name", pet.getName());
        cpets.set("pets." + p.getUniqueId().toString() + ".owner", p.getName());
        cpets.set("pets." + p.getUniqueId().toString() + ".colorname", pet.getColorName());
        cpets.set("pets." + p.getUniqueId().toString() + ".helmet", InventoryToBase64.itemToBase64(pet.getHelmet()));
        cpets.set("pets." + p.getUniqueId().toString() + ".chestplate", InventoryToBase64.itemToBase64(pet.getChestplate()));
        cpets.set("pets." + p.getUniqueId().toString() + ".leggings", InventoryToBase64.itemToBase64(pet.getLeggings()));
        cpets.set("pets." + p.getUniqueId().toString() + ".boots", InventoryToBase64.itemToBase64(pet.getBoots()));
        cpets.set("pets." + p.getUniqueId().toString() + ".mano", InventoryToBase64.itemToBase64(pet.getMano()));
        for (String kit : listKits) {
            if (pet.getElo(kit) != initialElo) {
                cstats.set(p.getUniqueId().toString() + "." + kit + ".elo", pet.getElo(kit));
            }
            if (pet.getPlayed(kit) != 0) {
                cstats.set(p.getUniqueId().toString() + "." + kit + ".played", pet.getPlayed(kit));
            }
            if (pet.getWins(kit) != 0) {
                cstats.set(p.getUniqueId().toString() + "." + kit + ".wins", pet.getWins(kit));
            }
        }

        Extra.guardar(filePets, cpets);
    }
}
