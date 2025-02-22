package com.balugaq.advancedban.api.events;

import com.balugaq.advancedban.api.Debug;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.BlockListener;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class FakeBlockBreakEvent extends BlockBreakEvent implements FakeEvent {
    private final @Nullable String slimefunId;

    public FakeBlockBreakEvent(@NotNull BlockBreakEvent event) {
        this(event.getBlock(), event.getPlayer());
    }

    public FakeBlockBreakEvent(@NotNull Block block, @NotNull Player player) {
        super(block, player);
        SlimefunItem item = BlockStorage.check(block.getLocation());
        if (item != null) {
            slimefunId = item.getId();
        } else {
            slimefunId = null;
        }
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        Debug.debug("FakeBlockBreakEvent#setCancelled() : Called 50");
        Debug.stackTraceManually();
    }
}
