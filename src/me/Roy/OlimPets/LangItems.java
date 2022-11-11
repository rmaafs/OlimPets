package me.Roy.OlimPets;

import java.util.List;
import static me.Roy.OlimPets.Lang.cconfig;
import static me.Roy.OlimPets.Lang.clang;
import org.bukkit.inventory.ItemStack;

public class LangItems {

    public static boolean guiGamesRunUse, guiGamesJumpUse, guiGamesCountUse, guiGamesAntiloverUse;
    
    public static String guiPetMenuNameName, guiPetMenuSummonEnabledName, guiPetMenuSummonDisabledName, guiPetMenuArmorName, guiPetColorArmorHatName, guiPetColorArmorLeaveName;
    public static List<String> guiPetMenuNameLore, guiPetMenuSummonEnabledLore, guiPetMenuSummonDisabledLore, guiPetMenuArmorLore, guiPetColorArmorHatLore, guiPetColorArmorLeaveLore;

    public static String guiGamesRunName, guiGamesJumpName, guiGamesCountName,
            guiGamesAntiloverName;
    public static List<String> guiGamesRunLore, guiGamesJumpLore, guiGamesCountLore,
            guiGamesAntiloverLore;
    
    public static int guiPetMenuNameId, guiPetMenuSummonEnabledId, guiPetMenuSummonDisabledId, guiPetMenuArmorId;
    public static int guiPetMenuNameDV, guiPetMenuSummonEnabledDV, guiPetMenuSummonDisabledDV, guiPetMenuArmorDV;
    public static int guiPetMenuNameSlot, guiPetMenuSummonSlot, guiPetMenuArmorSlot;
    public static int guiPetColorArmorHatSlot, guiPetColorArmorHatId, guiPetColorArmorHatDV;
    public static int guiPetColorArmorLeaveSlot, guiPetColorArmorLeaveId, guiPetColorArmorLeaveDV;

    public static int guiGamesRunId, guiGamesRunDV, guiGamesRunSlot;
    public static int guiGamesJumpId, guiGamesJumpDV, guiGamesJumpSlot;
    public static int guiGamesCountId, guiGamesCountDV, guiGamesCountSlot;
    public static int guiGamesAntiloverId, guiGamesAntiloverDV, guiGamesAntiloverSlot;
    
