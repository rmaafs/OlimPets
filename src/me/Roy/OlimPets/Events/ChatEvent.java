package me.Roy.OlimPets.Events;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.listaEditandoNombre;
import static me.Roy.OlimPets.Extra.pets;
import me.Roy.OlimPets.Inventarios.GuiPet;
import static me.Roy.OlimPets.Lang.LEVEL_UP;
import static me.Roy.OlimPets.Lang.nameChanged;
import static me.Roy.OlimPets.Lang.nameMaxLenght;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener{
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if (listaEditandoNombre.contains(e.getPlayer())){
            Player p = e.getPlayer();
            String msg = Extra.tc(e.getMessage());
            if (msg.length() > nameMaxLenght){
                msg = msg.substring(0, nameMaxLenght);
            }
            pets.get(p).setName(msg);
            listaEditandoNombre.remove(p);
            p.sendMessage(nameChanged.replaceAll("<name>", msg));
            Extra.sonido(p, LEVEL_UP);
            GuiPet.abrirMenu(p);
            e.setCancelled(true);
        }
    }
}
