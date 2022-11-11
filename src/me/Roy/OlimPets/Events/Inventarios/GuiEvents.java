package me.Roy.OlimPets.Events.Inventarios;

import me.Roy.OlimPets.Extra;
import static me.Roy.OlimPets.Extra.listaEditandoNombre;
import static me.Roy.OlimPets.Extra.partys;
import static me.Roy.OlimPets.Extra.pets;
import me.Roy.OlimPets.Games.Antilover.AntiloverControl;
import me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked;
import me.Roy.OlimPets.Games.Antilover.AntiloverCreate;
import me.Roy.OlimPets.Games.Count.CountControl;
import me.Roy.OlimPets.Games.Count.CountControlRanked;
import me.Roy.OlimPets.Games.Count.CountCreate;
import me.Roy.OlimPets.Games.Jump.JumpControl;
import me.Roy.OlimPets.Games.Jump.JumpControlRanked;
import me.Roy.OlimPets.Games.Jump.JumpCreate;
import me.Roy.OlimPets.Games.Run.RunControl;
import me.Roy.OlimPets.Games.Run.RunControlRanked;
import me.Roy.OlimPets.Games.Run.RunCreate;
import me.Roy.OlimPets.Inventarios.GuiPet;
import static me.Roy.OlimPets.Lang.NOTE_PLING;
import static me.Roy.OlimPets.Lang.ORB_PICKUP;
import static me.Roy.OlimPets.Lang.guiArmorBootsTitle;
import static me.Roy.OlimPets.Lang.guiArmorChestplateTitle;
import static me.Roy.OlimPets.Lang.guiArmorHandItemsTitle;
import static me.Roy.OlimPets.Lang.guiArmorHatTitle;
import static me.Roy.OlimPets.Lang.guiArmorHelmetTitle;
import static me.Roy.OlimPets.Lang.guiArmorLeggingsTitle;
import static me.Roy.OlimPets.Lang.guiPartyPlayTitle;
import static me.Roy.OlimPets.Lang.guiPetArmorBootsSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorChestplateSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorHandSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorHelmetSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorLeggingsSlot;
import static me.Roy.OlimPets.Lang.guiPetArmorTitle;
import static me.Roy.OlimPets.Lang.guiPetTitle;
import static me.Roy.OlimPets.Lang.guiRankedTitle;
import static me.Roy.OlimPets.Lang.guiUnrankedTitle;
import static me.Roy.OlimPets.Lang.noPermission;
import static me.Roy.OlimPets.Lang.partyMin2Players;
import static me.Roy.OlimPets.Lang.petHided;
import static me.Roy.OlimPets.Lang.petSummoned;
import static me.Roy.OlimPets.Lang.writeNameChanged;
import static me.Roy.OlimPets.LangItems.guiGamesAntiloverName;
import static me.Roy.OlimPets.LangItems.guiGamesCountName;
import static me.Roy.OlimPets.LangItems.guiGamesJumpName;
import static me.Roy.OlimPets.LangItems.guiPetColorArmorHat;
import static me.Roy.OlimPets.LangItems.guiPetColorArmorLeave;
import static me.Roy.OlimPets.LangItems.guiPetMenuArmorName;
import static me.Roy.OlimPets.LangItems.guiPetMenuNameName;
import static me.Roy.OlimPets.LangItems.guiPetMenuSummonDisabledName;
import static me.Roy.OlimPets.LangItems.guiPetMenuSummonEnabledName;
import static me.Roy.OlimPets.LangItems.guiGamesRunName;
import static me.Roy.OlimPets.LangItems.hotbarEvents;
import static me.Roy.OlimPets.LangItems.hotbarEventsSlot;
import static me.Roy.OlimPets.LangItems.hotbarLeave;
import static me.Roy.OlimPets.LangItems.hotbarLeaveSlot;
import static me.Roy.OlimPets.LangItems.hotbarParty;
import static me.Roy.OlimPets.LangItems.hotbarPartySlot;
import static me.Roy.OlimPets.LangItems.hotbarPet;
import static me.Roy.OlimPets.LangItems.hotbarPetSlot;
import static me.Roy.OlimPets.LangItems.hotbarRanked;
import static me.Roy.OlimPets.LangItems.hotbarRankedSlot;
import static me.Roy.OlimPets.LangItems.hotbarTournaments;
import static me.Roy.OlimPets.LangItems.hotbarTournamentsSlot;
import static me.Roy.OlimPets.LangItems.hotbarUnRanked;
import static me.Roy.OlimPets.LangItems.hotbarUnRankedSlot;
import me.Roy.OlimPets.PlayerControl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        InventoryAction a = e.getAction();
        ItemStack i = e.getCurrentItem();
        if (i == null || i.getItemMeta() == null) {
            if (inv.getName().equals(guiPetTitle)
                    || inv.getName().equals(guiPetArmorTitle)
                    || inv.getName().equals(guiUnrankedTitle)
                    || inv.getName().equals("Create map")
                    || inv.getName().equals(guiPartyPlayTitle)) {
                e.setCancelled(true);
            } else if ((p.getInventory().getItem(hotbarRankedSlot) != null && p.getInventory().getItem(hotbarRankedSlot).equals(hotbarRanked))
                    || (p.getInventory().getItem(hotbarUnRankedSlot) != null && p.getInventory().getItem(hotbarUnRankedSlot).equals(hotbarUnRanked))
                    || (p.getInventory().getItem(hotbarPetSlot) != null && p.getInventory().getItem(hotbarPetSlot).equals(hotbarPet))
                    || (p.getInventory().getItem(hotbarLeaveSlot) != null && p.getInventory().getItem(hotbarLeaveSlot).equals(hotbarLeave))
                    || (p.getInventory().getItem(hotbarPartySlot) != null && p.getInventory().getItem(hotbarPartySlot).equals(hotbarParty))
                    || (p.getInventory().getItem(hotbarEventsSlot) != null && p.getInventory().getItem(hotbarEventsSlot).equals(hotbarEvents))
                    || (p.getInventory().getItem(hotbarTournamentsSlot) != null && p.getInventory().getItem(hotbarTournamentsSlot).equals(hotbarTournaments))) {
                e.setCancelled(true);
            }
            return;
        }
        if (a.equals(InventoryAction.HOTBAR_SWAP)) {
            if ((p.getInventory().getItem(hotbarRankedSlot) != null && p.getInventory().getItem(hotbarRankedSlot).equals(hotbarRanked))
                    || (p.getInventory().getItem(hotbarUnRankedSlot) != null && p.getInventory().getItem(hotbarUnRankedSlot).equals(hotbarUnRanked))
                    || (p.getInventory().getItem(hotbarPetSlot) != null && p.getInventory().getItem(hotbarPetSlot).equals(hotbarPet))
                    || (p.getInventory().getItem(hotbarLeaveSlot) != null && p.getInventory().getItem(hotbarLeaveSlot).equals(hotbarLeave))
                    || (p.getInventory().getItem(hotbarPartySlot) != null && p.getInventory().getItem(hotbarPartySlot).equals(hotbarParty))
                    || (p.getInventory().getItem(hotbarEventsSlot) != null && p.getInventory().getItem(hotbarEventsSlot).equals(hotbarEvents))
                    || (p.getInventory().getItem(hotbarTournamentsSlot) != null && p.getInventory().getItem(hotbarTournamentsSlot).equals(hotbarTournaments))) {
                e.setCancelled(true);
            }
        }
        if (i.equals(hotbarRanked) || i.equals(hotbarUnRanked) || i.equals(hotbarPet) || i.equals(hotbarLeave) || i.equals(hotbarParty) || i.equals(hotbarEvents) || i.equals(hotbarTournaments)) {
            e.setCancelled(true);
        }

        if (inv.getName().equals(guiPetTitle)) {
            String n = i.getItemMeta().getDisplayName();
            if (n.equals(guiPetMenuNameName)) {
                if (p.hasPermission("olimpets.gui.name")) {
                    if (!listaEditandoNombre.contains(p)) {
                        listaEditandoNombre.add(p);
                    }
                    p.closeInventory();
                    p.sendMessage(writeNameChanged);
                    Extra.sonido(p, NOTE_PLING);
                } else {
                    p.sendMessage(noPermission);
                }
            } else if (n.equals(guiPetMenuArmorName)) {
                if (p.hasPermission("olimpets.gui.armor")) {
                    if (pets.get(p).getSummon()) {
                        pets.get(p).setSummon(false);
                        pets.get(p).kill();
                        p.sendMessage(petHided.replaceAll("<petname>", pets.get(p).getName()));
                        Extra.sonido(p, ORB_PICKUP);
                    }
                    GuiPet.abrirArmor(p);
                } else {
                    p.sendMessage(noPermission);
                }
            } else if (n.equals(guiPetMenuSummonEnabledName)) {
                if (p.hasPermission("olimpets.gui.summon")) {
                    p.closeInventory();
                    pets.get(p).setSummon(true);
                    pets.get(p).summon(true, 0);
                    p.sendMessage(petSummoned.replaceAll("<petname>", pets.get(p).getName()));
                    Extra.sonido(p, ORB_PICKUP);
                } else {
                    p.sendMessage(noPermission);
                }
            } else if (n.equals(guiPetMenuSummonDisabledName)) {
                if (p.hasPermission("olimpets.gui.summon")) {
                    p.closeInventory();
                    pets.get(p).setSummon(false);
                    pets.get(p).kill();
                    p.sendMessage(petHided.replaceAll("<petname>", pets.get(p).getName()));
                    Extra.sonido(p, ORB_PICKUP);
                } else {
                    p.sendMessage(noPermission);
                }
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiPetArmorTitle)) {
            if (e.getSlot() == guiPetArmorHelmetSlot) {
                GuiPet.abrirColorArmor(p, "HELMET");
            } else if (e.getSlot() == guiPetArmorChestplateSlot) {
                GuiPet.abrirColorArmor(p, "CHESTPLATE");
            } else if (e.getSlot() == guiPetArmorLeggingsSlot) {
                GuiPet.abrirColorArmor(p, "LEGGINGS");
            } else if (e.getSlot() == guiPetArmorBootsSlot) {
                GuiPet.abrirColorArmor(p, "BOOTS");
            } else if (e.getSlot() == guiPetArmorHandSlot) {
                GuiPet.abrirHandItems(p);
            } else if (i.isSimilar(guiPetColorArmorLeave)) {
                GuiPet.abrirMenu(p);
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiArmorHelmetTitle)) {
            if (i.isSimilar(guiPetColorArmorHat)) {
                GuiPet.abrirHats(p);
            } else if (i.isSimilar(guiPetColorArmorLeave)) {
                GuiPet.abrirArmor(p);
            } else {
                pets.get(p).setHelmet(i);
                GuiPet.abrirArmor(p);
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiArmorChestplateTitle)) {
            if (i.isSimilar(guiPetColorArmorLeave)) {
                GuiPet.abrirArmor(p);
            } else {
                pets.get(p).setChestplate(i);
                GuiPet.abrirArmor(p);
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiArmorLeggingsTitle)) {
            if (i.isSimilar(guiPetColorArmorLeave)) {
                GuiPet.abrirArmor(p);
            } else {
                pets.get(p).setLeggings(i);
                GuiPet.abrirArmor(p);
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiArmorBootsTitle)) {
            if (i.isSimilar(guiPetColorArmorLeave)) {
                GuiPet.abrirArmor(p);
            } else {
                pets.get(p).setBoots(i);
                GuiPet.abrirArmor(p);
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiArmorHatTitle)) {
            if (i.isSimilar(guiPetColorArmorLeave)) {
                GuiPet.abrirColorArmor(p, "HELMET");
            } else {
                pets.get(p).setHelmet(i);
                GuiPet.abrirArmor(p);
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiArmorHandItemsTitle)) {
            if (i.isSimilar(guiPetColorArmorLeave)) {
                GuiPet.abrirArmor(p);
            } else {
                pets.get(p).setMano(i);
                GuiPet.abrirArmor(p);
            }
            e.setCancelled(true);
        } else if (inv.getName().equals("Create Map")) {
            if (i.getItemMeta().getDisplayName().equals(guiGamesRunName)) {
                RunCreate.crearMapa(p);
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesJumpName)) {
                JumpCreate.crearMapa(p);
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesCountName)) {
                CountCreate.crearMapa(p);
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesAntiloverName)) {
                AntiloverCreate.crearMapa(p);
            }
            p.closeInventory();
            e.setCancelled(true);
        } else if (inv.getName().equals(guiRankedTitle)) {
            if (i.getItemMeta().getDisplayName().equals(guiGamesRunName)) {
                if (p.hasPermission("olimpets.ranked.run")) {
                    RunControlRanked.ponerEspera(p);
                } else {
                    p.sendMessage(noPermission);
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesJumpName)) {
                if (p.hasPermission("olimpets.ranked.jump")) {
                    JumpControlRanked.ponerEspera(p);
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesCountName)) {
                if (p.hasPermission("olimpets.ranked.count")) {
                    CountControlRanked.ponerEspera(p);
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesAntiloverName)) {
                if (p.hasPermission("olimpets.ranked.antilover")) {
                    AntiloverControlRanked.ponerEspera(p);
                }
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiUnrankedTitle)) {
            if (i.getItemMeta().getDisplayName().equals(guiGamesRunName)) {
                if (PlayerControl.hasPerm(p, "olimpets.unranked.run")) {
                    RunControl.ponerEspera(p);
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesJumpName)) {
                if (PlayerControl.hasPerm(p, "olimpets.unranked.jump")) {
                    JumpControl.ponerEspera(p);
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesCountName)) {
                if (PlayerControl.hasPerm(p, "olimpets.unranked.count")) {
                    CountControl.ponerEspera(p);
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesAntiloverName)) {
                if (PlayerControl.hasPerm(p, "olimpets.unranked.antilover")) {
                    AntiloverControl.ponerEspera(p);
                }
            }
            e.setCancelled(true);
        } else if (inv.getName().equals(guiPartyPlayTitle)) {
            if (i.getItemMeta().getDisplayName().equals(guiGamesRunName)) {
                if (PlayerControl.hasPerm(p, "olimpets.party.play.run")) {
                    if (partys.get(p).getPlayers().size() > 1) {
                        RunControl.empezarPartyDirecto(partys.get(p).getPlayers());
                    } else {
                        p.sendMessage(partyMin2Players);
                    }
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesJumpName)) {
                if (PlayerControl.hasPerm(p, "olimpets.party.play.jump")) {
                    if (partys.get(p).getPlayers().size() > 1) {
                        JumpControl.empezarPartyDirecto(partys.get(p).getPlayers());
                    } else {
                        p.sendMessage(partyMin2Players);
                    }
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesCountName)) {
                if (PlayerControl.hasPerm(p, "olimpets.party.play.count")) {
                    if (partys.get(p).getPlayers().size() > 1) {
                        CountControl.empezarPartyDirecto(partys.get(p).getPlayers());
                    } else {
                        p.sendMessage(partyMin2Players);
                    }
                }
            } else if (i.getItemMeta().getDisplayName().equals(guiGamesAntiloverName)) {
                if (PlayerControl.hasPerm(p, "olimpets.party.play.antilover")) {
                    if (partys.get(p).getPlayers().size() > 1) {
                        AntiloverControl.empezarPartyDirecto(partys.get(p).getPlayers());
                    } else {
                        p.sendMessage(partyMin2Players);
                    }
                }
            }
            e.setCancelled(true);
        }
    }
}
