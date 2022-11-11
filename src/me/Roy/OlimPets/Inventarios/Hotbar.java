package me.Roy.OlimPets.Inventarios;

import static me.Roy.OlimPets.LangItems.hotbarEvents;
import static me.Roy.OlimPets.LangItems.hotbarEventsSlot;
import static me.Roy.OlimPets.LangItems.hotbarLeave;
import static me.Roy.OlimPets.LangItems.hotbarLeaveSlot;
import static me.Roy.OlimPets.LangItems.hotbarParty;
import static me.Roy.OlimPets.LangItems.hotbarPartyPlay;
import static me.Roy.OlimPets.LangItems.hotbarPartyPlaySlot;
import static me.Roy.OlimPets.LangItems.hotbarPartySlot;
import static me.Roy.OlimPets.LangItems.hotbarPet;
import static me.Roy.OlimPets.LangItems.hotbarPetSlot;
import static me.Roy.OlimPets.LangItems.hotbarRanked;
import static me.Roy.OlimPets.LangItems.hotbarRankedSlot;
import static me.Roy.OlimPets.LangItems.hotbarTournaments;
import static me.Roy.OlimPets.LangItems.hotbarTournamentsSlot;
import static me.Roy.OlimPets.LangItems.hotbarUnRanked;
import static me.Roy.OlimPets.LangItems.hotbarUnRankedSlot;
import static me.Roy.OlimPets.LangItems.hotbarWaitingLeave;
import static me.Roy.OlimPets.LangItems.hotbarWaitingLeaveSlot;
import static me.Roy.OlimPets.Main.plugin;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Hotbar {

    public static void ponerMain(final Player p) {
        removerSimpleInv(p);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                if (p != null && p.isOnline() && (!PlayerControl.isPlaying(p))) {
                    if (p.hasPermission("olimpets.hotbar.ranked")) {
                        p.getInventory().setItem(hotbarRankedSlot, hotbarRanked);
                    }
                    if (p.hasPermission("olimpets.hotbar.unranked")) {
                        p.getInventory().setItem(hotbarUnRankedSlot, hotbarUnRanked);
                    }
                    if (p.hasPermission("olimpets.hotbar.pet")) {
                        p.getInventory().setItem(hotbarPetSlot, hotbarPet);
                    }
                    if (p.hasPermission("olimpets.hotbar.backserver")) {
                        p.getInventory().setItem(hotbarLeaveSlot, hotbarLeave);
                    }
                    if (p.hasPermission("olimpets.hotbar.party")) {
                        p.getInventory().setItem(hotbarPartySlot, hotbarParty);
                    }
                    if (p.hasPermission("olimpets.hotbar.events")) {
                        p.getInventory().setItem(hotbarEventsSlot, hotbarEvents);
                    }
                    if (p.hasPermission("olimpets.hotbar.tournaments")) {
                        p.getInventory().setItem(hotbarTournamentsSlot, hotbarTournaments);
                    }
                    p.setHealth(20);
                    p.setFoodLevel(20);
                }
            }
        }, 20L);
    }

    public static void ponerHotbarParty(final Player p) {
        removerSimpleInv(p);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                if (p != null && p.isOnline() && (!PlayerControl.isPlaying(p))) {
                    if (PlayerControl.isPartyOwner(p)) {
                        p.getInventory().setItem(hotbarPartyPlaySlot, hotbarPartyPlay);
                    }
                    if (p.hasPermission("olimpets.hotbar.pet")) {
                        p.getInventory().setItem(hotbarPetSlot, hotbarPet);
                    }
                    p.getInventory().setItem(hotbarWaitingLeaveSlot, hotbarWaitingLeave);
                }
            }
        }, 20L);
    }

    public static void ponerSalirWaiting(Player p) {
        removerSimpleInv(p);
        p.getInventory().setItem(hotbarWaitingLeaveSlot, hotbarWaitingLeave);
    }

    public static void removerSimpleInv(Player p) {
        p.getInventory().clear();
    }
}
