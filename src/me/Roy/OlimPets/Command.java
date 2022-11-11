package me.Roy.OlimPets;

import java.util.ArrayList;
import java.util.List;
import static me.Roy.OlimPets.Extra.cooldownParty;
import static me.Roy.OlimPets.Extra.partys;
import static me.Roy.OlimPets.Extra.prePartys;
import me.Roy.OlimPets.Games.Antilover.AntiloverCreate;
import static me.Roy.OlimPets.Games.Antilover.AntiloverCreate.antiloverCrearMapa;
import me.Roy.OlimPets.Games.Count.CountCreate;
import static me.Roy.OlimPets.Games.Count.CountCreate.countCrearMapa;
import static me.Roy.OlimPets.Games.Count.CountCreate.countZombieLocation;
import me.Roy.OlimPets.Games.Jump.JumpCreate;
import static me.Roy.OlimPets.Games.Jump.JumpCreate.jumpCrearMapa;
import static me.Roy.OlimPets.Games.Jump.JumpCreate.jumpZombieLocation;
import me.Roy.OlimPets.Games.Run.RunCreate;
import static me.Roy.OlimPets.Games.Run.RunCreate.runCrearMapa;
import static me.Roy.OlimPets.Games.Run.RunCreate.runCrearMapaLoc;
import me.Roy.OlimPets.Inventarios.GuiUnrankedControl;
import static me.Roy.OlimPets.Lang.NOTE_BASS;
import static me.Roy.OlimPets.Lang.SPLASH2;
import static me.Roy.OlimPets.Lang.creatingCanceled;
import static me.Roy.OlimPets.Lang.cspawnpoints;
import static me.Roy.OlimPets.Lang.notCreating;
import static me.Roy.OlimPets.Lang.partyCreateMsg;
import static me.Roy.OlimPets.Lang.partyDontHave;
import static me.Roy.OlimPets.Lang.partyFull;
import static me.Roy.OlimPets.Lang.partyInvited;
import static me.Roy.OlimPets.Lang.partyInvitedClick;
import static me.Roy.OlimPets.Lang.partyMaxPlayers;
import static me.Roy.OlimPets.Lang.partyNoExist;
import static me.Roy.OlimPets.Lang.partyNoInParty;
import static me.Roy.OlimPets.Lang.partyNoInvited;
import static me.Roy.OlimPets.Lang.partyNoOwner;
import static me.Roy.OlimPets.Lang.partyPlayerHasInvited;
import static me.Roy.OlimPets.Lang.partyPlayerInParty;
import static me.Roy.OlimPets.Lang.partySent;
import static me.Roy.OlimPets.Lang.playerAlreadyPlaying;
import static me.Roy.OlimPets.Lang.playerOffline;
import static me.Roy.OlimPets.Lang.spawnLobby;
import static me.Roy.OlimPets.Lang.spawnpoints;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("olim")) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (args[0].equals("leave")) {
                    Extra.enviarServer(p);
                } else if (args[0].equals("setspawn")) {
                    if (PlayerControl.hasPerm(p, "olimpets.setspawn")) {
                        cspawnpoints.set("lobby.w", p.getWorld().getName());
                        cspawnpoints.set("lobby.x", p.getLocation().getX());
                        cspawnpoints.set("lobby.y", p.getLocation().getY());
                        cspawnpoints.set("lobby.z", p.getLocation().getZ());
                        cspawnpoints.set("lobby.yaw", p.getLocation().getYaw());
                        cspawnpoints.set("lobby.pitch", p.getLocation().getPitch());
                        Extra.guardar(spawnpoints, cspawnpoints);
                        spawnLobby = new Location(
                                p.getWorld(),
                                p.getLocation().getX(),
                                p.getLocation().getY(),
                                p.getLocation().getZ(),
                                p.getLocation().getYaw(),
                                p.getLocation().getPitch());
                        p.sendMessage("§aSpawn set!");
                    }
                } else if (args[0].equals("create")) {
                    GuiUnrankedControl.openCreateMap(p);
                } /*else if (args[0].equals("finish")) {
                 if (runSettingFinal == p) {
                 RunCreate.setSpawn(p);
                 } else if (runSettingSpawn == p) {
                 RunCreate.registrar(p);
                 } else if (runCrearMapa.containsKey(p)) {
                 if (runCrearMapa.get(p) >= unrankedMaxPlayers) {
                 RunCreate.setFinal(p);
                 } else {
                 p.sendMessage(needMinSpawns.replaceAll("<number>", "" + unrankedMaxPlayers));
                 }
                 } else {
                 p.sendMessage(notCreating);
                 }
                 }*/ else if (args[0].equals("ready")) {
                    if (runCrearMapa.containsKey(p)) {
                        runCrearMapa.put(p, runCrearMapa.get(p) + 1);
                        RunCreate.nextStep(p);
                    } else if (countCrearMapa.containsKey(p)) {
                        countCrearMapa.put(p, countCrearMapa.get(p) + 1);
                        CountCreate.nextStep(p);
                    } else if (jumpCrearMapa.containsKey(p)) {
                        switch (jumpCrearMapa.get(p)) {
                            case 1:
                                JumpCreate.jumpSetPlayerSpawns(p);
                                break;
                            case 2:
                                JumpCreate.registrar(p);
                                break;
                        }
                    } else if (antiloverCrearMapa.containsKey(p)) {
                        antiloverCrearMapa.put(p, antiloverCrearMapa.get(p) + 1);
                        AntiloverCreate.nextStep(p);
                    } else {
                        p.sendMessage(notCreating);
                    }
                } else if (args[0].equals("cancel")) {
                    if (runCrearMapa.containsKey(p)) {
                        runCrearMapa.remove(p);
                        if (runCrearMapaLoc.containsKey(p)) {
                            runCrearMapaLoc.remove(p);
                        }
                        p.sendMessage(creatingCanceled);
                    } else if (jumpCrearMapa.containsKey(p)) {
                        jumpCrearMapa.remove(p);
                        if (jumpZombieLocation.containsKey(p)) {
                            jumpZombieLocation.remove(p);
                        }
                        p.sendMessage(creatingCanceled);
                    } else if (jumpZombieLocation.containsKey(p)) {
                        jumpZombieLocation.remove(p);
                        if (jumpCrearMapa.containsKey(p)) {
                            jumpCrearMapa.remove(p);
                        }
                        p.sendMessage(creatingCanceled);
                    } else if (countZombieLocation.containsKey(p)) {
                        countZombieLocation.remove(p);
                        if (countCrearMapa.containsKey(p)) {
                            countCrearMapa.remove(p);
                        }
                        p.sendMessage(creatingCanceled);
                    } else if (countCrearMapa.containsKey(p)) {
                        countCrearMapa.remove(p);
                        if (countZombieLocation.containsKey(p)) {
                            countZombieLocation.remove(p);
                        }
                        p.sendMessage(creatingCanceled);
                    } else if (antiloverCrearMapa.containsKey(p)) {
                        AntiloverCreate.sacar(p);
                        p.sendMessage(creatingCanceled);
                    } else {
                        p.sendMessage(notCreating);
                    }
                } else if (args[0].equals("clear")) {
                    if (PlayerControl.hasPerm(p, "olimpet.clear")) {
                        List<Entity> entidades = p.getNearbyEntities(10, 10, 10);
                        for (Entity current : entidades) {
                            if (!(current instanceof Player)) {
                                current.remove();
                            }
                        }
                    }
                }
            }
        } else if (cmd.getName().equalsIgnoreCase("party")) {
            Player p = (Player) sender;
            if (args[0].equalsIgnoreCase("invite")) {
                if (args.length == 2) {
                    if (PlayerControl.hasPerm(p, "olimpets.party.invite")) {
                        if (PlayerControl.isInParty(p)) {
                            if (PlayerControl.isPartyOwner(p)) {
                                if (partys.get(p).getPlayers().size() < partyMaxPlayers) {
                                    Player pp = Bukkit.getPlayer(args[1]);
                                    if (pp != null && pp.isOnline()) {
                                        if (!PlayerControl.isInParty(pp)) {
                                            if (!PlayerControl.isPlaying(pp)) {
                                                if (cooldownParty.containsKey(pp)) {
                                                    long secondsLeft = ((cooldownParty.get(pp) / 1000) + 8) - (System.currentTimeMillis() / 1000);
                                                    if (secondsLeft > 0) {
                                                        p.sendMessage(partyPlayerHasInvited);
                                                        Extra.sonido(p, NOTE_BASS);
                                                        return true;
                                                    }
                                                }
                                                cooldownParty.put(pp, System.currentTimeMillis());

                                                p.sendMessage(partySent.replaceAll("<player>", pp.getName()));
                                                pp.sendMessage(partyInvited.replaceAll("<player>", p.getName()));
                                                Extra.sonido(pp, SPLASH2);
                                                Extra.sonido(p, SPLASH2);
                                                prePartys.put(pp, partys.get(p));
                                                TextComponent l1 = new TextComponent();
                                                l1.setText(partyInvitedClick);
                                                l1.setColor(ChatColor.GREEN);
                                                l1.setBold(true);
                                                List<String> pla = new ArrayList<>();
                                                for (Player o : partys.get(p).getPlayers()) {
                                                    pla.add(o.getName());
                                                }
                                                l1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7" + pla.toString()).create()));
                                                l1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party accept"));
                                                pp.spigot().sendMessage(l1);
                                            } else {
                                                p.sendMessage(playerAlreadyPlaying);
                                            }
                                        } else {
                                            p.sendMessage(partyPlayerInParty);
                                        }
                                    } else {
                                        p.sendMessage(playerOffline);
                                    }
                                } else {
                                    p.sendMessage(partyFull);
                                    Extra.sonido(p, NOTE_BASS);
                                }
                            } else {
                                p.sendMessage(partyNoOwner);
                            }
                        } else {
                            p.sendMessage(partyDontHave);
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("accept")) {
                if (args.length == 1) {
                    if (PlayerControl.hasPerm(p, "olimpets.party.accept")) {
                        if (prePartys.containsKey(p)) {
                            if (partys.containsValue(prePartys.get(p))) {
                                PartyControl.joinParty(p, prePartys.get(p));
                                prePartys.remove(p);
                            } else {
                                prePartys.remove(p);
                                p.sendMessage(partyNoExist);
                            }
                        } else {
                            p.sendMessage(partyNoInvited);
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("kick")) {
                if (args.length == 2) {
                    if (PlayerControl.isInParty(p)) {
                        if (PlayerControl.isPartyOwner(p)) {
                            Player pp = Bukkit.getPlayer(args[1]);
                            if (pp != null && pp.isOnline()) {
                                if (partys.get(p).getPlayers().contains(pp)) {
                                    if (!PlayerControl.isPartyOwner(pp)) {
                                        PartyControl.kickParty(pp);
                                    } else {
                                        p.sendMessage(playerAlreadyPlaying);
                                    }
                                } else {
                                    p.sendMessage(partyNoInParty);
                                }
                            }
                        } else {
                            p.sendMessage(partyNoOwner);
                        }
                    } else {
                        p.sendMessage(partyDontHave);
                    }
                }
            } else {
                for (String msg : partyCreateMsg) {
                    p.sendMessage(msg);
                }
            }
        }
        return false;
    }
}
