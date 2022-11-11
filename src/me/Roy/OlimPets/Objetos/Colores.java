package me.Roy.OlimPets.Objetos;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Lang.ccolors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Colores {

    String permission = "he", type = "CHESTPLATE", itemType = "LEATHER_";
    int slot;
    ItemStack item;

    public Colores(String c) {
        slot = ccolors.getInt("colors." + c + ".slot");
        if (ccolors.contains("colors." + c + ".permission")) {
            permission = ccolors.getString("colors." + c + ".permission");
        }
        item = Extra.crearTraje(Material.valueOf("LEATHER_"+type), org.bukkit.Color.fromBGR(ccolors.getInt("colors." + c + ".b"), ccolors.getInt("colors." + c + ".g"), ccolors.getInt("colors." + c + ".r")), Extra.tc(ccolors.getString("colors." + c + ".name")), Extra.tCC(ccolors.getStringList("colors." + c + ".lore")));
    }
    
    public Colores(String c, boolean t) {
        slot = ccolors.getInt("minerals." + c + ".slot");
        if (ccolors.contains("minerals." + c + ".permission")) {
            permission = ccolors.getString("minerals." + c + ".permission");
        }
        itemType = c.toUpperCase()+"_";
        item = new ItemStack(Material.valueOf(itemType+type));
    }

    public String getPermission() {
        return permission;
    }
    
    public String getType() {
        return permission;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }
    
    public String getItemType() {
        return itemType;
    }
    
    public void setItem(String t){
        type = t;
        item.setType(Material.valueOf(itemType+t));
    }
    
    public void setItemType(String t){
        itemType = t;
        item.setType(Material.valueOf(itemType+type));
    }
}
