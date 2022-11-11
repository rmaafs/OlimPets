package me.Roy.OlimPets.Objetos;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Lang.chanditems;
import org.bukkit.inventory.ItemStack;

public class Hand {
    String permission = "he";
    int slot;
    ItemStack item;
    
    public Hand(String c){
        slot = chanditems.getInt("items." + c + ".slot");
        if (chanditems.contains("items." + c + ".permission")) {
            permission = chanditems.getString("items." + c + ".permission");
        }
        item = Extra.crearId(chanditems.getInt("items." + c + ".id"), chanditems.getInt("items." + c + ".data-value"), Extra.tc(chanditems.getString("items." + c + ".name")), Extra.tCC(chanditems.getStringList("items." + c + ".lore")), 1);
    }
    
    public String getPermission(){
        return permission;
    }
    
    public int getSlot(){
        return slot;
    }
    
    public ItemStack getItem(){
        return item;
    }
}
