package Principal;

import Habilidades.Eventos.*;
import Habilidades.Eventos.Customizados.PlayerKillAnimalEvent;
import Habilidades.Gerenciamento.Config.Configuracao;
import Habilidades.Gerenciamento.Config.Database;
import Habilidades.Gerenciamento.Mensagens;
import Jogadores.aoJogadorEntrar;
import Jogadores.aoJogadorSair;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin {

    public static Main main;
    public static Random Aleatorio = new Random();

    public Database Dtb = new Database(this);
    public Configuracao Config = new Configuracao(this);
    public Mensagens Msgs = new Mensagens(this);

    public void onLoad() {

    }

    public void onEnable() {

        main = this;
        saveDefaultConfig();
        // Mensagem abaixo será enviada ao Console quando o Plugin for Iniciado sem
        // erros.
        Bukkit.getConsoleSender()
                .sendMessage(ChatColor.GOLD + "Habilidades de Jogadores " + ChatColor.GREEN + "(V: "
                        + getDescription().getVersion() + ") - " + ChatColor.WHITE + "Desenvolvedor: " + ChatColor.BLUE
                        + "ySlaysherz_");

        // Registra as Classes que possuiem Eventos Listener
        Bukkit.getServer().getPluginManager().registerEvents(new aoQuebrarBloco(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new aoJogadorColetarItem(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new aoJogadorCorrer(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new aoJogadorMatarMonstros(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new aoJogadorMatarAnimais(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new aoJogadorEntrar(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new aoJogadorSair(), this);

        // Registra as Classes que possuem Comandos
        //        getCommand("").setExecutor(new classe());
    }

    public void onDisable() {
        // Mensagem abaixo será enviada ao Console quando o Plugin for desligado.
        Bukkit.getConsoleSender()
                .sendMessage(ChatColor.GOLD + "Habilidades de Jogadores" + ChatColor.WHITE + " Desligado com sucesso.");
    }

    public static Main getInstance() {
        return main;
    }

    public static int getIntAleatrio(int max) {
        return Aleatorio.nextInt(max);
    }

    public static int getIntAleatrioMinMax(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("O valor mínimo não pode ser maior que o valor máximo.");
        }
        return Aleatorio.nextInt(max - min + 1) + min;
    }

    public static boolean getChance(int porcentagem) {
        return Aleatorio.nextInt(100) <= porcentagem;
    }

    public static boolean get1em(int probabilidade) {
        return probabilidade == getIntAleatrioMinMax(1, probabilidade);
    }
}