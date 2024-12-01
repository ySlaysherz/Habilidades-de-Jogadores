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
        if (monstro.getKiller() == null) return;
        Player jogador = monstro.getKiller();
        if (getMonstrosSecao().contains(monstro.getType().toString())) {
            addExperiencia(getHabilidadeNome(Habilidade.CACADOR), jogador, 1);
        }
    }
}
