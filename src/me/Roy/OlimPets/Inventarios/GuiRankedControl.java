package me.Roy.OlimPets.Inventarios;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Lang.NOTE_PLING;
import static me.Roy.OlimPets.Lang.guiRankedTitle;
import static me.Roy.OlimPets.LangItems.guiGamesAntilover;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverSlot;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverUse;
import static me.Roy.OlimPets.LangItems.guiGamesCount;
import static me.Roy.OlimPets.LangItems.guiGamesCountSlot;
import static me.Roy.OlimPets.LangItems.guiGamesCountUse;
import static me.Roy.OlimPets.LangItems.guiGamesJump;
import static me.Roy.OlimPets.LangItems.guiGamesJumpSlot;
import static me.Roy.OlimPets.LangItems.guiGamesJumpUse;
import static me.Roy.OlimPets.LangItems.guiGamesRun;
import static me.Roy.OlimPets.LangItems.guiGamesRunSlot;
import static me.Roy.OlimPets.LangItems.guiGamesRunUse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiRankedControl {
    
    public static Inventory invRanked;
    
    public static void createInventories(){
        invRanked = Bukkit.createInventory(null, 54, guiRankedTitle);
    }
    
    public static void setRankedMenu(){
        if (guiGamesRunUse){
            invRanked.setItem(guiGamesRunSlot, guiGamesRun);
        }
        if (guiGamesJumpUse){
            invRanked.setItem(guiGamesJumpSlot, guiGamesJump);
        }
        if (guiGamesCountUse){
            invRanked.setItem(guiGamesCountSlot, guiGamesCount);
        }
        if (guiGamesAntiloverUse){
            invRanked.setItem(guiGamesAntiloverSlot, guiGamesAntilover);
        }
    }
    
    public static void openUnranked(Player p){
        p.openInventory(invRanked);
        Extra.sonido(p, NOTE_PLING);
    }
}
