package me.Roy.OlimPets;

import static me.Roy.OlimPets.Extra.partys;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverEsperando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverPartyJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedEsperando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedJugando;
import static me.Roy.OlimPets.Games.Count.CountControl.countEsperando;
import static me.Roy.OlimPets.Games.Count.CountControl.countJugando;
import static me.Roy.OlimPets.Games.Count.CountControl.countPartyJugando;
import static me.Roy.OlimPets.Games.Count.CountControlRanked.countRankedEsperando;
import static me.Roy.OlimPets.Games.Count.CountControlRanked.countRankedJugando;
import static me.Roy.OlimPets.Games.Count.CountCreate.countCrearMapa;
import static me.Roy.OlimPets.Games.Count.CountCreate.countZombieLocation;
import static me.Roy.OlimPets.Games.Count.CountCreate.countZombiePlayer;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpEsperando;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpJugando;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpPartyJugando;
import static me.Roy.OlimPets.Games.Jump.JumpControlRanked.jumpRankedEsperando;
import static me.Roy.OlimPets.Games.Jump.JumpControlRanked.jumpRankedJugando;
import static me.Roy.OlimPets.Games.Jump.JumpCreate.jumpCrearMapa;
import static me.Roy.OlimPets.Games.Jump.JumpCreate.jumpZombieLocation;
import static me.Roy.OlimPets.Games.Run.RunControl.runEsperando;
import static me.Roy.OlimPets.Games.Run.RunControl.runJugando;
import static me.Roy.OlimPets.Games.Run.RunControl.runPartyJugando;
import static me.Roy.OlimPets.Games.Run.RunControlRanked.runRankedEsperando;
import static me.Roy.OlimPets.Games.Run.RunControlRanked.runRankedJugando;
import static me.Roy.OlimPets.Games.Run.RunCreate.runCrearMapa;
import static me.Roy.OlimPets.Lang.noPermission;
import org.bukkit.entity.Player;

public class PlayerControl {
    
    public static boolean isPlaying(Player p){
        if (runJugando.containsKey(p) ||
                runRankedJugando.containsKey(p)){
            return true;
        } else if (runEsperando.contains(p) ||
                runRankedEsperando.contains(p)){
            return true;
        } else if (runPartyJugando.containsKey(p) ||
                runPartyJugando.containsKey(p)){
            return true;
        } else if (runCrearMapa.containsKey(p)){
            return true;
        } else if (jumpCrearMapa.containsKey(p) ||
                jumpZombieLocation.containsKey(p)){
            return true;
        } else if (jumpJugando.containsKey(p) ||
                jumpRankedJugando.containsKey(p)){
            return true;
        } else if (jumpEsperando.contains(p) ||
                jumpRankedEsperando.contains(p)){
            return true;
        } else if (jumpPartyJugando.containsKey(p) ||
                jumpPartyJugando.containsKey(p)){
            return true;
        } else if (countCrearMapa.containsKey(p) ||
                countZombieLocation.containsKey(p) ||
                countZombiePlayer.containsKey(p)){
            return true;
        } else if (countJugando.containsKey(p) ||
                countRankedJugando.containsKey(p)){
            return true;
        } else if (countEsperando.contains(p) ||
                countRankedEsperando.contains(p)){
            return true;
        } else if (countPartyJugando.containsKey(p) ||
                countPartyJugando.containsKey(p)){
            return true;
        } else if (antiloverJugando.containsKey(p) ||
                antiloverRankedJugando.containsKey(p)){
            return true;
        } else if (antiloverEsperando.contains(p) ||
                antiloverRankedEsperando.contains(p)){
            return true;
        } else if (antiloverPartyJugando.containsKey(p)){
            return true;
        }
        return false;
    }
    
    public static boolean isInParty(Player p){
        if (partys.containsKey(p)){
            return true;
        }
        return false;
    }
    
    public static boolean isPartyOwner(Player p){
        if (partys.get(p).getOwner() == p){
            return true;
        }
        return false;
    }
    
    public static boolean hasPerm(Player p, String perm){
        if (p.hasPermission(perm)){
            return true;
        } else {
            p.sendMessage(noPermission);
            return false;
        }
    }
}
