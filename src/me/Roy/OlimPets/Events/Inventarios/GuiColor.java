package me.Roy.OlimPets.Events.Inventarios;

import static me.Roy.OlimPets.Extra.colores;
import static me.Roy.OlimPets.Extra.handItems;
import static me.Roy.OlimPets.Extra.sombreros;
import static me.Roy.OlimPets.Lang.showNoPermissionColor;
import static me.Roy.OlimPets.LangItems.guiPetColorArmorHat;
import static me.Roy.OlimPets.LangItems.guiPetColorArmorHatSlot;
import static me.Roy.OlimPets.LangItems.guiPetColorArmorLeave;
import static me.Roy.OlimPets.LangItems.guiPetColorArmorLeaveSlot;
import me.Roy.OlimPets.Objetos.Colores;
import me.Roy.OlimPets.Objetos.Hand;
import me.Roy.OlimPets.Objetos.Hat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiColor {

    public static void openColor(Player p, String type, Inventory inv) {
        for (Colores c: colores){
            if (showNoPermissionColor || c.getPermission().equals("he") || p.hasPermission("olimpet.color.*") || p.hasPermission(c.getPermission())){
                if (!type.equals(c.getType())){
                    c.setItem(type);
                }
                inv.setItem(c.getSlot(), c.getItem());
            }
        }
        if (type.equals("HELMET")){
            inv.setItem(guiPetColorArmorHatSlot, guiPetColorArmorHat);
        }
        inv.setItem(guiPetColorArmorLeaveSlot, guiPetColorArmorLeave);
    }
    
    public static void openHats(Player p, Inventory inv){
        for (Hat c: sombreros){
            if (showNoPermissionColor || c.getPermission().equals("he") || p.hasPermission("olimpet.hat.*") || p.hasPermission(c.getPermission())){
                inv.setItem(c.getSlot(), c.getItem());
            }
        }
        inv.setItem(guiPetColorArmorLeaveSlot, guiPetColorArmorLeave);
    }
    
    public static void openHandItems(Player p, Inventory inv){
        for (Hand c: handItems){
            if (showNoPermissionColor || c.getPermission().equals("he") || p.hasPermission("olimpet.handitem.*") || p.hasPermission(c.getPermission())){
                inv.setItem(c.getSlot(), c.getItem());
            }
        }
        inv.setItem(guiPetColorArmorLeaveSlot, guiPetColorArmorLeave);
    }
}
