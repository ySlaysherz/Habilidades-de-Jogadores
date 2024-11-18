package Habilidades.Blocos;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Configuracao {
    private static File arquivo;
    private static FileConfiguration config;
    private static Plugin plugin;

    public Configuracao(Plugin pl) {
        plugin = pl;
        if (arquivo == null) {
            arquivo = new File(plugin.getDataFolder(), "config.yml");

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
            arquivo = new File(plugin.getDataFolder(), "config.yml");
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

    public static ConfigurationSection getBlocosSecao() {
        ConfigurationSection Secao = getConfig().getConfigurationSection("Blocos");
        if (Secao == null) {
            Secao = getConfig().createSection("Blocos");
        }
        return Secao;
    }

    public static List<Material> getBlocosTipos() {
        List<Material> tipos = new ArrayList<>();
        for (String tipo : getBlocosSecao().getKeys(false)) {
            tipos.add(Material.matchMaterial(tipo));
        }
        return tipos;
    }

    public static int getExperienciaBloco(Material tipo) {
        return getBlocosSecao().getInt(tipo.toString());
    }
}
