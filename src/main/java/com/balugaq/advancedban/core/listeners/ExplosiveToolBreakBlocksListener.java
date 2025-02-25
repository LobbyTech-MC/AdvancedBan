package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import io.github.thebusybiscuit.slimefun4.api.events.ExplosiveToolBreakBlocksEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ExplosiveToolBreakBlocksListener implements Listener {
    public static final EventType TYPE = EventType.EXPLOSIVE_TOOL_BREAK_BLOCKS;

    public static boolean presetPredications(@NotNull ExplosiveToolBreakBlocksEvent event, @NotNull EventPriority eventPriority) {
        SlimefunItem slimefunItem2 = BlockStorage.check(event.getPrimaryBlock());
        if (slimefunItem2 != null) {
            if (Predications.getPriority(slimefunItem2.getId(), TYPE) == eventPriority) {
                return true;
            }
        }
        
        Set<Block> toRemove = new HashSet<>();
        for (Block block : event.getAdditionalBlocks()) {
            SlimefunItem slimefunItem = BlockStorage.check(block);
            if (slimefunItem == null) {
                continue;
            }
            
            if (Predications.getPriority(slimefunItem.getId(), TYPE) == eventPriority) {
                toRemove.add(block);
            }
        }
        
        if (!toRemove.isEmpty()) {
            for (Block block : toRemove) {
                event.getAdditionalBlocks().remove(block);
            }
            return true;
        } else {
            return false;
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull ExplosiveToolBreakBlocksEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("ExplosiveToolBreakBlocksListener#onLowest(): Cancelled. 130");
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull ExplosiveToolBreakBlocksEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("ExplosiveToolBreakBlocksListener#onLow(): Cancelled. 131");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull ExplosiveToolBreakBlocksEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("ExplosiveToolBreakBlocksListener#onNormal(): Cancelled. 132");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull ExplosiveToolBreakBlocksEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("ExplosiveToolBreakBlocksListener#onHigh(): Cancelled. 133");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull ExplosiveToolBreakBlocksEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("ExplosiveToolBreakBlocksListener#onHighest(): Cancelled. 134");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull ExplosiveToolBreakBlocksEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("ExplosiveToolBreakBlocksListener#onMonitor(): Cancelled. 135");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
