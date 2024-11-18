package Habilidades.Gerenciamento.Config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Database {
    private static File arquivo;
    private static FileConfiguration config;
    private static Plugin plugin;

    public Database(Plugin pl) {
        plugin = pl;
        if (arquivo == null) {
            arquivo = new File(plugin.getDataFolder(), "database.yml");

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
            arquivo = new File(plugin.getDataFolder(), "database.yml");
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

    public static void reloadConfig() {
        try {
            config.load(arquivo);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationSection getJogadoresSecao() {
        ConfigurationSection Secao = getConfig().getConfigurationSection("Jogadores");
        if (Secao == null) {
            Secao = getConfig().createSection("Jogadores");
        }
        return Secao;
    }

    public static ConfigurationSection getJogadorSecao(String jogador) {
        ConfigurationSection Secao = getJogadoresSecao().getConfigurationSection(jogador);
        if (Secao == null) {
            Secao = getJogadoresSecao().createSection(jogador);
        }
        return Secao;
    }

    public static ConfigurationSection getHabilidadesSecao(String jogador) {
        ConfigurationSection Secao = getJogadorSecao(jogador).getConfigurationSection("Habilidades");
        if (Secao == null) {
            Secao = getJogadorSecao(jogador).createSection("Habilidades");
        }
        return Secao;
    }

    public static ConfigurationSection getHabilidadeSecao(String habilidade, String jogador) {
        ConfigurationSection Secao = getHabilidadesSecao(jogador).getConfigurationSection(habilidade);
        if (Secao == null) {
            Secao = getHabilidadesSecao(jogador).createSection(habilidade);
        }
        return Secao;
    }

    public static void setNivel(String habilidade, String jogador, int nivel) {
        getHabilidadeSecao(habilidade, jogador).set("Nivel", nivel);
        saveConfig();
        reloadConfig();
    }

    public static int getNivel(String habilidade, String jogador) {
        return getHabilidadeSecao(habilidade, jogador).getInt("Nivel");
    }

    public static void setExperiencia(String habilidade, String jogador, int experiencia) {
        getHabilidadeSecao(habilidade, jogador).set("Experiencia", experiencia);
        saveConfig();
        reloadConfig();
    }

    public static int getExperiencia(String habilidade, String jogador) {
        return getHabilidadeSecao(habilidade, jogador).getInt("Experiencia", 0);
    }
}
