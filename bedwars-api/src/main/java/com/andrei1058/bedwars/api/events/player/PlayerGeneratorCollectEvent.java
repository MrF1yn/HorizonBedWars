package com.andrei1058.bedwars.api.events.player;

import com.andrei1058.bedwars.api.arena.IArena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerGeneratorCollectEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private Player player;
    private ItemStack itemStack;
    private IArena arena;
    private boolean cancelled = false;

    /**
     * Triggered when players collect from generators
     */
    public PlayerGeneratorCollectEvent(Player player, ItemStack itemStack, IArena arena) {
        this.player = player;
        this.itemStack = itemStack;
        this.arena = arena;
    }

    public IArena getArena() {
        return arena;
    }

    /**
     * Get the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the itemStack involved
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Cancel this event
     */
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}