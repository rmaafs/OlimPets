package me.Roy.OlimPets.Inventarios;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Lang.NOTE_PLING;
import static me.Roy.OlimPets.Lang.guiPartyPlayTitle;
import static me.Roy.OlimPets.LangItems.guiGamesRun;
import static me.Roy.OlimPets.LangItems.guiGamesRunSlot;
import static me.Roy.OlimPets.LangItems.guiGamesRunUse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiPartyPlay {

    public static Inventory invPartyPlay;
    
    public static void createInventories(){
        invPartyPlay = Bukkit.createInventory(null, 54, guiPartyPlayTitle);
    }
    
    public static void setPlayMenu(){
        if (guiGamesRunUse){
            invPartyPlay.setItem(guiGamesRunSlot, guiGamesRun);
        }
    }
    
    public static void open(Player p){
        p.openInventory(invPartyPlay);
        Extra.sonido(p, NOTE_PLING);
    }
}
