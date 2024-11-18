package Habilidades.Gerenciamento;

import org.bukkit.entity.Player;

import static Habilidades.Gerenciamento.Config.Database.*;

public class Habilidades {

    public enum Habilidade {
        MINERADOR("Minerador"),
        PESCADOR("Pescador"),
        COLETOR("Coletor"),
        CAÇADOR("Caçador"),
        LENHADOR("Lenhador"),
        CORREDOR("Corredor"),
        PLANADOR("Planador"),
        NADADOR("Nadador"),
        CACADOR_DE_DRAGOES("Caçador de Dragões"),
        FORJADOR("Forjador");

        private final String displayName;

        Habilidade(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public static String getHabilidadeNome(Habilidade habilidade) {
        return habilidade.getDisplayName();
    }

    public static int getNecessario(Player jogador, String habilidade) {
        int nivel = getNivel(habilidade, jogador.getName());
        return nivel == 0 ? 25 : 50 * nivel;
    }

    public static boolean verificarExperiencia(Player jogador, String habilidade) {
        return getExperiencia(habilidade, jogador.getName()) > getNecessario(jogador, habilidade);
    }

}
