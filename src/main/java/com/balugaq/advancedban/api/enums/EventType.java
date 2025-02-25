package com.balugaq.advancedban.api.enums;

import com.balugaq.advancedban.api.utils.Lang;
import com.balugaq.advancedban.core.managers.PredicationsLoader;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * This enum is used to ban the specific event for the {@link SlimefunItem}
 *
 * @author balugaq
 * @see PredicationsLoader
 * @since 1.0
 */
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
    ENTITY_EXPLODE("entity-explode", Lang.getMessage("events.cancel-entity-explode")),
    BLOCK_EXPLODE("block-explode", Lang.getMessage("events.cancel-block-explode")),
    EXPLOSIVE_TOOL_BREAK_BLOCKS("explosive-tool-break-blocks", Lang.getMessage("events.cancel-explosive-tool-break-blocks")),
    BLOCK_BURN("block-burn", Lang.getMessage("events.cancel-block-burn")),
    ENTITY_CHANGE_BLOCK("entity-change-block", Lang.getMessage("events.cancel-entity-change-block")),
    ;

    private final @NotNull String key;
    private final @NotNull String message;

    EventType(@NotNull String key, @NotNull String message) {
        this.key = key;
        this.message = message;
    }
}
