package me.Roy.OlimPets;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import me.Roy.OlimPets.Events.BooleanEvent;
import me.Roy.OlimPets.Events.ChatEvent;
import me.Roy.OlimPets.Events.ClickEvent;
import me.Roy.OlimPets.Events.DamageEvent;
import me.Roy.OlimPets.Events.Inventarios.GuiEvents;
import me.Roy.OlimPets.Events.Inventarios.HotbarEvent;
import me.Roy.OlimPets.Events.JoinEvent;
import me.Roy.OlimPets.Events.MoveEvent;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpOcupadas;
import me.Roy.OlimPets.Games.Jump.JumpMap;
import me.Roy.OlimPets.Inventarios.GuiPartyPlay;
import me.Roy.OlimPets.Inventarios.GuiRankedControl;
import me.Roy.OlimPets.Inventarios.GuiUnrankedControl;
import me.Roy.OlimPets.Inventarios.Hotbar;
import static me.Roy.OlimPets.Lang.cpets;
import static me.Roy.OlimPets.Lang.cstats;
import me.Roy.OlimPets.Objetos.Cuerda;
import me.Roy.OlimPets.versions.Packets;
import me.Roy.OlimPets.versions.v1_10_R1;
import me.Roy.OlimPets.versions.v1_11_R1;
import me.Roy.OlimPets.versions.v1_12_R1;
import me.Roy.OlimPets.versions.v1_8_R3;
import me.Roy.OlimPets.versions.v1_9_R1;
import me.Roy.OlimPets.versions.v1_9_R2;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    
    
    public static Main plugin;
    public static Packets version;
    
    public static final String CVERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    
    public static List<String> listKits = new ArrayList<>();

    File config = new File(getDataFolder() + File.separator + "config.yml");
    File lang = new File(getDataFolder() + File.separator + "lang.yml");
    File spawnpoints = new File(getDataFolder() + File.separator + "spawnpoints.yml");
    File filePets = new File(getDataFolder() + File.separator + "pets.yml");
    File colors = new File(getDataFolder() + File.separator + "colors.yml");
    File hats = new File(getDataFolder() + File.separator + "hats.yml");
    File handitems = new File(getDataFolder() + File.separator + "handitems.yml");
    File maps = new File(getDataFolder() + File.separator + "maps.yml");
    File stats = new File(getDataFolder() + File.separator + "stats.yml");

    @Override
    public void onDisable() {
        for (JumpMap c: jumpOcupadas){
            for (Cuerda cu: c.getCuerdas()){
                cu.removerCuerda();
            }
        }
        Extra.guardar(filePets, cpets);
        Extra.guardar(stats, cstats);
        plugin = null;
    }

    @Override
    public void onEnable() {
        plugin = this;
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        if (!lang.exists()) {
            Extra.copy(getResource("lang.yml"), new File(getDataFolder(), "lang.yml"));
        }
        if (!spawnpoints.exists()) {
            Extra.copy(getResource("spawnpoints.yml"), new File(getDataFolder(), "spawnpoints.yml"));
        }
        if (!filePets.exists()) {
            Extra.copy(getResource("pets.yml"), new File(getDataFolder(), "pets.yml"));
        }
        if (!colors.exists()) {
            Extra.copy(getResource("colors.yml"), new File(getDataFolder(), "colors.yml"));
        }

        if (!hats.exists()) {
            Extra.copy(getResource("hats.yml"), new File(getDataFolder(), "hats.yml"));
        }
        if (!handitems.exists()) {
            Extra.copy(getResource("handitems.yml"), new File(getDataFolder(), "handitems.yml"));
        }
        if (!maps.exists()) {
            Extra.copy(getResource("maps.yml"), new File(getDataFolder(), "maps.yml"));
        }
        if (!stats.exists()) {
            Extra.copy(getResource("stats.yml"), new File(getDataFolder(), "stats.yml"));
        }
        
        switch (CVERSION) {
            /*case "v1_7_R4":
                getServer().getConsoleSender().sendMessage("§7OlimPets > §cDetected 1.7 version.");
                version = new v1_7_R4();
                break;*/
            case "v1_8_R3":
                getServer().getConsoleSender().sendMessage("§7OlimPets > §cDetected 1.8 version.");
                version = new v1_8_R3();
                break;
            case "v1_9_R1":
                getServer().getConsoleSender().sendMessage("§7OlimPets > §cDetected 1.9.2 version.");
                version = new v1_9_R1();
                break;
            case "v1_9_R2":
                getServer().getConsoleSender().sendMessage("§7OlimPets > §cDetected 1.9.4 version.");
                version = new v1_9_R2();
                break;
            case "v1_10_R1":
                getServer().getConsoleSender().sendMessage("§7OlimPets > §cDetected 1.10.2 version.");
                version = new v1_10_R1();
                break;
            case "v1_11_R1":
                getServer().getConsoleSender().sendMessage("§7OlimPets > §cDetected 1.11.2 version.");
                version = new v1_11_R1();
                break;
            case "v1_12_R1":
                getServer().getConsoleSender().sendMessage("§7OlimPets > §cDetected 1.12 version.");
                version = new v1_12_R1();
                break;
            default:
                getServer().getConsoleSender().sendMessage("§7OlimPets > §4Detected " + CVERSION + " version, your version is incompatible, please contact with @Royendero1 on spigot, he will be update this plugin to your version. :D");
                version = new v1_12_R1();
                break;
        }
        
        listKits.add("run");
        listKits.add("jump");
        listKits.add("count");
        listKits.add("antilover");
        loadConfiguraciones();
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new HotbarEvent(), this);
        getServer().getPluginManager().registerEvents(new GuiEvents(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
        getServer().getPluginManager().registerEvents(new BooleanEvent(), this);
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        getCommand("olim").setExecutor(new Command());
        getCommand("party").setExecutor(new Command());
        getServer().getConsoleSender().sendMessage("§2----------------------------");
        getServer().getConsoleSender().sendMessage("§2OlimPets (" + getDescription().getVersion() + ") by " + "§4Royendero");
        getServer().getConsoleSender().sendMessage("§2----------------------------");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getInventory().clear();
            Hotbar.ponerMain(p);
            PetControl.comprobarPet(p);
        }
        Reloj.tiempo();
        cargarItemsMenus();
    }

    public void loadConfiguraciones() {
        Lang.setSpawns();
        Lang.setConfig();
        Lang.setSoundsVersion();
        Lang.setLang();
        LangColors.setLangColors();
        LangColors.loadColors();
        LangColors.loadHats();
        LangColors.loadHandItems();
        GuiUnrankedControl.createInventories();
        GuiUnrankedControl.setUnrankedMenu();
        GuiRankedControl.createInventories();
        GuiRankedControl.setRankedMenu();
        GuiPartyPlay.createInventories();
        GuiPartyPlay.setPlayMenu();
        
        Lang.registerMaps();
    }
    
    public void cargarItemsMenus(){
        LangItemsUpdater.updateLoreRunUnrankedMenu();
        LangItemsUpdater.updateLoreRunRankedMenu();
        LangItemsUpdater.updateLoreRunPartyMenu();
        //---
        LangItemsUpdater.updateLoreJumpUnrankedMenu();
        LangItemsUpdater.updateLoreJumpRankedMenu();
        LangItemsUpdater.updateLoreJumpPartyMenu();
        //---
        LangItemsUpdater.updateLoreCountUnrankedMenu();
        LangItemsUpdater.updateLoreCountRankedMenu();
        LangItemsUpdater.updateLoreCountPartyMenu();
        //---
        LangItemsUpdater.updateLoreAntiloverUnrankedMenu();
        LangItemsUpdater.updateLoreAntiloverRankedMenu();
        LangItemsUpdater.updateLoreAntiloverPartyMenu();
    }

    public File getcConfig() {
        return config;
    }

    public File getcLang() {
        return lang;
    }

    public File getcSpawnPoints() {
        return spawnpoints;
    }

    public File getcPets() {
        return filePets;
    }

    public File getcColors() {
        return colors;
    }

    public File getcHats() {
        return hats;
    }

    public File getcHandItems() {
        return handitems;
    }

    public File getcMaps() {
        return maps;
    }
    
    public File getcStats() {
        return stats;
    }
}
