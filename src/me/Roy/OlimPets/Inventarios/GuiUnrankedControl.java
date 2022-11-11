package me.Roy.OlimPets.Inventarios;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Lang.NOTE_PLING;
import static me.Roy.OlimPets.Lang.guiUnrankedTitle;
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

public class GuiUnrankedControl {
    
    public static Inventory invUnranked, invCreatemap;
    
    public static void createInventories(){
        invUnranked = Bukkit.createInventory(null, 54, guiUnrankedTitle);
        invCreatemap = Bukkit.createInventory(null, 54, "Create Map");
    }
    
    public static void setUnrankedMenu(){
        invCreatemap.setItem(guiGamesRunSlot, guiGamesRun);
        invCreatemap.setItem(guiGamesJumpSlot, guiGamesJump);
        invCreatemap.setItem(guiGamesCountSlot, guiGamesCount);
        invCreatemap.setItem(guiGamesAntiloverSlot, guiGamesAntilover);
        if (guiGamesRunUse){
            invUnranked.setItem(guiGamesRunSlot, guiGamesRun);
        }
        if (guiGamesJumpUse){
            invUnranked.setItem(guiGamesJumpSlot, guiGamesJump);
        }
        if (guiGamesCountUse){
            invUnranked.setItem(guiGamesCountSlot, guiGamesCount);
        }
        if (guiGamesAntiloverUse){
            invUnranked.setItem(guiGamesAntiloverSlot, guiGamesAntilover);
        }
    }
    
    public static void updateUnrankedMenu(){
        if (guiGamesRunUse){
            invUnranked.setItem(guiGamesRunSlot, guiGamesRun);
        }
        if (guiGamesJumpUse){
            invUnranked.setItem(guiGamesJumpSlot, guiGamesJump);
        }
        if (guiGamesCountUse){
            invUnranked.setItem(guiGamesCountSlot, guiGamesCount);
        }
        if (guiGamesAntiloverUse){
            invUnranked.setItem(guiGamesAntiloverSlot, guiGamesAntilover);
        }
    }
    
    public static void openUnranked(Player p){
        p.openInventory(invUnranked);
        Extra.sonido(p, NOTE_PLING);
    }
    
    public static void openCreateMap(Player p){
        p.openInventory(invCreatemap);
        Extra.sonido(p, NOTE_PLING);
    }
}
