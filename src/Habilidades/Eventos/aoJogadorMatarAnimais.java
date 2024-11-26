package Habilidades.Eventos;

import Habilidades.Eventos.Customizados.PlayerKillAnimalEvent;
import Habilidades.Gerenciamento.Habilidades;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static Habilidades.Gerenciamento.Config.Configuracao.*;
import static Habilidades.Gerenciamento.Config.Database.*;
import static Habilidades.Gerenciamento.Config.Database.getNivel;
import static Habilidades.Gerenciamento.Habilidades.getHabilidadeNome;
import static Habilidades.Gerenciamento.Habilidades.*;
import static Habilidades.Gerenciamento.Mensagens.LevelUp;


public final class aoJogadorMatarAnimais implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        event.getEntity();
        if (event.getEntity().getKiller() == null) return;

        Player jogador = event.getEntity().getKiller();
        Entity animal = event.getEntity();

        // Disparar o evento customizado
        PlayerKillAnimalEvent playerKillAnimalEvent = new PlayerKillAnimalEvent(jogador, animal);
        Bukkit.getPluginManager().callEvent(playerKillAnimalEvent);
    }

    @EventHandler
    public void aoJogadorMatarAnimal(PlayerKillAnimalEvent evento) {
        Player jogador = evento.getPlayer();
        Entity animal = evento.getAnimal();
        EntityType tipo = animal.getType();
        if (getAnimaisSecao().contains(animal.getType().toString())) {
            jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f);
            addExperiencia(getHabilidadeNome(Habilidade.CACADOR), jogador.getName(), getExperienciaAnimal(tipo));
            if (!verificarExperiencia(jogador, getHabilidadeNome(Habilidade.CACADOR))) {
                return;
            }
            jogador.playSound(jogador.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 0.5f);
            addNivel(getHabilidadeNome(Habilidade.CACADOR), jogador.getName(), 1);
            jogador.sendMessage(LevelUp(jogador, getHabilidadeNome(Habilidade.CACADOR), getNivel(getHabilidadeNome(Habilidade.CACADOR), jogador.getName())));
        }
    }
}
