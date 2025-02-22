package com.balugaq.advancedban.api.events;

import com.balugaq.advancedban.api.Debug;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class FakeBlockPlaceEvent extends BlockPlaceEvent implements FakeEvent {
    private final @Nullable String slimefunId;

    public FakeBlockPlaceEvent(@NotNull BlockPlaceEvent event) {
        this(event.getBlock(), event.getBlockReplacedState(), event.getBlockAgainst(), event.getItemInHand(), event.getPlayer(), event.canBuild(), event.getHand());
    }

    public FakeBlockPlaceEvent(@NotNull Block placedBlock, @NotNull BlockState replacedBlockState, @NotNull Block placedAgainst, @NotNull ItemStack itemInHand, @NotNull Player thePlayer, boolean canBuild, @NotNull EquipmentSlot hand) {
        super(placedBlock, replacedBlockState, placedAgainst, itemInHand, thePlayer, canBuild, hand);
        SlimefunItem slimefunItem = SlimefunItem.getByItem(getItemInHand());
        if (slimefunItem != null) {
            slimefunId = slimefunItem.getId();
        } else {
            slimefunId = null;
        }
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        Debug.debug("FakeBlockPlaceEvent#setCancelled() : Called 49");
        Debug.stackTraceManually();
    }
}
