package Habilidades.Eventos.Customizados;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class PlayerKillAnimalEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Player Player;
    private final Entity Animal;

    public Player getPlayer() {
        return Player;
    }

    public Entity getAnimal() {
        return Animal;
    }

    public PlayerKillAnimalEvent(Player player, Entity animal) {
        this.Player = player;
        this.Animal = animal;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


}
