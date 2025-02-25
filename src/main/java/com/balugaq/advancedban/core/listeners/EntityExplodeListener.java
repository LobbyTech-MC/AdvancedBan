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
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class EntityExplodeListener implements Listener {
    public static final EventType TYPE = EventType.ENTITY_EXPLODE;

    public static boolean presetPredications(@NotNull EntityExplodeEvent event, @NotNull EventPriority eventPriority) {
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
    public void onLowest(@NotNull EntityExplodeEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("EntityExplodeListener#onLowest(): Cancelled. 100");
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull EntityExplodeEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("EntityExplodeListener#onLow(): Cancelled. 101");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull EntityExplodeEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("EntityExplodeListener#onNormal(): Cancelled. 102");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull EntityExplodeEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("EntityExplodeListener#onHigh(): Cancelled. 103");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull EntityExplodeEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("EntityExplodeListener#onHighest(): Cancelled. 104");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull EntityExplodeEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("EntityExplodeListener#onMonitor(): Cancelled. 105");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
