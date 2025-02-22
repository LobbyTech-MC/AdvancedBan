package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.Debug;
import com.balugaq.advancedban.api.EventUtil;
import com.balugaq.advancedban.api.Predications;
import com.balugaq.advancedban.api.events.EventType;
import com.balugaq.advancedban.api.events.FakeBlockPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.events.SlimefunBlockPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.BlockListener;
import net.bytebuddy.asm.Advice;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class BlockPlaceListener implements Listener {
    public static final Set<Location> fixLocations = new HashSet<>();
    public static final EventType TYPE = EventType.BLOCK_PLACE;

    public static boolean presetPredications(@NotNull FakeBlockPlaceEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(event.getSlimefunId(), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void fakeEvent(@NotNull BlockPlaceEvent event) {
        if (!EventUtil.isFakeEvent(event)) {
            Debug.debug("BlockPlaceListener#fakeEvent(): New a fake event. 2");
            FakeBlockPlaceEvent e = new FakeBlockPlaceEvent(event);
            if (Predications.getPriority(e.getSlimefunId(), TYPE) != null) {
                fixLocations.add(event.getBlock().getLocation());
            }
            Bukkit.getPluginManager().callEvent(e);
            if (e.isCancelled()) {
                event.setCancelled(true);
                String id = e.getSlimefunId();
                if (id == null) {
                    return;
                }
                SlimefunItem item = SlimefunItem.getById(id);
                if (item == null) {
                    return;
                }
                event.getPlayer().sendMessage(EventUtil.color(TYPE.getMessage(), "name", item.getItemName()));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("BlockPlaceListener#onLowest(): Cancelled. 15");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("BlockPlaceListener#onLow(): Cancelled. 16");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("BlockPlaceListener#onNormal(): Cancelled. 17");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("BlockPlaceListener#onHigh(): Cancelled. 18");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("BlockPlaceListener#onHighest(): Cancelled. 19");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("BlockPlaceListener#onMonitor(): Cancelled. 20");
            event.setCancelled(true);
        }
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    public static boolean onMethodEnter(
            @Advice.Argument(0) BlockPlaceEvent event
    ) {
        Debug.debug("BlockPlaceListener#onMethodEnter(): Entered. 54");
        if (EventUtil.isFakeEvent(event)) {
            Debug.debug("BlockPlaceListener#onMethodEnter(): Is fake event. 55");
            return true; // ignored the original logic
        } else {
            Debug.debug("BlockPlaceListener#onMethodEnter(): Is not fake event. 56");
            if (fixLocations.contains(event.getBlock().getLocation())) {
                Debug.debug("BlockPlaceListener#onMethodEnter(): Is fixed location. 57");
                return true; // ignored the original logic
            }
            return false; // continue the original logic
        }
    }
}
