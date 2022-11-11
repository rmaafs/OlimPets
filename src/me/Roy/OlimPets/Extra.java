package me.Roy.OlimPets;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverEsperando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverPartyJugando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedEsperando;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControlRanked.antiloverRankedJugando;
import me.Roy.OlimPets.Games.Antilover.AntiloverCreate;
import static me.Roy.OlimPets.Games.Antilover.AntiloverCreate.antiloverCrearMapa;
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
import static me.Roy.OlimPets.Games.Run.RunControl.runClickeando;
import static me.Roy.OlimPets.Games.Run.RunControl.runEsperando;
import static me.Roy.OlimPets.Games.Run.RunControl.runJugando;
import static me.Roy.OlimPets.Games.Run.RunControl.runPartyJugando;
import static me.Roy.OlimPets.Games.Run.RunControlRanked.runRankedEsperando;
import static me.Roy.OlimPets.Games.Run.RunControlRanked.runRankedJugando;
import static me.Roy.OlimPets.Games.Run.RunCreate.runCrearMapa;
import static me.Roy.OlimPets.Games.Run.RunCreate.runCrearMapaLoc;
import static me.Roy.OlimPets.Lang.backServer;
import static me.Roy.OlimPets.Lang.cstats;
import static me.Roy.OlimPets.Lang.initialElo;
import static me.Roy.OlimPets.Lang.unrankedMaxPlayers;
import static me.Roy.OlimPets.Main.elmen;
import static me.Roy.OlimPets.Main.listKits;
import static me.Roy.OlimPets.Main.plugin;
import me.Roy.OlimPets.Objetos.Colores;
import me.Roy.OlimPets.Objetos.Hand;
import me.Roy.OlimPets.Objetos.Hat;
import me.Roy.OlimPets.Objetos.Party;
import me.Roy.OlimPets.Objetos.Pet;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffectType;

public class Extra {

    public static HashMap<Player, Pet> pets = new HashMap<>();
    public static HashMap<Player, Zombie> petsZombie = new HashMap<>();
    public static HashMap<Zombie, Player> petsZombieDueño = new HashMap<>();
    public static List<Player> listaEditandoNombre = new ArrayList<>();

    public static List<Colores> colores = new ArrayList<>();
    public static List<Colores> mineralesColores = new ArrayList<>();
    public static List<Hat> sombreros = new ArrayList<>();
    public static List<Hand> handItems = new ArrayList<>();

    public static HashMap<Player, Party> partys = new HashMap<>();
    public static HashMap<Player, Party> prePartys = new HashMap<>();
    public static HashMap<Player, Long> cooldownParty = new HashMap<>();
    
    public static List<Player> jumpPlayers = new ArrayList<>();
    //public static List<Cuerda> cuerdas = new ArrayList<>();

