package me.Roy.OlimPets.Events.Inventarios;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.partys;
import me.Roy.OlimPets.Games.Antilover.AntiloverControl;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverEsperando;
import me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedEsperando;
import me.Roy.OlimPets.Games.Count.CountControl;
import static me.Roy.OlimPets.Games.Count.CountControl.countEsperando;
import me.Roy.OlimPets.Games.Count.CountControlRanked;
import static me.Roy.OlimPets.Games.Count.CountControlRanked.countRankedEsperando;
import me.Roy.OlimPets.Games.Jump.JumpControl;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpEsperando;
import me.Roy.OlimPets.Games.Jump.JumpControlRanked;
import static me.Roy.OlimPets.Games.Jump.JumpControlRanked.jumpRankedEsperando;
import me.Roy.OlimPets.Games.Run.RunControl;
import static me.Roy.OlimPets.Games.Run.RunControl.runEsperando;
import me.Roy.OlimPets.Games.Run.RunControlRanked;
import static me.Roy.OlimPets.Games.Run.RunControlRanked.runRankedEsperando;
import me.Roy.OlimPets.Inventarios.GuiPartyPlay;
import me.Roy.OlimPets.Inventarios.GuiPet;
import me.Roy.OlimPets.Inventarios.GuiRankedControl;
import me.Roy.OlimPets.Inventarios.GuiUnrankedControl;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.LangItems.hotbarLeave;
import static me.Roy.OlimPets.LangItems.hotbarParty;
import static me.Roy.OlimPets.LangItems.hotbarPartyPlay;
import static me.Roy.OlimPets.LangItems.hotbarPet;
import static me.Roy.OlimPets.LangItems.hotbarRanked;
import static me.Roy.OlimPets.LangItems.hotbarUnRanked;
import static me.Roy.OlimPets.LangItems.hotbarWaitingLeave;
import me.Roy.OlimPets.PartyControl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HotbarEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        ItemStack itemInHand = e.getItem();
        Player p = e.getPlayer();
        if (hotbarRanked.isSimilar(itemInHand)) {
            e.setCancelled(true);
            GuiRankedControl.openUnranked(p);
        } else if (hotbarUnRanked.isSimilar(itemInHand)) {
            e.setCancelled(true);
            GuiUnrankedControl.openUnranked(p);
        } else if (hotbarPet.isSimilar(itemInHand)) {
            e.setCancelled(true);
            GuiPet.abrirMenu(p);
        } else if (hotbarLeave.isSimilar(itemInHand)) {
            e.setCancelled(true);
            Extra.enviarServer(p);
        } else if (hotbarWaitingLeave.isSimilar(itemInHand)) {
            e.setCancelled(true);
            sacarWaiting(p);
        } else if (hotbarParty.isSimilar(itemInHand)) {
            e.setCancelled(true);
            PartyControl.createParty(p);
        } else if (hotbarPartyPlay.isSimilar(itemInHand)) {
            e.setCancelled(true);
            GuiPartyPlay.open(p);
        }
    }
    
    public static void sacarWaiting(Player p){
        if (runEsperando.contains(p)){
            runEsperando.remove(p);
            RunControl.salirEspera(p);
        }
        if (runRankedEsperando.contains(p)){
            runRankedEsperando.remove(p);
            RunControlRanked.salirEspera(p);
        }
        if (jumpEsperando.contains(p)){
            jumpEsperando.remove(p);
            JumpControl.salirEspera(p);
        }
        if (jumpRankedEsperando.contains(p)){
            jumpRankedEsperando.remove(p);
            JumpControlRanked.salirEspera(p);
        }
        if (countEsperando.contains(p)){
            countEsperando.remove(p);
            CountControl.salirEspera(p);
        }
        if (countRankedEsperando.contains(p)){
            countRankedEsperando.remove(p);
            CountControlRanked.salirEspera(p);
        }
        if (antiloverEsperando.contains(p)){
            antiloverEsperando.remove(p);
            AntiloverControl.salirEspera(p);
        }
        if (antiloverRankedEsperando.contains(p)){
            antiloverRankedEsperando.remove(p);
            AntiloverControlRanked.salirEspera(p);
        }
        if (partys.containsKey(p)){
            PartyControl.salirParty(p, true);
        }
        Hotbar.ponerMain(p);
    }
}
