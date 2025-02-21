package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.Debug;
import com.balugaq.advancedban.api.EventUtil;
import com.balugaq.advancedban.api.Predications;
import com.balugaq.advancedban.api.events.EventType;
import com.balugaq.advancedban.api.events.FakePlayerInteractEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerInteractListener implements Listener {
    public static final EventType TYPE = EventType.PLAYER_INTERACT;

    public static boolean presetPredications(@NotNull FakePlayerInteractEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(event.getSlimefunId(), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void fakeEvent(@NotNull PlayerInteractEvent event) {
        if (!EventUtil.isFakeEvent(event)) {
            Debug.debug("PlayerInteractListener#fakeEvent(): New a fake event. 6");
            FakePlayerInteractEvent e = new FakePlayerInteractEvent(event);
            Bukkit.getPluginManager().callEvent(e);
            if (e.isCancelled()) {
                event.setCancelled(true);
                String id = e.getSlimefunId();
                if (id == null) {
                    return;
                }
                Debug.debug("PlayerInteractListener#fakeEvent(): Cancelled a fake event. 7");
                Debug.debug("id = " + id + " 8");
                SlimefunItem item = SlimefunItem.getById(id);
                if (item == null) {
                    return;
                }
                event.getPlayer().sendMessage(EventUtil.color(TYPE.getMessage(), "name", item.getItemName()));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull FakePlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull FakePlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull FakePlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull FakePlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull FakePlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull FakePlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            event.setCancelled(true);
        }
    }
}
