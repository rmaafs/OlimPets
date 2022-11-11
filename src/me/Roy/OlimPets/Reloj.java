package me.Roy.OlimPets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverOcupadas;
import static me.Roy.OlimPets.Games.Antilover.AntiloverControl.antiloverStarting;
import me.Roy.OlimPets.Games.Antilover.AntiloverMap;
import static me.Roy.OlimPets.Games.Count.CountControl.countOcupadas;
import static me.Roy.OlimPets.Games.Count.CountControl.countStarting;
import static me.Roy.OlimPets.Games.Count.CountControl.countZombies;
import me.Roy.OlimPets.Games.Count.CountMap;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpOcupadas;
import static me.Roy.OlimPets.Games.Jump.JumpControl.jumpStarting;
import me.Roy.OlimPets.Games.Jump.JumpMap;
import static me.Roy.OlimPets.Games.Run.RunControl.runStarting;
import me.Roy.OlimPets.Games.Run.RunMap;
import static me.Roy.OlimPets.Lang.ORB_PICKUP;
import static me.Roy.OlimPets.Lang.antiloverStartingMsg;
import static me.Roy.OlimPets.Lang.countStartingMsg;
import static me.Roy.OlimPets.Lang.runStartingMsg;
import static me.Roy.OlimPets.Lang.jumpStartingMsg;
import static me.Roy.OlimPets.Main.plugin;
import me.Roy.OlimPets.Objetos.CountZombie;

public class Reloj {

    public static void tiempo() {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin,
                new Runnable() {
                    int sec = 0;

                    @Override
                    public void run() {
                        sec++;
                        if (sec == 10) {//MEDIO SEGUNDO
                            agregarCuerdas();
                        }

                        if (sec >= 20) {//SEGUNDO
                            sec = 0;
                            runMapStaring();
                            jumpMapStaring();
                            agregarCuerdas();
                            countMapStaring();
                            countRemoverTime();
                            antiloverMapStaring();
                        }
                        moverCuerdaJump();
                        countRoundZombies();
                        moverCountZombies();
                        moverAntiloverZombies();
                    }
                }, 0, 1);
    }

    public static void runMapStaring() {
        List<RunMap> removeRun = new ArrayList<>();
        for (RunMap map : runStarting) {
            if (map.getPretime() < 1) {
                map.setPregame(false);
                map.iniciarJuego();
                removeRun.add(map);
            } else {
                if (map.getPretime() == 4) {
                    map.aparecerZombies();
                }
                if (map.getPretime() == 3) {
                    map.teleportarSpawn();
                    map.ocultarPlayers();
                }
                map.msg(runStartingMsg.replaceAll("<number>", "" + map.getPretime()));
                map.song(ORB_PICKUP);
                map.removePretime();
            }
        }
        for (RunMap map : removeRun) {
            runStarting.remove(map);
        }
        removeRun.clear();
    }

    public static void jumpMapStaring() {
        List<JumpMap> removeRun = new ArrayList<>();
        for (JumpMap map : jumpStarting) {
            if (map.getPretime() < 1) {
                map.iniciarJuego();
                removeRun.add(map);
            } else {
                if (map.getPretime() == 3) {
                    map.teleportarSpawn();
                    map.ocultarPlayers();
                }
                map.msg(jumpStartingMsg.replaceAll("<number>", "" + map.getPretime()));
                map.song(ORB_PICKUP);
                map.removePretime();
            }
        }
        for (JumpMap map : removeRun) {
            jumpStarting.remove(map);
        }
        removeRun.clear();
    }

    public static void moverCuerdaJump() {
//        List<Cuerda> removeJump = new ArrayList<>();
//        for (Cuerda c : cuerdas) {
//            if (c.moverCuerda()) {
//                c.removerCuerda();
//                removeJump.add(c);
//            }
//        }
//        for (Cuerda c : removeJump) {
//            cuerdas.remove(c);
//        }
        List<JumpMap> removerOcupadas = new ArrayList<>();
        for (JumpMap c: jumpOcupadas){
            c.moverCuerdas();
            if (c.isAcabada()){
                removerOcupadas.add(c);
            }
        }
        jumpOcupadas.removeAll(removerOcupadas);
    }

    public static void agregarCuerdas() {
        for (JumpMap map : jumpOcupadas) {
            if (!map.isPreGame()) {
                map.ponerCuerda();
            }
        }
    }

    public static void countMapStaring() {
        List<CountMap> removeRun = new ArrayList<>();
        for (CountMap map : countStarting) {
            if (map.getPretime() < 1) {
                map.iniciarJuego();
                removeRun.add(map);
            } else {
                if (map.getPretime() == 3) {
                    map.teleportarSpawn();
                    map.ocultarPlayers();
                }
                map.msg(countStartingMsg.replaceAll("<number>", "" + map.getPretime()));
                map.song(ORB_PICKUP);
                map.removePretime();
            }
        }
        for (CountMap map : removeRun) {
            countStarting.remove(map);
        }
        removeRun.clear();
    }

    public static void countRemoverTime() {
        for (CountMap m : countOcupadas) {
            if (!m.isPreGame()) {
                m.removeTime();
            }
        }
    }

    public static void countRoundZombies() {
        List<CountMap> terminadas = new ArrayList<>();
        Random r = new Random();
        int level = r.nextInt(50);
        if (level == 0) {
            level = 1;
        }
        for (CountMap m : countOcupadas) {
            if (!m.isPreGame()) {
                if (m.getTime() >= 15) {
                    if (level == 1) {
                        m.ponerZombie();
                    }
                } else if (m.getTime() > 0 && m.getTime() < 10) {
                    if (level == 6 || level == 9) {
                        m.ponerZombie();
                    }
                } else if (m.getTime() <= -5) {
                    terminadas.add(m);
                }
            }
        }

        for (CountMap m : terminadas) {
            m.presentar();
        }
    }

    public static void moverCountZombies() {
        List<CountZombie> removeJump = new ArrayList<>();
        for (CountZombie c : countZombies) {
            if (!c.moverZombie()) {
                removeJump.add(c);
            }
        }
        for (CountZombie c : removeJump) {
            countZombies.remove(c);
        }
    }

    public static void antiloverMapStaring() {
        List<AntiloverMap> removeRun = new ArrayList<>();
        for (AntiloverMap map : antiloverStarting) {
            if (map.getPretime() < 1) {
                map.iniciarJuego();
                removeRun.add(map);
            } else {
                if (map.getPretime() == 4) {
                    map.ponerZombies();
                }
                if (map.getPretime() == 3) {
                    map.teleportarSpawn();
                    map.ocultarPlayers();
                }
                map.msg(antiloverStartingMsg.replaceAll("<number>", "" + map.getPretime()));
                map.song(ORB_PICKUP);
                map.removePretime();
            }
        }
        for (AntiloverMap map : removeRun) {
            antiloverStarting.remove(map);
        }
    }

    public static void moverAntiloverZombies() {
        List<AntiloverMap> antiloverTerminadas = new ArrayList<>();
        Random r = new Random();
        int level = r.nextInt(5);
        if (level == 0) {
            level = 1;
        }
        for (AntiloverMap c : antiloverOcupadas) {
            if (c.isTerminada()) {
                antiloverTerminadas.add(c);
            } else {
                if (!c.isPreGame()) {
                    c.moverZombies();
                    if (level == 1) {
                        c.ponerZombie();
                    }
                }
            }
        }
        for (AntiloverMap c : antiloverTerminadas) {
            c.setTerminada(false);
            antiloverOcupadas.remove(c);
        }
    }
}
