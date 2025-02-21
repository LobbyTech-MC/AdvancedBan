package com.balugaq.advancedban.api.events;

import com.balugaq.advancedban.api.Lang;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum EventType {
    BLOCK_BREAK("block-break", Lang.getMessage("events.cancel-block-break")),
    BLOCK_PLACE("block-place", Lang.getMessage("events.cancel-block-place")),
    CRAFT_ITEM("craft-item", Lang.getMessage("events.cancel-craft-item")),
    MULTI_BLOCK_CRAFT("multi-block-craft", Lang.getMessage("events.cancel-multi-block-craft")),
    PLAYER_DROP_ITEM("player-drop-item", Lang.getMessage("events.cancel-player-drop-item")),
    PLAYER_INTERACT("player-interact", Lang.getMessage("events.cancel-player-interact")),
    ;

    private final String key;
    private final String message;
    EventType(@NotNull String key, @NotNull String message) {
        this.key = key;
        this.message = message;
    }
}
