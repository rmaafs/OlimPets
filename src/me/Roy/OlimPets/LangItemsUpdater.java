package me.Roy.OlimPets;

import static me.Roy.OlimPets.Inventarios.GuiPartyPlay.invPartyPlay;
import static me.Roy.OlimPets.Inventarios.GuiRankedControl.invRanked;
import static me.Roy.OlimPets.Inventarios.GuiUnrankedControl.invUnranked;
import static me.Roy.OlimPets.LangItems.guiGamesAntilover;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverDV;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverId;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverLore;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverName;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverSlot;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverUse;
import static me.Roy.OlimPets.LangItems.guiGamesCount;
import static me.Roy.OlimPets.LangItems.guiGamesCountDV;
import static me.Roy.OlimPets.LangItems.guiGamesCountId;
import static me.Roy.OlimPets.LangItems.guiGamesCountLore;
import static me.Roy.OlimPets.LangItems.guiGamesCountName;
import static me.Roy.OlimPets.LangItems.guiGamesCountSlot;
import static me.Roy.OlimPets.LangItems.guiGamesCountUse;
import static me.Roy.OlimPets.LangItems.guiGamesJump;
import static me.Roy.OlimPets.LangItems.guiGamesJumpDV;
import static me.Roy.OlimPets.LangItems.guiGamesJumpId;
import static me.Roy.OlimPets.LangItems.guiGamesJumpLore;
import static me.Roy.OlimPets.LangItems.guiGamesJumpName;
import static me.Roy.OlimPets.LangItems.guiGamesJumpSlot;
import static me.Roy.OlimPets.LangItems.guiGamesJumpUse;
import static me.Roy.OlimPets.LangItems.guiGamesRun;
import static me.Roy.OlimPets.LangItems.guiGamesRunDV;
import static me.Roy.OlimPets.LangItems.guiGamesRunId;
import static me.Roy.OlimPets.LangItems.guiGamesRunLore;
import static me.Roy.OlimPets.LangItems.guiGamesRunName;
import static me.Roy.OlimPets.LangItems.guiGamesRunSlot;
import static me.Roy.OlimPets.LangItems.guiGamesRunUse;

public class LangItemsUpdater {

    public static void updateLoreRunUnrankedMenu() {
        guiGamesRun = Extra.crearIdLore(guiGamesRunId, guiGamesRunDV, guiGamesRunName, guiGamesRunLore, 1, "runUn");
        if (guiGamesRunUse) {
            invUnranked.setItem(guiGamesRunSlot, guiGamesRun);
        }
    }

    public static void updateLoreRunRankedMenu() {
        guiGamesRun = Extra.crearIdLore(guiGamesRunId, guiGamesRunDV, guiGamesRunName, guiGamesRunLore, 1, "runR");
        if (guiGamesRunUse) {
            invRanked.setItem(guiGamesRunSlot, guiGamesRun);
        }
    }

    public static void updateLoreRunPartyMenu() {
        guiGamesRun = Extra.crearIdLore(guiGamesRunId, guiGamesRunDV, guiGamesRunName, guiGamesRunLore, 1, "runP");
        if (guiGamesRunUse) {
            invPartyPlay.setItem(guiGamesRunSlot, guiGamesRun);
        }
    }
    
    //---------------------
    
    public static void updateLoreJumpUnrankedMenu(){
        guiGamesJump = Extra.crearIdLore(guiGamesJumpId, guiGamesJumpDV, guiGamesJumpName, guiGamesJumpLore, 1, "jumpUn");
        if (guiGamesJumpUse){
            invUnranked.setItem(guiGamesJumpSlot, guiGamesJump);
        }
    }
    
    public static void updateLoreJumpRankedMenu(){
        guiGamesJump = Extra.crearIdLore(guiGamesJumpId, guiGamesJumpDV, guiGamesJumpName, guiGamesJumpLore, 1, "jumpR");
        if (guiGamesJumpUse){
            invRanked.setItem(guiGamesJumpSlot, guiGamesJump);
        }
    }
    
    public static void updateLoreJumpPartyMenu(){
        guiGamesJump = Extra.crearIdLore(guiGamesJumpId, guiGamesJumpDV, guiGamesJumpName, guiGamesJumpLore, 1, "jumpP");
        if (guiGamesJumpUse){
            invPartyPlay.setItem(guiGamesJumpSlot, guiGamesJump);
        }
    }
    
    //---------------------
    
    public static void updateLoreCountUnrankedMenu(){
        guiGamesCount = Extra.crearIdLore(guiGamesCountId, guiGamesCountDV, guiGamesCountName, guiGamesCountLore, 1, "countUn");
        if (guiGamesCountUse){
            invUnranked.setItem(guiGamesCountSlot, guiGamesCount);
        }
    }
    
    public static void updateLoreCountRankedMenu(){
        guiGamesCount = Extra.crearIdLore(guiGamesCountId, guiGamesCountDV, guiGamesCountName, guiGamesCountLore, 1, "countR");
        if (guiGamesCountUse){
            invRanked.setItem(guiGamesCountSlot, guiGamesCount);
        }
    }
    
    public static void updateLoreCountPartyMenu(){
        guiGamesCount = Extra.crearIdLore(guiGamesCountId, guiGamesCountDV, guiGamesCountName, guiGamesCountLore, 1, "countP");
        if (guiGamesCountUse){
            invPartyPlay.setItem(guiGamesCountSlot, guiGamesCount);
        }
    }
    
    //---------------------
    
    public static void updateLoreAntiloverUnrankedMenu(){
        guiGamesAntilover = Extra.crearIdLore(guiGamesAntiloverId, guiGamesAntiloverDV, guiGamesAntiloverName, guiGamesAntiloverLore, 1, "antiloverUn");
        if (guiGamesAntiloverUse){
            invUnranked.setItem(guiGamesAntiloverSlot, guiGamesAntilover);
        }
    }
    
    public static void updateLoreAntiloverRankedMenu(){
        guiGamesAntilover = Extra.crearIdLore(guiGamesAntiloverId, guiGamesAntiloverDV, guiGamesAntiloverName, guiGamesAntiloverLore, 1, "antiloverR");
        if (guiGamesAntiloverUse){
            invRanked.setItem(guiGamesAntiloverSlot, guiGamesAntilover);
        }
    }
    
    public static void updateLoreAntiloverPartyMenu(){
        guiGamesAntilover= Extra.crearIdLore(guiGamesAntiloverId, guiGamesAntiloverDV, guiGamesAntiloverName, guiGamesAntiloverLore, 1, "antiloverP");
        if (guiGamesAntiloverUse){
            invPartyPlay.setItem(guiGamesAntiloverSlot, guiGamesAntilover);
        }
    }
}
