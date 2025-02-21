package com.balugaq.advancedban.api.events;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
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
}
