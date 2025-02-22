package com.balugaq.advancedban.api.events;

import com.balugaq.advancedban.api.Debug;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class FakePlayerDropItemEvent extends PlayerDropItemEvent implements FakeEvent {
    private final @Nullable String slimefunId;

    public FakePlayerDropItemEvent(@NotNull PlayerDropItemEvent event) {
        this(event.getPlayer(), event.getItemDrop());
    }

    public FakePlayerDropItemEvent(@NotNull Player player, @NotNull Item item) {
        super(player, item);
        SlimefunItem sfItem = SlimefunItem.getByItem(item.getItemStack());
        if (sfItem != null) {
            slimefunId = sfItem.getId();
        } else {
            slimefunId = null;
        }
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        Debug.debug("FakePlayerDropItemEvent#setCancelled() : Called 46");
        Debug.stackTraceManually();
    }
}
