package me.Roy.OlimPets;

import java.io.File;
import java.util.List;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverLibres;
import me.Roy.OlimPets.Games.Antilover.AntiloverMap;
import static me.Roy.OlimPets.Games.Count.CountControl.countLibres;
import me.Roy.OlimPets.Games.Count.CountMap;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpLibres;
import me.Roy.OlimPets.Games.Jump.JumpMap;
import static me.Roy.OlimPets.Games.Run.RunControl.runLibres;
import me.Roy.OlimPets.Games.Run.RunMap;
import static me.Roy.OlimPets.Main.CVERSION;
import static me.Roy.OlimPets.Main.plugin;
import static me.Roy.OlimPets.Main.version;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Lang {

    public static File config, lang, spawnpoints, filePets, colors, hats, handitems, maps, stats;
    public static FileConfiguration cconfig, clang, cspawnpoints, cpets, ccolors, chats, chanditems, cmaps, cstats;

    public static Location spawnLobby;

    public static void setSpawns() {
        config = plugin.getcConfig();
        cconfig = YamlConfiguration.loadConfiguration(config);
        lang = plugin.getcLang();
        clang = YamlConfiguration.loadConfiguration(lang);
        spawnpoints = plugin.getcSpawnPoints();
        cspawnpoints = YamlConfiguration.loadConfiguration(spawnpoints);
        filePets = plugin.getcPets();
        cpets = YamlConfiguration.loadConfiguration(filePets);
        colors = plugin.getcColors();
        ccolors = YamlConfiguration.loadConfiguration(colors);
        hats = plugin.getcHats();
        chats = YamlConfiguration.loadConfiguration(hats);
        handitems = plugin.getcHandItems();
        chanditems = YamlConfiguration.loadConfiguration(handitems);
        maps = plugin.getcMaps();
        cmaps = YamlConfiguration.loadConfiguration(maps);
        stats = plugin.getcStats();
        cstats = YamlConfiguration.loadConfiguration(stats);

        
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                spawnLobby = new Location(
                Bukkit.getWorld(cspawnpoints.getString("lobby.w")),
                cspawnpoints.getDouble("lobby.x"),
                cspawnpoints.getDouble("lobby.y"),
                cspawnpoints.getDouble("lobby.z"),
                cspawnpoints.getInt("lobby.yaw"),
                cspawnpoints.getInt("lobby.pitch"));
            }
        }, 5L);
    }

    public static String backServer;
    public static int nameMaxLenght, unrankedMaxPlayers, unrankedMinPlayers, unrankedPreTime,
            partyMaxPlayers, initialElo;
    public static boolean showNoPermissionColor;
    
    public static double runZombieStep, runZombieSpawnDistance, jumpZombieSpawnDistance, jumpFenceVelocity,
            antiloverZombieStep, antiloverLoverStep;

    public static int guiPetArmorHelmetSlot, guiPetArmorChestplateSlot, guiPetArmorLeggingsSlot, guiPetArmorBootsSlot, guiPetArmorHandSlot;

    public static List<Double> countZombieVelocity;
    
    public static void setConfig() {
        LangItems.setItems();

        backServer = cconfig.getString("backserver");
        nameMaxLenght = cconfig.getInt("namepetmaxlenght");
        if (nameMaxLenght > 16) {
            nameMaxLenght = 16;
        }
        unrankedMaxPlayers = cconfig.getInt("unrankedmaxplayers");
        unrankedMinPlayers = cconfig.getInt("unrankedminplayers");
        unrankedPreTime = cconfig.getInt("unrankedpretime") * 20;
        partyMaxPlayers = cconfig.getInt("partymaxplayers");
        initialElo = cconfig.getInt("initialelo");
        showNoPermissionColor = cconfig.getBoolean("color.shownopermissioncolor");

        runZombieStep = cconfig.getDouble("game.run.zombiestep");
        runZombieSpawnDistance = cconfig.getDouble("game.run.zombiespawndistance");
        
        jumpZombieSpawnDistance = cconfig.getDouble("game.jump.zombiespawndistance");
        jumpFenceVelocity = cconfig.getDouble("game.jump.fencevelocity");
        
        countZombieVelocity = cconfig.getDoubleList("game.count.zombievelocity");
        
        antiloverZombieStep = cconfig.getDouble("game.antilover.zombiestep");
        antiloverLoverStep = cconfig.getDouble("game.antilover.loverstep");
        
        guiPetArmorHelmetSlot = cconfig.getInt("gui.petarmor.helmet.slot");
        guiPetArmorChestplateSlot = cconfig.getInt("gui.petarmor.chestplate.slot");
        guiPetArmorLeggingsSlot = cconfig.getInt("gui.petarmor.leggings.slot");
        guiPetArmorBootsSlot = cconfig.getInt("gui.petarmor.boots.slot");
        guiPetArmorHandSlot = cconfig.getInt("gui.petarmor.hand.slot");

    }

    public static String guiPetTitle, guiPetArmorTitle, guiUnrankedTitle, guiRankedTitle,
            guiPartyPlayTitle;
    public static String guiArmorHelmetTitle, guiArmorChestplateTitle, guiArmorLeggingsTitle, guiArmorBootsTitle, guiArmorHandTitle;
    public static String guiArmorHatTitle, guiArmorHandItemsTitle;
    public static String noPermission, writeNameChanged, nameChanged, petSummoned, petHided, waitingGame, startingPreGame;

    public static String notCreating, needMinSpawns, creatingCanceled, configureRunStep, configureRunFinal, configureRunCreated, configureRunSpawn,
            runStartingMsg, noMapsAvailable, playerLeaved, playerExit,
            noDestroy, noPlace, noMapsSlotsAvailable, playerOffline, playerRankedEloStart,
            playerRankedEloFinish, youArePlaying, configureJumpSpawn, configureJumpPlayer,
            configureJumpCreated, configureCountSpawn, configureCountSpawnFinal, configureCountSpawnPlayer, 
            configureCountCreated, configureAntiloverZombieSpawn, configureAntiloverPlayerSpawn,
            configureAntiloverLoverSpawn, configureAntiloverFirstBorder, configureAntiloverSecondBorder,
            configureAntiloverCreated;
    
    public static String partyPrefix, partyNewOwner, partyKickPlayer, partyKicked,
            partyJoined, partyLeaved, partySent, partyInvited, partyNoInvited, partyInvitedClick,
            partyPlayerInParty, partyNoExist, partyDontHave, partyNoOwner, partyPlayerHasInvited,
            partyNoInParty, partyYouHave, partyFull, partyMin2Players, playerAlreadyPlaying;
    
    public static List<String> runHow, jumpHow, countHow, countResults, partyCreateMsg,
            antiloverHow;
    public static String runFinish, runWinner, jumpLose, jumpWinner, jumpStartingMsg,
            countStartingMsg, countWinner, countDraw, antiloverStartingMsg, 
            antiloverLose, antiloverWinner;

    public static void setLang() {
        guiPetTitle = Extra.tc(clang.getString("gui.petmenu.title"));
        guiPetArmorTitle = Extra.tc(clang.getString("gui.petarmormenu.title"));
        noPermission = Extra.tc(clang.getString("nopermission"));
        writeNameChanged = Extra.tc(clang.getString("writenamechanged"));
        nameChanged = Extra.tc(clang.getString("namechanged"));
        petSummoned = Extra.tc(clang.getString("petsummoned"));
        petHided = Extra.tc(clang.getString("pethide"));
        notCreating = Extra.tc(clang.getString("creatingmap.notare"));

        creatingCanceled = Extra.tc(clang.getString("creatingmap.canceled"));
        configureRunStep = Extra.tc(clang.getString("creatingmap.run.step"));
        configureRunFinal = Extra.tc(clang.getString("creatingmap.run.final"));
        configureRunCreated = Extra.tc(clang.getString("creatingmap.run.created"));
        configureRunSpawn = Extra.tc(clang.getString("creatingmap.run.spawn"));

        configureJumpSpawn = Extra.tc(clang.getString("creatingmap.jump.zombiespawn"));
        configureJumpPlayer = Extra.tc(clang.getString("creatingmap.jump.playerspawn"));
        configureJumpCreated = Extra.tc(clang.getString("creatingmap.jump.created"));
        
        configureCountSpawn = Extra.tc(clang.getString("creatingmap.count.zombiespawn"));
        configureCountSpawnFinal = Extra.tc(clang.getString("creatingmap.count.zombiespawnfinal"));
        configureCountSpawnPlayer = Extra.tc(clang.getString("creatingmap.count.zombiespawnplayer"));
        configureCountCreated = Extra.tc(clang.getString("creatingmap.count.created"));
        
        configureAntiloverZombieSpawn = Extra.tc(clang.getString("creatingmap.antilover.zombiespawn"));
        configureAntiloverPlayerSpawn = Extra.tc(clang.getString("creatingmap.antilover.playerspawn"));
        configureAntiloverLoverSpawn = Extra.tc(clang.getString("creatingmap.antilover.loverspawn"));
        configureAntiloverFirstBorder = Extra.tc(clang.getString("creatingmap.antilover.firstborder"));
        configureAntiloverSecondBorder = Extra.tc(clang.getString("creatingmap.antilover.secondborder"));
        configureAntiloverCreated = Extra.tc(clang.getString("creatingmap.antilover.created"));
        
        waitingGame = Extra.tc(clang.getString("game.waiting"));
        startingPreGame = Extra.tc(clang.getString("game.startingpregame"));
        noMapsAvailable = Extra.tc(clang.getString("game.nomapsavailable"));
        playerLeaved = Extra.tc(clang.getString("game.playerleaved"));
        playerExit = Extra.tc(clang.getString("game.playerexit"));
        noDestroy = Extra.tc(clang.getString("game.nodestroy"));
        noPlace = Extra.tc(clang.getString("game.noplace"));
        noMapsSlotsAvailable = Extra.tc(clang.getString("game.nomapsslotavailables"));
        playerOffline = Extra.tc(clang.getString("game.playeroffline"));
        playerRankedEloStart = Extra.tc(clang.getString("game.rankedelostart"));
        playerRankedEloFinish = Extra.tc(clang.getString("game.rankedelofinish"));
        youArePlaying = Extra.tc(clang.getString("game.youareplaying"));
        playerAlreadyPlaying = Extra.tc(clang.getString("game.alreadyplaying"));
        
        partyPrefix = Extra.tc(clang.getString("prefix.partychat"));
        
        partyNewOwner = Extra.tc(clang.getString("party.newowner"));
        partyKickPlayer = Extra.tc(clang.getString("party.kickplayer"));
        partyKicked = Extra.tc(clang.getString("party.kicked"));
        partyJoined = Extra.tc(clang.getString("party.joined"));
        partyLeaved = Extra.tc(clang.getString("party.leaved"));
        partySent = Extra.tc(clang.getString("party.sent"));
        partyInvited = Extra.tc(clang.getString("party.invited"));
        partyInvitedClick = Extra.tc(clang.getString("party.invitedclick"));
        partyNoInvited = Extra.tc(clang.getString("party.noinvited"));
        partyPlayerInParty = Extra.tc(clang.getString("party.playerinparty"));
        partyPlayerHasInvited = Extra.tc(clang.getString("party.hasinvited"));
        partyNoExist = Extra.tc(clang.getString("party.noexist"));
        partyDontHave = Extra.tc(clang.getString("party.donthave"));
        partyNoOwner = Extra.tc(clang.getString("party.noowner"));
        partyNoInParty = Extra.tc(clang.getString("party.noinparty"));
        partyYouHave = Extra.tc(clang.getString("party.youhave"));
        partyFull = Extra.tc(clang.getString("party.full"));
        partyMin2Players = Extra.tc(clang.getString("party.min2players"));


        guiArmorHelmetTitle = Extra.tc(clang.getString("gui.petarmormenu.petarmorcolorhelmet"));
        guiArmorChestplateTitle = Extra.tc(clang.getString("gui.petarmormenu.petarmorcolorchestplate"));
        guiArmorLeggingsTitle = Extra.tc(clang.getString("gui.petarmormenu.petarmorcolorleggings"));
        guiArmorBootsTitle = Extra.tc(clang.getString("gui.petarmormenu.petarmorcolorboots"));
        guiArmorHandTitle = Extra.tc(clang.getString("gui.petarmormenu.petarmorcolorhand"));

        guiArmorHatTitle = Extra.tc(clang.getString("gui.petarmormenu.petarmorhat"));
        guiArmorHandItemsTitle = Extra.tc(clang.getString("gui.petarmormenu.petarmorhanditems"));

        guiUnrankedTitle = Extra.tc(clang.getString("gui.unranked.title"));
        guiRankedTitle = Extra.tc(clang.getString("gui.ranked.title"));
        guiPartyPlayTitle = Extra.tc(clang.getString("gui.partyplay.title"));
        
        runStartingMsg = Extra.tc(clang.getString("game.run.starting"));
        runHow = Extra.tCC(clang.getStringList("game.run.how"));
        runFinish = Extra.tc(clang.getString("game.run.finish"));
        runWinner = Extra.tc(clang.getString("game.run.winner"));
        
        jumpStartingMsg = Extra.tc(clang.getString("game.jump.starting"));
        jumpHow = Extra.tCC(clang.getStringList("game.jump.how"));
        jumpLose = Extra.tc(clang.getString("game.jump.lose"));
        jumpWinner = Extra.tc(clang.getString("game.jump.winner"));
        
        countStartingMsg = Extra.tc(clang.getString("game.count.starting"));
        countHow = Extra.tCC(clang.getStringList("game.count.how"));
        countResults = Extra.tCC(clang.getStringList("game.count.results"));
        countWinner = Extra.tc(clang.getString("game.count.winner"));
        countDraw = Extra.tc(clang.getString("game.count.draw"));
        
        antiloverStartingMsg = Extra.tc(clang.getString("game.antilover.starting"));
        antiloverHow = Extra.tCC(clang.getStringList("game.antilover.how"));
        antiloverLose = Extra.tc(clang.getString("game.antilover.lose"));
        antiloverWinner = Extra.tc(clang.getString("game.antilover.winner"));
        
        partyCreateMsg = Extra.tCC(clang.getStringList("party.createmsg"));

    }

    public static void registerMaps() {
        if (cmaps.contains("run")) {
            for (String c : cmaps.getConfigurationSection("run").getKeys(false)) {
                Location player = new Location(
                        Bukkit.getWorld(cmaps.getString("run."+c+".spawnplayer.w")),
                            cmaps.getDouble("run."+c+".spawnplayer.x"),
                            cmaps.getDouble("run."+c+".spawnplayer.y"),
                            cmaps.getDouble("run."+c+".spawnplayer.z"),
                            cmaps.getInt("run."+c+".spawnplayer.yaw"),
                            cmaps.getInt("run."+c+".spawnplayer.pitch"));
                Location zombie = new Location(
                        Bukkit.getWorld(cmaps.getString("run."+c+".spawnzombie.w")),
                            cmaps.getDouble("run."+c+".spawnzombie.x"),
                            cmaps.getDouble("run."+c+".spawnzombie.y"),
                            cmaps.getDouble("run."+c+".spawnzombie.z"),
                            cmaps.getInt("run."+c+".spawnzombie.yaw"),
                            cmaps.getInt("run."+c+".spawnzombie.pitch"));
                runLibres.add(new RunMap(player, zombie, cmaps.getInt("run."+c+".final"), cmaps.getInt("run."+c+".id")));
            }
        }
        if (cmaps.contains("jump")) {
            for (String c : cmaps.getConfigurationSection("jump").getKeys(false)) {
                Location loc = new Location(
                        Bukkit.getWorld(cmaps.getString("jump."+c+".spawnplayer.w")),
                            cmaps.getDouble("jump."+c+".spawnplayer.x"),
                            cmaps.getDouble("jump."+c+".spawnplayer.y"),
                            cmaps.getDouble("jump."+c+".spawnplayer.z"),
                            cmaps.getInt("jump."+c+".spawnplayer.yaw"),
                            cmaps.getInt("jump."+c+".spawnplayer.pitch"));
                Location sp = new Location(
                        Bukkit.getWorld(cmaps.getString("jump."+c+".spawnzombie.w")),
                            cmaps.getDouble("jump."+c+".spawnzombie.x"),
                            cmaps.getDouble("jump."+c+".spawnzombie.y"),
                            cmaps.getDouble("jump."+c+".spawnzombie.z"),
                            cmaps.getInt("jump."+c+".spawnzombie.yaw"),
                            cmaps.getInt("jump."+c+".spawnzombie.pitch"));
                jumpLibres.add(new JumpMap(loc, sp, cmaps.getInt("jump."+c+".id")));
            }
        }
        if (cmaps.contains("count")) {
            for (String c : cmaps.getConfigurationSection("count").getKeys(false)) {
                Location pla = new Location(
                        Bukkit.getWorld(cmaps.getString("count."+c+".spawnplayer.w")),
                            cmaps.getDouble("count."+c+".spawnplayer.x"),
                            cmaps.getDouble("count."+c+".spawnplayer.y"),
                            cmaps.getDouble("count."+c+".spawnplayer.z"),
                            cmaps.getInt("count."+c+".spawnplayer.yaw"),
                            cmaps.getInt("count."+c+".spawnplayer.pitch"));
                Location loc = new Location(
                        Bukkit.getWorld(cmaps.getString("count."+c+".spawn.w")),
                            cmaps.getDouble("count."+c+".spawn.x"),
                            cmaps.getDouble("count."+c+".spawn.y"),
                            cmaps.getDouble("count."+c+".spawn.z"),
                            cmaps.getInt("count."+c+".spawn.yaw"),
                            cmaps.getInt("count."+c+".spawn.pitch"));
                Location sp = new Location(
                        Bukkit.getWorld(cmaps.getString("count."+c+".spawnfinal.w")),
                            cmaps.getDouble("count."+c+".spawnfinal.x"),
                            cmaps.getDouble("count."+c+".spawnfinal.y"),
                            cmaps.getDouble("count."+c+".spawnfinal.z"),
                            cmaps.getInt("count."+c+".spawnfinal.yaw"),
                            cmaps.getInt("count."+c+".spawnfinal.pitch"));
                countLibres.add(new CountMap(pla, loc, sp, cmaps.getInt("count."+c+".id")));
            }
        }
        if (cmaps.contains("antilover")) {
            for (String c : cmaps.getConfigurationSection("antilover").getKeys(false)) {
                Location pla = new Location(
                        Bukkit.getWorld(cmaps.getString("antilover."+c+".spawnplayer.w")),
                            cmaps.getDouble("antilover."+c+".spawnplayer.x"),
                            cmaps.getDouble("antilover."+c+".spawnplayer.y"),
                            cmaps.getDouble("antilover."+c+".spawnplayer.z"),
                            cmaps.getInt("antilover."+c+".spawnplayer.yaw"),
                            cmaps.getInt("antilover."+c+".spawnplayer.pitch"));
                Location zom = new Location(
                        Bukkit.getWorld(cmaps.getString("antilover."+c+".spawnzombie.w")),
                            cmaps.getDouble("antilover."+c+".spawnzombie.x"),
                            cmaps.getDouble("antilover."+c+".spawnzombie.y"),
                            cmaps.getDouble("antilover."+c+".spawnzombie.z"),
                            cmaps.getInt("antilover."+c+".spawnzombie.yaw"),
                            cmaps.getInt("antilover."+c+".spawnzombie.pitch"));
                Location lov = new Location(
                        Bukkit.getWorld(cmaps.getString("antilover."+c+".spawnlovers.w")),
                            cmaps.getDouble("antilover."+c+".spawnlovers.x"),
                            cmaps.getDouble("antilover."+c+".spawnlovers.y"),
                            cmaps.getDouble("antilover."+c+".spawnlovers.z"),
                            cmaps.getInt("antilover."+c+".spawnlovers.yaw"),
                            cmaps.getInt("antilover."+c+".spawnlovers.pitch"));
                
                antiloverLibres.add(new AntiloverMap(pla,
                        zom,
                        lov,
                        cmaps.getInt("antilover."+c+".firstborder"),
                        cmaps.getInt("antilover."+c+".secondborder"),
                        cmaps.getInt("antilover."+c+".id")));
            }
        }
    }

    public static String NOTE_BASS, CHICKEN_EGG_POP, NOTE_PLING, BURP, ORB_PICKUP, SPLASH2, LEVEL_UP, WITHER_DEATH, CHEST_CLOSE, CHEST_OPEN,
            ITEM_PICKUP, VILLAGER_YES, VILLAGER_NO, HORSE_ARMOR, NOTE_STICKS, EXPLODE;

    public static void setSoundsVersion() {
        if (CVERSION.equals("v1_7_R4") || CVERSION.equals("v1_8_R3")) {
            NOTE_BASS = "NOTE_BASS";
            NOTE_STICKS = "NOTE_STICKS";
            CHICKEN_EGG_POP = "CHICKEN_EGG_POP";
            NOTE_PLING = "NOTE_PLING";
            BURP = "BURP";
            ORB_PICKUP = "ORB_PICKUP";
            SPLASH2 = "SPLASH2";
            LEVEL_UP = "LEVEL_UP";
            WITHER_DEATH = "WITHER_DEATH";
            CHEST_CLOSE = "CHEST_CLOSE";
            CHEST_OPEN = "CHEST_OPEN";
            ITEM_PICKUP = "ITEM_PICKUP";
            VILLAGER_YES = "VILLAGER_YES";
            VILLAGER_NO = "VILLAGER_NO";
            HORSE_ARMOR = "HORSE_ARMOR";
            EXPLODE = "EXPLODE";
        } else {
            //if (version.equals("9")){
            NOTE_BASS = "BLOCK_NOTE_BASS";
            NOTE_STICKS = "BLOCK_NOTE_STICKS";
            CHICKEN_EGG_POP = "ENTITY_CHICKEN_EGG";
            NOTE_PLING = "BLOCK_NOTE_PLING";
            BURP = "ENTITY_PLAYER_BURP";
            ORB_PICKUP = "ENTITY_EXPERIENCE_ORB_PICKUP";
            SPLASH2 = "ENTITY_PLAYER_SPLASH";
            LEVEL_UP = "ENTITY_PLAYER_LEVELUP";
            WITHER_DEATH = "ENTITY_WITHER_DEATH";
            CHEST_CLOSE = "BLOCK_CHEST_CLOSE";
            CHEST_OPEN = "BLOCK_CHEST_OPEN";
            ITEM_PICKUP = "ENTITY_ITEM_PICKUP";
            VILLAGER_YES = "ENTITY_VILLAGER_YES";
            VILLAGER_NO = "ENTITY_VILLAGER_NO";
            HORSE_ARMOR = "ENTITY_HORSE_ARMOR";
            EXPLODE = "BLOCK_GLASS_BREAK";
        }
    }
}
