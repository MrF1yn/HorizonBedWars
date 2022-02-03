package com.andrei1058.bedwars.api.events.server;

import com.andrei1058.bedwars.api.arena.IArena;
import com.google.gson.JsonObject;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LobbyMessageEvent extends Event {

        private static final HandlerList HANDLERS = new HandlerList();

        private JsonObject Message;

        /**
         * Called when the server receives a socket message from lobby.
         */
        public LobbyMessageEvent(JsonObject message) {
            this.Message = message;
        }

        /**
         * Get the message.
         */
        public JsonObject getMessage() {
            return Message;
        }

        @Override
        public HandlerList getHandlers() {
            return HANDLERS;
        }


        public static HandlerList getHandlerList() {
            return HANDLERS;
        }


}
