package com.balugaq.advancedban.core.managers;

import com.balugaq.advancedban.core.listeners.BlockBreakListener;
import com.balugaq.advancedban.core.listeners.BlockBurnListener;
import com.balugaq.advancedban.core.listeners.BlockExplodeListener;
import com.balugaq.advancedban.core.listeners.BlockPlaceListener;
import com.balugaq.advancedban.core.listeners.CraftItemListener;
import com.balugaq.advancedban.core.listeners.EntityChangeBlockListener;
import com.balugaq.advancedban.core.listeners.EntityExplodeListener;
import com.balugaq.advancedban.core.listeners.ExplosiveToolBreakBlocksListener;
import com.balugaq.advancedban.core.listeners.MachineCraftListener;
import com.balugaq.advancedban.core.listeners.MultiBlockCraftListener;
import com.balugaq.advancedban.core.listeners.PlayerDropItemListener;
import com.balugaq.advancedban.core.listeners.PlayerInteractListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListenerManager {
    private final @NotNull JavaPlugin plugin;
    private final @NotNull List<Listener> listeners;

    public ListenerManager(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        this.listeners = new ArrayList<>();
    }

    public void setup() {
        listeners.add(new BlockBreakListener());
        listeners.add(new BlockBurnListener());
        listeners.add(new BlockExplodeListener());
        listeners.add(new BlockPlaceListener());
        listeners.add(new CraftItemListener());
        listeners.add(new EntityChangeBlockListener());
        listeners.add(new EntityExplodeListener());
        listeners.add(new ExplosiveToolBreakBlocksListener());
        listeners.add(new MachineCraftListener());
        listeners.add(new MultiBlockCraftListener());
        listeners.add(new PlayerDropItemListener());
        listeners.add(new PlayerInteractListener());
    }

    public void load() {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    public void unload() {
        for (Listener listener : listeners) {
            HandlerList.unregisterAll(listener);
        }
    }
}
