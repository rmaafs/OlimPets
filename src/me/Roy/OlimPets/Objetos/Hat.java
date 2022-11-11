package me.Roy.OlimPets.Objetos;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Lang.chats;
import org.bukkit.inventory.ItemStack;

public class Hat {
    String permission = "he";
    int slot;
    ItemStack item;
    
    public Hat(String c){
        slot = chats.getInt("hats." + c + ".slot");
        if (chats.contains("hats." + c + ".permission")) {
            permission = chats.getString("hats." + c + ".permission");
        }
        item = Extra.crearId(chats.getInt("hats." + c + ".id"), chats.getInt("hats." + c + ".data-value"), Extra.tc(chats.getString("hats." + c + ".name")), Extra.tCC(chats.getStringList("hats." + c + ".lore")), 1);
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
