package me.Roy.OlimPets.Inventarios;

import java.util.ArrayList;
import java.util.List;
import me.Roy.OlimPets.Events.Inventarios.GuiColor;
import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.pets;
import static me.Roy.OlimPets.Lang.ORB_PICKUP;
import static me.Roy.OlimPets.Lang.VILLAGER_NO;
import static me.Roy.OlimPets.Lang.VILLAGER_YES;
import static me.Roy.OlimPets.Lang.guiArmorBootsTitle;
import static me.Roy.OlimPets.Lang.guiArmorChestplateTitle;
import static me.Roy.OlimPets.Lang.guiArmorHandItemsTitle;
import static me.Roy.OlimPets.Lang.guiArmorHandTitle;
import static me.Roy.OlimPets.Lang.guiArmorHatTitle;
import static me.Roy.OlimPets.Lang.guiArmorHelmetTitle;
import static me.Roy.OlimPets.Lang.guiArmorLeggingsTitle;
import static me.Roy.OlimPets.Lang.guiPetArmorBootsSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorChestplateSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorHandSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorHelmetSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorLeggingsSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorTitle;
import static me.Roy.OlimPets.Lang.guiPetTitle;
import static me.Roy.OlimPets.LangColors.guiColorSize;
import static me.Roy.OlimPets.LangItems.guiPetMenuArmor;
import static me.Roy.OlimPets.LangItems.guiPetMenuArmorSlot;
import static me.Roy.OlimPets.LangItems.guiPetMenuSummonSlot;
import static me.Roy.OlimPets.LangItems.guiPetMenuName;
import static me.Roy.OlimPets.LangItems.guiPetMenuNameSlot;
import static me.Roy.OlimPets.LangItems.guiPetMenuSummonDisabled;
import static me.Roy.OlimPets.LangItems.guiPetMenuSummonEnabled;
import me.Roy.OlimPets.Objetos.Pet;
import me.Roy.OlimPets.PetControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiPet {

    public static void abrirMenu(Player p) {
        PetControl.comprobarPet(p);
        Pet pet = pets.get(p);
        Inventory inv = Bukkit.createInventory(null, 54, guiPetTitle);
        inv.setItem(guiPetMenuNameSlot, setVariables(guiPetMenuName.clone(), pet));
        if (pet.getSummon()){
            inv.setItem(guiPetMenuSummonSlot, setVariables(guiPetMenuSummonDisabled.clone(), pet));
        } else {
            inv.setItem(guiPetMenuSummonSlot, setVariables(guiPetMenuSummonEnabled.clone(), pet));
        }
        inv.setItem(guiPetMenuArmorSlot, setVariables(guiPetMenuArmor.clone(), pet));
        p.openInventory(inv);
        Extra.sonido(p, ORB_PICKUP);
    }

    public static ItemStack setVariables(ItemStack item, Pet pet) {
        ItemMeta meta = item.getItemMeta();
        List<String> l = new ArrayList<>();
        for (String s: meta.getLore()){
            if (s.contains("<name>")){
                s = s.replaceAll("<name>", pet.getName());
            }
            if (s.contains("<color>")){
                s = s.replaceAll("<color>", pet.getColorName());
            }
            l.add(Extra.tc(s));
        }
        meta.setLore(l);
        item.setItemMeta(meta);
        return item;
    }
    
    public static void abrirArmor(Player p){
        Pet pet = pets.get(p);
        Inventory inv = Bukkit.createInventory(null, guiColorSize, guiPetArmorTitle);
        inv.setItem(guiPetArmorHelmetSlot, pet.getHelmet());
        inv.setItem(guiPetArmorChestplateSlot, pet.getChestplate());
        inv.setItem(guiPetArmorLeggingsSlot, pet.getLeggings());
        inv.setItem(guiPetArmorBootsSlot, pet.getBoots());
        inv.setItem(guiPetArmorHandSlot, pet.getMano());
        p.openInventory(inv);
        Extra.sonido(p, VILLAGER_YES);
    }
    
    public static void abrirColorArmor(Player p, String type){
        Inventory inv = p.getInventory();
        switch (type) {
            case "HELMET":
                inv = Bukkit.createInventory(null, guiColorSize, guiArmorHelmetTitle);
                break;
            case "CHESTPLATE":
                inv = Bukkit.createInventory(null, guiColorSize, guiArmorChestplateTitle);
                break;
            case "LEGGINGS":
                inv = Bukkit.createInventory(null, guiColorSize, guiArmorLeggingsTitle);
                break;
            case "BOOTS":
                inv = Bukkit.createInventory(null, guiColorSize, guiArmorBootsTitle);
                break;
            case "HAND":
                inv = Bukkit.createInventory(null, guiColorSize, guiArmorHandTitle);
                break;
        }
        GuiColor.openColor(p, type, inv);
        p.openInventory(inv);
        Extra.sonido(p, VILLAGER_YES);
    }
    
    public static void abrirHats(Player p){
        Inventory inv = Bukkit.createInventory(null, guiColorSize, guiArmorHatTitle);
        GuiColor.openHats(p, inv);
        p.openInventory(inv);
        Extra.sonido(p, VILLAGER_NO);
    }
    
    public static void abrirHandItems(Player p){
        Inventory inv = Bukkit.createInventory(null, guiColorSize, guiArmorHandItemsTitle);
        GuiColor.openHandItems(p, inv);
        p.openInventory(inv);
        Extra.sonido(p, VILLAGER_NO);
    }
}
