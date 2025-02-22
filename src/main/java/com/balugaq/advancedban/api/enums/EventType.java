package com.balugaq.advancedban.api.enums;

import com.balugaq.advancedban.api.utils.Lang;
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
    // Not a real event, but used for cancelling machine crafting
    MACHINE_CRAFT("machine-craft", Lang.getMessage("events.cancel-machine-craft")),
    ;

    private final @NotNull String key;
    private final @NotNull String message;

    EventType(@NotNull String key, @NotNull String message) {
        this.key = key;
        this.message = message;
    }
}
