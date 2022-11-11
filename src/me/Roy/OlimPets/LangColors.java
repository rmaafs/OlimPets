package me.Roy.OlimPets;

import static me.Roy.OlimPets.Extra.colores;
import static me.Roy.OlimPets.Extra.handItems;
import static me.Roy.OlimPets.Extra.sombreros;
import static me.Roy.OlimPets.Lang.ccolors;
import static me.Roy.OlimPets.Lang.chanditems;
import static me.Roy.OlimPets.Lang.chats;
import me.Roy.OlimPets.Objetos.Colores;
import me.Roy.OlimPets.Objetos.Hand;
import me.Roy.OlimPets.Objetos.Hat;

public class LangColors {

    public static int guiColorSize;

    public static void setLangColors() {
        guiColorSize = ccolors.getInt("guisize");
    }

    public static void loadColors() {
        colores.clear();
        if (ccolors.contains("colors")) {
            for (String c : ccolors.getConfigurationSection("colors").getKeys(false)) {
                colores.add(new Colores(c));
            }
            for (String c : ccolors.getConfigurationSection("minerals").getKeys(false)) {
                colores.add(new Colores(c, true));
            }
        }
    }
    
    public static void loadHats(){
        sombreros.clear();
        if (chats.contains("hats")) {
            for (String c : chats.getConfigurationSection("hats").getKeys(false)) {
                sombreros.add(new Hat(c));
            }
        }
    }
    
    public static void loadHandItems(){
        handItems.clear();
        if (chanditems.contains("items")) {
            for (String c : chanditems.getConfigurationSection("items").getKeys(false)) {
                handItems.add(new Hand(c));
            }
        }
    }
}
