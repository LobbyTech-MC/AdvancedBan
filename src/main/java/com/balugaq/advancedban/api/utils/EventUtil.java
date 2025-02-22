package com.balugaq.advancedban.api.utils;

import com.balugaq.advancedban.api.enums.EventType;
import io.github.thebusybiscuit.slimefun4.api.events.MultiBlockCraftEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.experimental.UtilityClass;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class EventUtil {
    public static @NotNull String color(@NotNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static @NotNull String color(@NotNull String message, Object @NotNull ... args) {
        String coloredMessage = color(message);
        for (int i = 0; i < args.length; i += 2) {
            coloredMessage = coloredMessage.replace("{" + args[i] + "}", args[i + 1].toString());
        }
        return coloredMessage;
    }

    @Nullable
    public static String getSlimefunId(@NotNull BlockBreakEvent event) {
        SlimefunItem item = BlockStorage.check(event.getBlock());
        String id = item == null ? null : item.getId();
        Debug.debug("getSlimefunId#BlockBreakEvent: id = " + id);
        return id;
    }

    @Nullable
    public static String getSlimefunId(@NotNull BlockPlaceEvent event) {
        SlimefunItem item = SlimefunItem.getByItem(event.getItemInHand());
        String id = item == null ? null : item.getId();
        Debug.debug("getSlimefunId#BlockPlaceEvent: id = " + id);
        return id;
    }

    @Nullable
    public static String getSlimefunId(@NotNull CraftItemEvent event) {
        SlimefunItem item = SlimefunItem.getByItem(event.getRecipe().getResult());
        String id = item == null ? null : item.getId();
        Debug.debug("getSlimefunId#CraftItemEvent: id = " + id);
        return id;
    }

    @Nullable
    public static String getSlimefunId(@NotNull MultiBlockCraftEvent event) {
        SlimefunItem item = SlimefunItem.getByItem(event.getOutput());
        String id = item == null ? null : item.getId();
        Debug.debug("getSlimefunId#MultiBlockCraftEvent: id = " + id);
        return id;
    }

    @Nullable
    public static String getSlimefunId(@NotNull PlayerDropItemEvent event) {
        SlimefunItem item = SlimefunItem.getByItem(event.getItemDrop().getItemStack());
        String id = item == null ? null : item.getId();
        Debug.debug("getSlimefunId#PlayerDropItemEvent: id = " + id);
        return id;
    }

    @Nullable
    public static String getSlimefunId(@NotNull PlayerInteractEvent event) {
        SlimefunItem item = SlimefunItem.getByItem(event.getItem());
        String id = item == null ? null : item.getId();
        Debug.debug("getSlimefunId#PlayerInteractEvent: id = " + id);
        return id;
    }

    @Nullable
    public static String getSlimefunId(@NotNull ItemStack itemStack) {
        SlimefunItem item = SlimefunItem.getByItem(itemStack);
        String id = item == null ? null : item.getId();
        Debug.debug("getSlimefunId#ItemStack: id = " + id);
        return id;
    }

    public static void notice(@NotNull Event event, @NotNull EventType eventType) {
        if (event instanceof BlockBreakEvent bbe) {
            String id = getSlimefunId(bbe);
            if (id == null) {
                return;
            }
            SlimefunItem item = SlimefunItem.getById(id);
            if (item == null) {
                return;
            }
            notice(bbe.getPlayer(), eventType, item.getItemName());
        } else if (event instanceof BlockPlaceEvent bpe) {
            String id = getSlimefunId(bpe);
            if (id == null) {
                return;
            }
            SlimefunItem item = SlimefunItem.getById(id);
            if (item == null) {
                return;
            }
            notice(bpe.getPlayer(), eventType, item.getItemName());
        } else if (event instanceof CraftItemEvent cie) {
            String id = getSlimefunId(cie);
            if (id == null) {
                return;
            }
            SlimefunItem item = SlimefunItem.getById(id);
            if (item == null) {
                return;
            }
            notice(cie.getWhoClicked(), eventType, item.getItemName());
        } else if (event instanceof MultiBlockCraftEvent mbce) {
            String id = getSlimefunId(mbce);
            if (id == null) {
                return;
            }
            SlimefunItem item = SlimefunItem.getById(id);
            if (item == null) {
                return;
            }
            notice(mbce.getPlayer(), eventType, item.getItemName());
        } else if (event instanceof PlayerDropItemEvent pde) {
            String id = getSlimefunId(pde);
            if (id == null) {
                return;
            }
            SlimefunItem item = SlimefunItem.getById(id);
            if (item == null) {
                return;
            }
            notice(pde.getPlayer(), eventType, item.getItemName());
        } else if (event instanceof PlayerInteractEvent pie) {
            String id = getSlimefunId(pie);
            if (id == null) {
                return;
            }
            SlimefunItem item = SlimefunItem.getById(id);
            if (item == null) {
                return;
            }
            notice(pie.getPlayer(), eventType, item.getItemName());
        }
    }

    public static void notice(@NotNull HumanEntity player, @NotNull EventType eventType, @NotNull String itemName) {
        player.sendMessage(EventUtil.color(eventType.getMessage(), "name", itemName));
    }
}