    public static void setLang() {
        guiPetMenuNameName = Extra.tc(clang.getString("gui.petmenu.items.name.name"));
        guiPetMenuNameLore = Extra.tCC(clang.getStringList("gui.petmenu.items.name.lore"));
        guiPetMenuNameId = cconfig.getInt("gui.petmenu.name.id");
        guiPetMenuNameDV = cconfig.getInt("gui.petmenu.name.data-value");
        guiPetMenuNameSlot = cconfig.getInt("gui.petmenu.name.slot");

        guiPetMenuSummonEnabledName = Extra.tc(clang.getString("gui.petmenu.items.summon.enabled.name"));
        guiPetMenuSummonEnabledLore = Extra.tCC(clang.getStringList("gui.petmenu.items.summon.enabled.lore"));
        guiPetMenuSummonDisabledName = Extra.tc(clang.getString("gui.petmenu.items.summon.disabled.name"));
        guiPetMenuSummonDisabledLore = Extra.tCC(clang.getStringList("gui.petmenu.items.summon.disabled.lore"));
        guiPetMenuSummonEnabledId = cconfig.getInt("gui.petmenu.summon.enabled.id");
        guiPetMenuSummonEnabledDV = cconfig.getInt("gui.petmenu.summon.enabled.data-value");
        guiPetMenuSummonDisabledId = cconfig.getInt("gui.petmenu.summon.disabled.id");
        guiPetMenuSummonDisabledDV = cconfig.getInt("gui.petmenu.summon.disabled.data-value");
        guiPetMenuSummonSlot = cconfig.getInt("gui.petmenu.summon.slot");

        guiPetMenuArmorName = Extra.tc(clang.getString("gui.petmenu.items.armor.name"));
        guiPetMenuArmorLore = Extra.tCC(clang.getStringList("gui.petmenu.items.armor.lore"));
        guiPetMenuArmorId = cconfig.getInt("gui.petmenu.armor.id");
        guiPetMenuArmorDV = cconfig.getInt("gui.petmenu.armor.data-value");
        guiPetMenuArmorSlot = cconfig.getInt("gui.petmenu.armor.slot");
        
        guiPetColorArmorHatName = Extra.tc(clang.getString("gui.petarmorcolor.items.hat.name"));
        guiPetColorArmorHatLore = Extra.tCC(clang.getStringList("gui.petarmorcolor.items.hat.lore"));
        guiPetColorArmorHatId = cconfig.getInt("gui.petarmorcolor.hat.id");
        guiPetColorArmorHatDV = cconfig.getInt("gui.petarmorcolor.hat.data-value");
        guiPetColorArmorHatSlot = cconfig.getInt("gui.petarmorcolor.hat.slot");
        
        guiPetColorArmorLeaveName = Extra.tc(clang.getString("gui.petarmorcolor.items.leave.name"));
        guiPetColorArmorLeaveLore = Extra.tCC(clang.getStringList("gui.petarmorcolor.items.leave.lore"));
        guiPetColorArmorLeaveId = cconfig.getInt("gui.petarmorcolor.leave.id");
        guiPetColorArmorLeaveDV = cconfig.getInt("gui.petarmorcolor.leave.data-value");
        guiPetColorArmorLeaveSlot = cconfig.getInt("gui.petarmorcolor.leave.slot");
    
        //-------- UnRanked menú
        
        guiGamesRunName = Extra.tc(clang.getString("gui.games.items.running.name"));
        guiGamesRunLore = Extra.tCC(clang.getStringList("gui.games.items.running.lore"));
        guiGamesRunId = cconfig.getInt("gui.games.running.id");
        guiGamesRunDV = cconfig.getInt("gui.games.running.data-value");
        guiGamesRunSlot = cconfig.getInt("gui.games.running.slot");
        guiGamesRunUse = cconfig.getBoolean("gui.games.running.use");
        
        guiGamesJumpName = Extra.tc(clang.getString("gui.games.items.jump.name"));
        guiGamesJumpLore = Extra.tCC(clang.getStringList("gui.games.items.jump.lore"));
        guiGamesJumpId = cconfig.getInt("gui.games.jump.id");
        guiGamesJumpDV = cconfig.getInt("gui.games.jump.data-value");
        guiGamesJumpSlot = cconfig.getInt("gui.games.jump.slot");
        guiGamesJumpUse = cconfig.getBoolean("gui.games.jump.use");
        
        guiGamesCountName = Extra.tc(clang.getString("gui.games.items.count.name"));
        guiGamesCountLore = Extra.tCC(clang.getStringList("gui.games.items.count.lore"));
        guiGamesCountId = cconfig.getInt("gui.games.count.id");
        guiGamesCountDV = cconfig.getInt("gui.games.count.data-value");
        guiGamesCountSlot = cconfig.getInt("gui.games.count.slot");
        guiGamesCountUse = cconfig.getBoolean("gui.games.count.use");
        
        guiGamesAntiloverName = Extra.tc(clang.getString("gui.games.items.antilover.name"));
        guiGamesAntiloverLore = Extra.tCC(clang.getStringList("gui.games.items.antilover.lore"));
        guiGamesAntiloverId = cconfig.getInt("gui.games.antilover.id");
        guiGamesAntiloverDV = cconfig.getInt("gui.games.antilover.data-value");
        guiGamesAntiloverSlot = cconfig.getInt("gui.games.antilover.slot");
        guiGamesAntiloverUse = cconfig.getBoolean("gui.games.antilover.use");
    }

    public static ItemStack guiPetMenuName, guiPetMenuSummonEnabled, guiPetMenuSummonDisabled, guiPetMenuArmor;
    public static ItemStack guiPetColorArmorHat, guiPetColorArmorLeave;
    
    public static ItemStack guiGamesRun, guiGamesJump, guiGamesCount, guiGamesAntilover;
    