    public static void sacar(Player p) {
        if (listaEditandoNombre.contains(p)) {
            listaEditandoNombre.remove(p);
        }
        if (runCrearMapa.containsKey(p)) {
            runCrearMapa.remove(p);
        }
        if (runEsperando.contains(p)) {
            runEsperando.remove(p);
        }
        if (runCrearMapaLoc.containsKey(p)) {
           runCrearMapaLoc.remove(p);
        }
        if (runJugando.containsKey(p)) {
            runJugando.get(p).exit(p);
            runJugando.remove(p);
        }
        if (runRankedEsperando.contains(p)) {
            runRankedEsperando.remove(p);
        }
        if (runRankedJugando.containsKey(p)) {
            runRankedJugando.get(p).exit(p);
            runRankedJugando.remove(p);
        }
        if (runPartyJugando.containsKey(p)) {
            runPartyJugando.get(p).exit(p);
            runPartyJugando.remove(p);
        }
        if (runClickeando.containsKey(p)) {
            runClickeando.remove(p);
        }
        if (jumpCrearMapa.containsKey(p)) {
            jumpCrearMapa.remove(p);
        }
        if (jumpZombieLocation.containsKey(p)) {
            jumpZombieLocation.remove(p);
        }
        if (jumpJugando.containsKey(p)) {
            jumpJugando.get(p).exit(p);
            jumpJugando.remove(p);
        }
        if (jumpRankedJugando.containsKey(p)) {
            jumpRankedJugando.get(p).exit(p);
            jumpRankedJugando.remove(p);
        }
        if (jumpPartyJugando.containsKey(p)) {
            jumpPartyJugando.get(p).exit(p);
            jumpPartyJugando.remove(p);
        }
        if (countCrearMapa.containsKey(p)) {
            countCrearMapa.remove(p);
        }
        if (countZombieLocation.containsKey(p)) {
            countZombieLocation.remove(p);
        }
        if (countZombiePlayer.containsKey(p)) {
            countZombiePlayer.remove(p);
        }
        if (countJugando.containsKey(p)) {
            countJugando.get(p).exit(p);
            countJugando.remove(p);
        }
        if (countRankedJugando.containsKey(p)) {
            countRankedJugando.get(p).exit(p);
            countRankedJugando.remove(p);
        }
        if (countPartyJugando.containsKey(p)) {
            countPartyJugando.get(p).exit(p);
            countPartyJugando.remove(p);
        }
        
        if (antiloverCrearMapa.containsKey(p)) {
            AntiloverCreate.sacar(p);
        }
        if (antiloverJugando.containsKey(p)) {
            antiloverJugando.get(p).exit(p);
            antiloverJugando.remove(p);
        }
        if (antiloverRankedJugando.containsKey(p)) {
            antiloverRankedJugando.get(p).exit(p);
            antiloverRankedJugando.remove(p);
        }
        if (antiloverPartyJugando.containsKey(p)) {
            antiloverPartyJugando.get(p).exit(p);
            antiloverPartyJugando.remove(p);
        }
        if (partys.containsKey(p)) {
            PartyControl.salirParty(p, false);
        }
        if (prePartys.containsKey(p)) {
            prePartys.remove(p);
        }
        for (String kit : listKits) {
            if (pets.get(p).getElo(kit) != initialElo) {
                cstats.set(p.getUniqueId().toString() + "." + kit + ".elo", pets.get(p).getElo(kit));
            }
            if (pets.get(p).getPlayed(kit) != 0) {
                cstats.set(p.getUniqueId().toString() + "." + kit + ".played", pets.get(p).getPlayed(kit));
            }
            if (pets.get(p).getWins(kit) != 0) {
                cstats.set(p.getUniqueId().toString() + "." + kit + ".wins", pets.get(p).getWins(kit));
            }
            cstats.set(p.getUniqueId().toString() + "." + kit + ".owner", p.getName());
        }
        if (petsZombieDueño.containsValue(p)){
            petsZombieDueño.remove(petsZombie.get(p));
        }
        if (petsZombie.containsKey(p)){
            petsZombie.get(p).remove();
            petsZombie.remove(p);
        }
        if (pets.containsKey(p)) {
            pets.remove(p);
        }
    }

    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte['?'];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void guardar(File file, FileConfiguration fc) {
        try {
            fc.save(file);

        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ItemStack crearId(int id, int dv, String d, List<String> l, int a) {
        ItemStack item = new ItemStack(id, a, (short) dv);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(d);
        if (!(l == null)) {
            List<String> Lore = new ArrayList();
            Lore.addAll(tCC(l));
            meta.setLore(Lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack crearIdLore(int id, int dv, String d, List<String> l, int a, String type) {
        ItemStack item = new ItemStack(id, a, (short) dv);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(d);
        if (!(l == null)) {
            List<String> Lore = new ArrayList();
            Lore.addAll(tCCLore(l, type));
            meta.setLore(Lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack crearTraje(Material material, Color color, String name, List<String> lore) {
        ItemStack lchest = new ItemStack(material, 1);
        LeatherArmorMeta lch = (LeatherArmorMeta) lchest.getItemMeta();
        lch.setColor(color);
        lch.setDisplayName(name);
        lch.setLore(lore);
        lchest.setItemMeta(lch);
        return lchest;
    }

    public static List<String> tCC(List<String> list) {
        List<String> finalList = new ArrayList();
        int size = list.size();
        for (int index = 0; index < size; index++) {
            String string = ChatColor.translateAlternateColorCodes('&', (String) list.get(index));
            finalList.add(string);
        }
        return finalList;
    }

    public static List<String> tCCLore(List<String> list, String type) {
        List<String> finalList = new ArrayList();
        int size = list.size();
        for (int index = 0; index < size; index++) {
            String string = ChatColor.translateAlternateColorCodes('&', (String) list.get(index));
            if (type.equals("runUn")) {
                string = string.replaceAll("<playing>", "" + runJugando.size()).replaceAll("<waiting>", "" + runEsperando.size()).replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            } else if (type.equals("runR")) {
                string = string.replaceAll("<playing>", "" + runRankedJugando.size()).replaceAll("<waiting>", "" + runRankedEsperando.size()).replaceAll("<maxunranked>", "2");
            } else if (type.equals("runP")) {
                string = string.replaceAll("<playing>", "" + runPartyJugando.size()).replaceAll("<waiting>", "0").replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            } else if (type.equals("jumpUn")) {
                string = string.replaceAll("<playing>", "" + jumpJugando.size()).replaceAll("<waiting>", "" + jumpEsperando.size()).replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            } else if (type.equals("jumpR")) {
                string = string.replaceAll("<playing>", "" + jumpRankedJugando.size()).replaceAll("<waiting>", "" + jumpRankedEsperando.size()).replaceAll("<maxunranked>", "2");
            } else if (type.equals("jumpP")) {
                string = string.replaceAll("<playing>", "" + jumpPartyJugando.size()).replaceAll("<waiting>", "0").replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            } else if (type.equals("countUn")) {
                string = string.replaceAll("<playing>", "" + countJugando.size()).replaceAll("<waiting>", "" + countEsperando.size()).replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            } else if (type.equals("countR")) {
                string = string.replaceAll("<playing>", "" + countRankedJugando.size()).replaceAll("<waiting>", "" + countRankedEsperando.size()).replaceAll("<maxunranked>", "2");
            } else if (type.equals("countP")) {
                string = string.replaceAll("<playing>", "" + countPartyJugando.size()).replaceAll("<waiting>", "0").replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            } else if (type.equals("antiloverUn")) {
                string = string.replaceAll("<playing>", "" + antiloverJugando.size()).replaceAll("<waiting>", "" + antiloverEsperando.size()).replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            } else if (type.equals("antiloverR")) {
                string = string.replaceAll("<playing>", "" + antiloverRankedJugando.size()).replaceAll("<waiting>", "" + antiloverRankedEsperando.size()).replaceAll("<maxunranked>", "2");
            } else if (type.equals("antiloverP")) {
                string = string.replaceAll("<playing>", "" + antiloverPartyJugando.size()).replaceAll("<waiting>", "0").replaceAll("<maxunranked>", "" + unrankedMaxPlayers);
            }
            finalList.add(string);
        }
        return finalList;
    }

    public static String tc(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sonido(Player player, String sonido) {
        player.playSound(player.getLocation(), Sound.valueOf(sonido), 1.0F, 1.0F);
    }
    
    public static int rand(int min, int max) {
        Random r = new Random();
        boolean neg = false;
        if (min < 0){
            min = min * -1;
            neg = true;
        }
        if (max < 0){
            max = max * -1;
            neg = true;
        }
        int t = r.nextInt((max - min) + 1) + min;
        if (neg){
            t = t * -1;
        }
        return t;
    }

    public static void limpiarP(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.setLevel(0);
        player.setFoodLevel(20);
        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
        if (player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
        if (player.hasPotionEffect(PotionEffectType.POISON)) {
            player.removePotionEffect(PotionEffectType.POISON);
        }
        if (player.hasPotionEffect(PotionEffectType.ABSORPTION)) {
            player.removePotionEffect(PotionEffectType.ABSORPTION);
        }
        if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        }
        if (player.hasPotionEffect(PotionEffectType.SLOW)) {
            player.removePotionEffect(PotionEffectType.SLOW);
        }
        if (player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
            player.removePotionEffect(PotionEffectType.BLINDNESS);
        }
        if (player.hasPotionEffect(PotionEffectType.REGENERATION)) {
            player.removePotionEffect(PotionEffectType.REGENERATION);
        }
        if (player.hasPotionEffect(PotionEffectType.HARM)) {
            player.removePotionEffect(PotionEffectType.HARM);
        }
        if (player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
            player.removePotionEffect(PotionEffectType.WEAKNESS);
        }
        if (player.hasPotionEffect(PotionEffectType.SATURATION)) {
            player.removePotionEffect(PotionEffectType.SATURATION);
        }
        if (player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
            player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        }
        if (player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        }
        player.setHealth(20.0);
        player.setFireTicks(0);
    }

    public static String secToMin(int i) {
        String f = "0";
        if (i < 0) {
            i = 0;
        }
        if (i >= 3600 && i < 7200) {
            i = i - 3600;
            int ms = i / 60;
            int ss = i % 60;
            String m = (ms < 10 ? "0" : "") + ms;
            String s = (ss < 10 ? "0" : "") + ss;
            f = "1:" + m + ":" + s;
        } else if (i >= 7200 && i < 10800) {
            i = i - 7200;
            int ms = i / 60;
            int ss = i % 60;
            String m = (ms < 10 ? "0" : "") + ms;
            String s = (ss < 10 ? "0" : "") + ss;
            f = "2:" + m + ":" + s;
        } else if (i >= 10800 && i < 14400) {
            i = i - 10800;
            int ms = i / 60;
            int ss = i % 60;
            String m = (ms < 10 ? "0" : "") + ms;
            String s = (ss < 10 ? "0" : "") + ss;
            f = "3:" + m + ":" + s;
        } else {
            int ms = i / 60;
            int ss = i % 60;
            String m = (ms < 10 ? "0" : "") + ms;
            String s = (ss < 10 ? "0" : "") + ss;
            f = m + ":" + s;
        }

        return f;
    }

    public static void enviarServer(Player p) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(backServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    public static int eloM(int total) {
        if (total <= -500) {
            total = 31;
        } else if (total <= -450 && total >= -499) {
            total = 30;
        } else if (total <= -400 && total >= -449) {
            total = 29;
        } else if (total <= -350 && total >= -399) {
            total = 28;
        } else if (total <= -300 && total >= -349) {
            total = 27;
        } else if (total <= -250 && total >= -299) {
            total = 26;
        } else if (total <= -200 && total >= -249) {
            total = 24;
        } else if (total <= -150 && total >= -199) {
            total = 22;
        } else if (total <= -100 && total >= -149) {
            total = 20;
        } else if (total <= -50 && total >= -99) {
            total = 19;
        } else if (total <= 0 && total >= -49) {
            total = 17;
        } //--------------
        else if (total >= 450) {
            total = 1;
        } else if (total >= 400 && total <= 449) {
            total = 2;
        } else if (total >= 350 && total <= 399) {
            total = 3;
        } else if (total >= 300 && total <= 349) {
            total = 4;
        } else if (total >= 250 && total <= 299) {
            total = 6;
        } else if (total >= 200 && total <= 249) {
            total = 8;
        } else if (total >= 150 && total <= 199) {
            total = 10;
        } else if (total >= 100 && total <= 149) {
            total = 12;
        } else if (total >= 50 && total <= 99) {
            total = 14;
        } else if (total >= 1 && total <= 49) {
            total = 17;
        }
        return total;
    }
}
