package com.balugaq.advancedban.api.events;

import com.balugaq.advancedban.api.Debug;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class FakePlayerInteractEvent extends PlayerInteractEvent implements FakeEvent {
    private final @Nullable String slimefunId;

    public FakePlayerInteractEvent(@NotNull PlayerInteractEvent event) {
        this(event.getPlayer(), event.getAction(), event.getItem(), event.getClickedBlock(), event.getBlockFace(), event.getHand());
    }

    public FakePlayerInteractEvent(@NotNull Player player, @NotNull Action action, @Nullable ItemStack itemStack, @Nullable Block clickedBlock, @NotNull BlockFace blockFace, @NotNull EquipmentSlot hand) {
        super(player, action, itemStack, clickedBlock, blockFace, hand);
        SlimefunItem item = SlimefunItem.getByItem(itemStack);
        if (item != null) {
            slimefunId = item.getId();
        } else {
            slimefunId = null;
        }
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        Debug.debug("FakePlayerInteractEvent#setCancelled() : Called 45");
        Debug.stackTraceManually();
    }

    @Override
    public void setUseInteractedBlock(@NotNull Event.Result useInteractedBlock) {
        super.setUseInteractedBlock(useInteractedBlock);
        Debug.debug("FakePlayerInteractEvent#setUseInteractedBlock() : Called 56");
        Debug.stackTraceManually();
    }

    @Override
    public void setUseItemInHand(@NotNull Event.Result useItemInHand) {
        super.setUseItemInHand(useItemInHand);
        Debug.debug("FakePlayerInteractEvent#setUseItemInHand() : Called 57");
        Debug.stackTraceManually();
    }

}
