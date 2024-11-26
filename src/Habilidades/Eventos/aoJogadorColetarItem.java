package Habilidades.Eventos;

import Habilidades.Gerenciamento.Habilidades;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import static Habilidades.Gerenciamento.Config.Configuracao.*;
import static Habilidades.Gerenciamento.Config.Database.*;
import static Habilidades.Gerenciamento.Habilidades.*;
import static Habilidades.Gerenciamento.Mensagens.*;

public class aoJogadorColetarItem implements Listener {

    @EventHandler
    public static void aoColetar(PlayerPickupItemEvent evento) {
        Player jogador = evento.getPlayer();
        Item item = evento.getItem();
        ItemStack coletado = item.getItemStack();
        for (Material coletaveis : getItensColetaveis())
            if (coletado.getType() == coletaveis) {
                addExperiencia(getHabilidadeNome(Habilidades.Habilidade.COLETOR), jogador.getName(), getExperienciaColetaveis(coletado.getType()));
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f);

                if (!verificarExperiencia(jogador, getHabilidadeNome(Habilidade.MINERADOR))) {
                    return;
                }
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 0.5f);
                addNivel(getHabilidadeNome(Habilidade.COLETOR), jogador.getName(), 1);
                jogador.sendMessage(LevelUp(jogador, getHabilidadeNome(Habilidade.MINERADOR), getNivel(getHabilidadeNome(Habilidade.MINERADOR), jogador.getName())));
            }
    }
}
