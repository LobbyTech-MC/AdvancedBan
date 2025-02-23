package com.balugaq.advancedban.api.utils;

import com.balugaq.advancedban.api.enums.EventType;
import lombok.experimental.UtilityClass;
import org.bukkit.event.EventPriority;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@UtilityClass
public class Predications {
    private static final Map<String, Map<EventType, EventPriority>> PREDICATIONS = new HashMap<>();
    private static final Map<String, Map<EventType, Set<String>>> BYPASS_PLAYERS = new HashMap<>();
    private static final Map<String, Map<EventType, Set<String>>> BYPASS_PERMISSIONS = new HashMap<>();

    public static void addPredication(@NotNull String itemId, @NotNull EventType type, @NotNull EventPriority priority) {
        if (!PREDICATIONS.containsKey(itemId)) {
            PREDICATIONS.put(itemId, new HashMap<>());
        }
        PREDICATIONS.get(itemId).put(type, priority);
    }

    public static void addBypassPlayers(@NotNull String itemId, @NotNull EventType type, @NotNull Collection<String> players) {
        if (!BYPASS_PLAYERS.containsKey(itemId)) {
            BYPASS_PLAYERS.put(itemId, new HashMap<>());
        }
        if (!BYPASS_PLAYERS.get(itemId).containsKey(type)) {
            BYPASS_PLAYERS.get(itemId).put(type, new HashSet<>());
        }
        BYPASS_PLAYERS.get(itemId).get(type).addAll(players);
    }

    public static void addBypassPlayer(@NotNull String itemId, @NotNull EventType type, @NotNull String player) {
        if (!BYPASS_PLAYERS.containsKey(itemId)) {
            BYPASS_PLAYERS.put(itemId, new HashMap<>());
        }

        if (!BYPASS_PLAYERS.get(itemId).containsKey(type)) {
            BYPASS_PLAYERS.get(itemId).put(type, new HashSet<>());
        }
        BYPASS_PLAYERS.get(itemId).get(type).add(player);
    }

    public static void addBypassPermissions(@NotNull String itemId, @NotNull EventType type, @NotNull Collection<String> permissions) {
        if (!BYPASS_PERMISSIONS.containsKey(itemId)) {
            BYPASS_PERMISSIONS.put(itemId, new HashMap<>());
        }

        if (!BYPASS_PERMISSIONS.get(itemId).containsKey(type)) {
            BYPASS_PERMISSIONS.get(itemId).put(type, new HashSet<>());
        }
        BYPASS_PERMISSIONS.get(itemId).get(type).addAll(permissions);
    }

    public static void addBypassPermission(@NotNull String itemId, @NotNull EventType type, @NotNull String permission) {
        if (!BYPASS_PERMISSIONS.containsKey(itemId)) {
            BYPASS_PERMISSIONS.put(itemId, new HashMap<>());
        }

        if (!BYPASS_PERMISSIONS.get(itemId).containsKey(type)) {
            BYPASS_PERMISSIONS.get(itemId).put(type, new HashSet<>());
        }
        BYPASS_PERMISSIONS.get(itemId).get(type).add(permission);
    }

    public static boolean isBypassPlayer(@NotNull String itemId, @NotNull EventType type, @NotNull String playerName) {
        if (!BYPASS_PLAYERS.containsKey(itemId)) {
            return false;
        }

        if (!BYPASS_PLAYERS.get(itemId).containsKey(type)) {
            return false;
        }
        return BYPASS_PLAYERS.get(itemId).get(type).contains(playerName);
    }

    public static boolean isBypassPermission(@NotNull String itemId, @NotNull EventType type, @NotNull String permission) {
        if (!BYPASS_PERMISSIONS.containsKey(itemId)) {
            return false;
        }

        if (!BYPASS_PERMISSIONS.get(itemId).containsKey(type)) {
            return false;
        }
        return BYPASS_PERMISSIONS.get(itemId).get(type).contains(permission);
    }

    @Contract("null, _ -> false; _, null -> false;")
    public static boolean preset(@Nullable String itemId, @Nullable EventType type) {
        if (itemId == null || type == null) {
            return false;
        }

        Map<EventType, EventPriority> eventPredications = PREDICATIONS.get(itemId);
        if (eventPredications == null) {
            return false;
        }

        EventPriority eventPriority = eventPredications.get(type);
        if (eventPriority == null) {
            return false;
        }

        return true;
    }

    @Nullable
    @Contract("null, _ -> null; _, null -> null;")
    public static EventPriority getPriority(@Nullable String itemId, @Nullable EventType type) {
        if (itemId == null || type == null) {
            return null;
        }
        if (!preset(itemId, type)) {
            return null;
        }

        return PREDICATIONS.get(itemId).get(type);
    }

    @Nonnull
    public static Map<String, Map<EventType, EventPriority>> getPredications() {
        return PREDICATIONS;
    }

    @Nonnull
    public static Map<String, Map<EventType, Set<String>>> getBypassPlayers() {
        return BYPASS_PLAYERS;
    }

    @Nonnull
    public static Map<String, Map<EventType, Set<String>>> getBypassPermissions() {
        return BYPASS_PERMISSIONS;
    }

    @Nonnull
    public static Set<String> getBypassPermissions(@NotNull String itemId, @NotNull EventType type) {
        if (!BYPASS_PERMISSIONS.containsKey(itemId)) {
            return new HashSet<>();
        }

        if (!BYPASS_PERMISSIONS.get(itemId).containsKey(type)) {
            return new HashSet<>();
        }

        return BYPASS_PERMISSIONS.get(itemId).get(type);
    }

    public static void clearPredications() {
        synchronized (PREDICATIONS) {
            PREDICATIONS.clear();
        }
    }

    public static void clearBypassPlayers() {
        synchronized (BYPASS_PLAYERS) {
            BYPASS_PLAYERS.clear();
        }
    }

    public static void clearBypassPermissions() {
        synchronized (BYPASS_PERMISSIONS) {
            BYPASS_PERMISSIONS.clear();
        }
    }
}
