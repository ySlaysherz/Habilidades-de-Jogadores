package Habilidades.Eventos;

import Habilidades.Gerenciamento.Habilidades;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static Habilidades.Gerenciamento.Config.Configuracao.*;
import static Habilidades.Gerenciamento.Config.Database.*;
import static Habilidades.Gerenciamento.Habilidades.*;
import static Habilidades.Gerenciamento.Mensagens.*;

public class aoJogadorMatarMonstros implements Listener {

    @EventHandler
    public void aoMatar(EntityDeathEvent evento) {
        LivingEntity monstro = evento.getEntity();
        if (monstro.getKiller() != null) {
            Player jogador = monstro.getKiller();

            if (getMonstrosSecao().contains(monstro.getType().toString())) {
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f);
                addExperiencia(getHabilidadeNome(Habilidade.CACADOR), jogador.getName(), 1);
                if (!verificarExperiencia(jogador, getHabilidadeNome(Habilidade.CACADOR))) {
                    return;
                }
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 0.5f);
                addNivel(getHabilidadeNome(Habilidade.CACADOR), jogador.getName(), 1);
                jogador.sendMessage(LevelUp(jogador, getHabilidadeNome(Habilidade.CACADOR), getNivel(getHabilidadeNome(Habilidade.CACADOR), jogador.getName())));
            }
        }
    }

}
