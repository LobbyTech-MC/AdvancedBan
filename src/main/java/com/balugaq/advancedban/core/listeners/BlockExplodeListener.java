package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class BlockExplodeListener implements Listener {
    public static final EventType TYPE = EventType.BLOCK_EXPLODE;

    public static boolean presetPredications(@NotNull BlockExplodeEvent event, @NotNull EventPriority eventPriority) {
        Set<Block> toRemove = new HashSet<>();
        for (Block block : event.blockList()) {
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
                event.blockList().remove(block);
            }
            return true;
        } else {
            return false;
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull BlockExplodeEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("BlockExplodeListener#onLowest(): Cancelled. 110");
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull BlockExplodeEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("BlockExplodeListener#onLow(): Cancelled. 111");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull BlockExplodeEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("BlockExplodeListener#onNormal(): Cancelled. 112");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull BlockExplodeEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("BlockExplodeListener#onHigh(): Cancelled. 113");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull BlockExplodeEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("BlockExplodeListener#onHighest(): Cancelled. 114");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull BlockExplodeEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("BlockExplodeListener#onMonitor(): Cancelled. 115");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
