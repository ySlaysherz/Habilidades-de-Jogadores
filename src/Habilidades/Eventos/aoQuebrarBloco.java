package Habilidades.Eventos;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


import static Habilidades.Blocos.Configuracao.*;

import static Habilidades.Gerenciamento.Mensagens.*;
import static Habilidades.Gerenciamento.Config.Database.*;
import static Habilidades.Gerenciamento.Habilidades.*;

public class aoQuebrarBloco implements Listener {

    @EventHandler
    public static void ColetarMinerio(BlockBreakEvent evento) {
        Block bloco = evento.getBlock();
        Material tipo = bloco.getType();
        Player jogador = evento.getPlayer();

        if (!getBlocosTipos().contains(tipo)) {
            return;
        }

        jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f);
        setExperiencia(getHabilidadeNome(Habilidade.MINERADOR), jogador.getName(), getExperienciaBloco(tipo) + getExperiencia(getHabilidadeNome(Habilidade.MINERADOR), jogador.getName()));

        if (verificarExperiencia(jogador, getHabilidadeNome(Habilidade.MINERADOR))) {
            jogador.playSound(jogador.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 0.5f);
            setNivel(getHabilidadeNome(Habilidade.MINERADOR), jogador.getName(), getNivel(getHabilidadeNome(Habilidade.MINERADOR), jogador.getName()) + 1);
            jogador.sendMessage(LevelUp(jogador, getHabilidadeNome(Habilidade.MINERADOR), getNivel(getHabilidadeNome(Habilidade.MINERADOR), jogador.getName())));
        }
    }
}

