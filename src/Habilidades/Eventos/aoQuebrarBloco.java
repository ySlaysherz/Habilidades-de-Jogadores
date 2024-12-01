package Habilidades.Eventos;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


import static Habilidades.Gerenciamento.Config.Configuracao.*;

import static Habilidades.Gerenciamento.Mensagens.*;
import static Habilidades.Gerenciamento.Config.Database.*;
import static Habilidades.Gerenciamento.Habilidades.*;

public class aoQuebrarBloco implements Listener {

    @EventHandler
    public static void ColetarMinerio(BlockBreakEvent evento) {
        Block bloco = evento.getBlock();
        Material tipo = bloco.getType();
        Player jogador = evento.getPlayer();

        if (!getBlocosTipos().contains(tipo)) return;
        if (evento.isCancelled()) return;
        addExperiencia(getHabilidadeNome(Habilidade.MINERADOR), jogador, getExperienciaBloco(tipo));
    }
}