    public static void setItems() {
        setLang();
        setHotbar();

        guiPetMenuName = Extra.crearId(guiPetMenuNameId, guiPetMenuNameDV, guiPetMenuNameName, guiPetMenuNameLore, 1);
        guiPetMenuSummonEnabled = Extra.crearId(guiPetMenuSummonEnabledId, guiPetMenuSummonEnabledDV, guiPetMenuSummonEnabledName, guiPetMenuSummonEnabledLore, 1);
        guiPetMenuSummonDisabled = Extra.crearId(guiPetMenuSummonDisabledId, guiPetMenuSummonDisabledDV, guiPetMenuSummonDisabledName, guiPetMenuSummonDisabledLore, 1);
        guiPetMenuArmor = Extra.crearId(guiPetMenuArmorId, guiPetMenuArmorDV, guiPetMenuArmorName, guiPetMenuArmorLore, 1);
    
        guiPetColorArmorHat = Extra.crearId(guiPetColorArmorHatId, guiPetColorArmorHatDV, guiPetColorArmorHatName, guiPetColorArmorHatLore, 1);
        guiPetColorArmorLeave = Extra.crearId(guiPetColorArmorLeaveId, guiPetColorArmorLeaveDV, guiPetColorArmorLeaveName, guiPetColorArmorLeaveLore, 1);
        
        guiGamesRun = Extra.crearId(guiGamesRunId, guiGamesRunDV, guiGamesRunName, guiGamesRunLore, 1);
        guiGamesJump = Extra.crearId(guiGamesJumpId, guiGamesJumpDV, guiGamesJumpName, guiGamesJumpLore, 1);
        guiGamesCount = Extra.crearId(guiGamesCountId, guiGamesCountDV, guiGamesCountName, guiGamesCountLore, 1);
        guiGamesAntilover = Extra.crearId(guiGamesAntiloverId, guiGamesAntiloverDV, guiGamesAntiloverName, guiGamesAntiloverLore, 1);
    }
    
    

    public static ItemStack hotbarRanked, hotbarUnRanked, hotbarPet, hotbarLeave, hotbarWaitingLeave, hotbarParty, hotbarEvents, hotbarTournaments,
            hotbarPartyPlay;

    public static String hotbarRankedName, hotbarUnRankedName, hotbarPetName, hotbarLeaveName, hotbarWaitingLeaveName,
            hotbarPartyName, hotbarEventsName, hotbarTournamentsName, hotbarPartyPlayName;

    public static List<String> hotbarRankedLore, hotbarUnRankedLore, hotbarPetLore, hotbarLeaveLore, hotbarWaitingLeaveLore,
            hotbarPartyLore, hotbarEventsLore, hotbarTournamentsLore, hotbarPartyPlayLore;

    public static int hotbarRankedId, hotbarUnRankedId, hotbarPetId, hotbarLeaveId, hotbarWaitingLeaveId, hotbarPartyId, hotbarEventsId, hotbarTournamentsId,
            hotbarPartyPlayId;
    public static int hotbarRankedDV, hotbarUnRankedDV, hotbarPetDV, hotbarLeaveDV, hotbarWaitingLeaveDV, hotbarPartyDV, hotbarEventsDV, hotbarTournamentsDV,
            hotbarPartyPlayDV;
    public static int hotbarRankedSlot, hotbarUnRankedSlot, hotbarPetSlot, hotbarLeaveSlot, hotbarWaitingLeaveSlot, hotbarPartySlot, hotbarEventsSlot, hotbarTournamentsSlot,
            hotbarPartyPlaySlot;

