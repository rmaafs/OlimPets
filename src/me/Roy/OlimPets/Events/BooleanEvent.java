package me.Roy.OlimPets.Events;

import static me.Roy.OlimPets.Lang.noDestroy;
import static me.Roy.OlimPets.Lang.noPlace;
import static me.Roy.OlimPets.LangItems.hotbarEvents;
import static me.Roy.OlimPets.LangItems.hotbarLeave;
import static me.Roy.OlimPets.LangItems.hotbarParty;
import static me.Roy.OlimPets.LangItems.hotbarPartyPlay;
import static me.Roy.OlimPets.LangItems.hotbarPet;
import static me.Roy.OlimPets.LangItems.hotbarRanked;
import static me.Roy.OlimPets.LangItems.hotbarTournaments;
import static me.Roy.OlimPets.LangItems.hotbarUnRanked;
import static me.Roy.OlimPets.LangItems.hotbarWaitingLeave;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

public class BooleanEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (PlayerControl.isPlaying(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(noDestroy);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (PlayerControl.isPlaying(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(noPlace);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        ItemStack i = e.getItemDrop().getItemStack();
        if (hotbarRanked.isSimilar(i)
                || hotbarUnRanked.isSimilar(i)
                || hotbarPet.isSimilar(i)
                || hotbarLeave.isSimilar(i)
                || hotbarWaitingLeave.isSimilar(i)
                || hotbarParty.isSimilar(i)
                || hotbarPartyPlay.isSimilar(i)
                || hotbarTournaments.isSimilar(i)
                || hotbarEvents.isSimilar(i)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void WeatherChangeEvent(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }
}
