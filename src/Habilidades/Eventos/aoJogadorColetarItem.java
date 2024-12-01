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
                addExperiencia(getHabilidadeNome(Habilidades.Habilidade.COLETOR), jogador, getExperienciaColetaveis(coletado.getType()));
            }
    }
}