    public static void setHotbarLang(){
        hotbarRankedName = Extra.tc(clang.getString("hotbar.ranked.name"));
        hotbarRankedLore = Extra.tCC(clang.getStringList("hotbar.ranked.lore"));
        hotbarRankedId = cconfig.getInt("hotbar.ranked.id");
        hotbarRankedDV = cconfig.getInt("hotbar.ranked.data-value");
        hotbarRankedSlot = cconfig.getInt("hotbar.ranked.slot");

        hotbarUnRankedName = Extra.tc(clang.getString("hotbar.unranked.name"));
        hotbarUnRankedLore = Extra.tCC(clang.getStringList("hotbar.unranked.lore"));
        hotbarUnRankedId = cconfig.getInt("hotbar.unranked.id");
        hotbarUnRankedDV = cconfig.getInt("hotbar.unranked.data-value");
        hotbarUnRankedSlot = cconfig.getInt("hotbar.unranked.slot");

        hotbarPetName = Extra.tc(clang.getString("hotbar.pet.name"));
        hotbarPetLore = Extra.tCC(clang.getStringList("hotbar.pet.lore"));
        hotbarPetId = cconfig.getInt("hotbar.pet.id");
        hotbarPetDV = cconfig.getInt("hotbar.pet.data-value");
        hotbarPetSlot = cconfig.getInt("hotbar.pet.slot");

        hotbarLeaveName = Extra.tc(clang.getString("hotbar.leave.name"));
        hotbarLeaveLore = Extra.tCC(clang.getStringList("hotbar.leave.lore"));
        hotbarLeaveId = cconfig.getInt("hotbar.leave.id");
        hotbarLeaveDV = cconfig.getInt("hotbar.leave.data-value");
        hotbarLeaveSlot = cconfig.getInt("hotbar.leave.slot");
        
        hotbarPartyName = Extra.tc(clang.getString("hotbar.party.name"));
        hotbarPartyLore = Extra.tCC(clang.getStringList("hotbar.party.lore"));
        hotbarPartyId = cconfig.getInt("hotbar.party.id");
        hotbarPartyDV = cconfig.getInt("hotbar.party.data-value");
        hotbarPartySlot = cconfig.getInt("hotbar.party.slot");
        
        hotbarEventsName = Extra.tc(clang.getString("hotbar.events.name"));
        hotbarEventsLore = Extra.tCC(clang.getStringList("hotbar.events.lore"));
        hotbarEventsId = cconfig.getInt("hotbar.events.id");
        hotbarEventsDV = cconfig.getInt("hotbar.events.data-value");
        hotbarEventsSlot = cconfig.getInt("hotbar.events.slot");
        
        hotbarTournamentsName = Extra.tc(clang.getString("hotbar.tournaments.name"));
        hotbarTournamentsLore = Extra.tCC(clang.getStringList("hotbar.tournaments.lore"));
        hotbarTournamentsId = cconfig.getInt("hotbar.tournaments.id");
        hotbarTournamentsDV = cconfig.getInt("hotbar.tournaments.data-value");
        hotbarTournamentsSlot = cconfig.getInt("hotbar.tournaments.slot");
        
        //-------- Queue
        
        hotbarWaitingLeaveName = Extra.tc(clang.getString("hotbarwaiting.leave.name"));
        hotbarWaitingLeaveLore = Extra.tCC(clang.getStringList("hotbarwaiting.leave.lore"));
        hotbarWaitingLeaveId = cconfig.getInt("hotbarwaiting.leave.id");
        hotbarWaitingLeaveDV = cconfig.getInt("hotbarwaiting.leave.data-value");
        hotbarWaitingLeaveSlot = cconfig.getInt("hotbarwaiting.leave.slot");
        
        //-------- Party
        
        hotbarPartyPlayName = Extra.tc(clang.getString("hotbarparty.play.name"));
        hotbarPartyPlayLore = Extra.tCC(clang.getStringList("hotbarparty.play.lore"));
        hotbarPartyPlayId = cconfig.getInt("hotbarparty.play.id");
        hotbarPartyPlayDV = cconfig.getInt("hotbarparty.play.data-value");
        hotbarPartyPlaySlot = cconfig.getInt("hotbarparty.play.slot");
    }
    
    public static void setHotbar() {
        setHotbarLang();
        
        hotbarRanked = Extra.crearId(hotbarRankedId, hotbarRankedDV, hotbarRankedName, hotbarRankedLore, 1);
        hotbarUnRanked = Extra.crearId(hotbarUnRankedId, hotbarUnRankedDV, hotbarUnRankedName, hotbarUnRankedLore, 1);
        hotbarPet = Extra.crearId(hotbarPetId, hotbarPetDV, hotbarPetName, hotbarPetLore, 1);
        hotbarLeave = Extra.crearId(hotbarLeaveId, hotbarLeaveDV, hotbarLeaveName, hotbarLeaveLore, 1);
        hotbarParty = Extra.crearId(hotbarPartyId, hotbarPartyDV, hotbarPartyName, hotbarPartyLore, 1);
        hotbarEvents = Extra.crearId(hotbarEventsId, hotbarEventsDV, hotbarEventsName, hotbarEventsLore, 1);
        hotbarTournaments = Extra.crearId(hotbarTournamentsId, hotbarTournamentsDV, hotbarTournamentsName, hotbarTournamentsLore, 1);
        
        hotbarWaitingLeave = Extra.crearId(hotbarWaitingLeaveId, hotbarWaitingLeaveDV, hotbarWaitingLeaveName, hotbarWaitingLeaveLore, 1);
        
        hotbarPartyPlay = Extra.crearId(hotbarPartyPlayId, hotbarPartyPlayDV, hotbarPartyPlayName, hotbarPartyPlayLore, 1);
    }
}
