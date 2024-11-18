package Habilidades.Gerenciamento;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mensagens {
    private static File arquivo;
    private static FileConfiguration config;
    private static Plugin plugin;

    public static enum Opcoes {
        Level_Up, Level_Down, Level_Give, Level_Set, Experiencia_Add, Experiencia_Set, Experiencia_Down
    }

    public Mensagens(Plugin pl) {
        plugin = pl;
        if (arquivo == null) {
            arquivo = new File(plugin.getDataFolder(), "mensagens.yml");

            if (!arquivo.exists()) {
                CreateDefaultConfig();
            }
        }
    }

    public static FileConfiguration getConfig() {
        if (config == null) {
            config = YamlConfiguration.loadConfiguration(arquivo);
        }
        return config;
    }

    public File getArquivo() {
        return arquivo;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void CreateDefaultConfig() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
            arquivo = new File(plugin.getDataFolder(), "mensagens.yml");
        }
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(arquivo);
        getConfig().options().copyDefaults(true);
    }

    public static void saveConfig() {
        try {
            config.save(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationSection getMensagensSecao() {
        ConfigurationSection Secao = getConfig().getConfigurationSection("Mensagens");
        if (Secao == null) {
            Secao = getConfig().createSection("Mensagens");
        }
        return Secao;
    }


    public static String LevelUp(Player jogador, String habilidade, int nivel) {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("%habilidade%", habilidade);
        placeholders.put("%jogador%", jogador.getName());
        placeholders.put("%nivel%", String.valueOf(nivel));
        placeholders.put("&", "§");
        return formatarMensagem("Level_Up", placeholders);
    }

    public static String LevelDown(Player jogador, String habilidade, int nivel, int niveis) {


        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("%habilidade%", habilidade);
        placeholders.put("%niveis_diminuidos%", String.valueOf(niveis));
        placeholders.put("%nivel%", String.valueOf(nivel));
        placeholders.put("&", "§");
        return formatarMensagem("Level_Give", placeholders);
    }

    public static String LevelGive(Player jogador, String habilidade, int nivel) {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("%habilidade%", habilidade);
        placeholders.put("%jogador%", jogador.getName());
        placeholders.put("%nivel%", String.valueOf(nivel));
        placeholders.put("&", "§");
        return formatarMensagem("Level_Give", placeholders);
    }

    public static String LevelSet(Player jogador, String habilidade, int nivel) {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("%habilidade%", habilidade);
        placeholders.put("%jogador%", jogador.getName());
        placeholders.put("%nivel%", String.valueOf(nivel));
        placeholders.put("&", "§");
        return formatarMensagem("Level_Set", placeholders);
    }

    public static String ExperienciaAdd(Player jogador, String habilidade, int experiencia) {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("%habilidade%", habilidade);
        placeholders.put("%jogador%", jogador.getName());
        placeholders.put("%experiencia%", String.valueOf(experiencia));
        placeholders.put("&", "§");
        return formatarMensagem("Experiencia_Add", placeholders);
    }

    public static String Experiencia_Set(Player jogador, String habilidade, int experiencia) {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("%habilidade%", habilidade);
        placeholders.put("%jogador%", jogador.getName());
        placeholders.put("%nivel%", String.valueOf(experiencia));
        placeholders.put("&", "§");
        return formatarMensagem("Experiencia_Set", placeholders);
    }

    public static String ExperienciaDown(Player jogador, String habilidade, int experiencia) {

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("%habilidade%", habilidade);
        placeholders.put("%jogador%", jogador.getName());
        placeholders.put("%experiencia%", String.valueOf(experiencia));
        placeholders.put("&", "§");
        return formatarMensagem("Experiencia_Down", placeholders);
    }

    private static String formatarMensagem(String chaveMensagem, Map<String, String> placeholders) {
        String mensagem = getMensagensSecao().getString(chaveMensagem);
        for (Map.Entry<String, String> placeholder : placeholders.entrySet()) {
            mensagem = mensagem.replace(placeholder.getKey(), placeholder.getValue());
        }
        return mensagem;
    }

}
