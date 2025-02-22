package com.balugaq.advancedban.api.utils;

import com.balugaq.advancedban.api.enums.EventType;
import lombok.experimental.UtilityClass;
import org.bukkit.event.EventPriority;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Predications {
    private static final Map<String, Map<EventType, EventPriority>> PREDICATIONS = new HashMap<>();

    public static void addPredication(@NotNull String itemId, @NotNull EventType type, @NotNull EventPriority priority) {
        if (!PREDICATIONS.containsKey(itemId)) {
            PREDICATIONS.put(itemId, new HashMap<>());
        }
        PREDICATIONS.get(itemId).put(type, priority);
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

    public static void clearPredications() {
        synchronized (PREDICATIONS) {
            PREDICATIONS.clear();
        }
    }
}
