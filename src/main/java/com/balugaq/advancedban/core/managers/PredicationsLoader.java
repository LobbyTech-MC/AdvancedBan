package com.balugaq.advancedban.core.managers;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.Lang;
import com.balugaq.advancedban.api.utils.Predications;
import com.balugaq.advancedban.implementation.AdvancedBan;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventPriority;

import java.util.List;

@UtilityClass
public class PredicationsLoader {
    public static final String BANS_KEY = "bans";
    public static final String ENABLED_KEY = "enabled";
    public static final String PRIORITY_KEY = "priority";
    public static final String ITEMS_KEY = "items";
    public static final String PLAYERS_KEY = "players";
    public static final String PERMISSIONS_KEY = "permissions";
    public static final String EXAMPLE_ITEM = "a_slimefun_id";
    public static final String EXAMPLE_PLAYER = "a_player_name";
    public static final String EXAMPLE_PERMISSION_NODE = "a_permission_node";
    public static final String DEFAULT_PRIORITY = "NORMAL";

    public static void loadPredications() {
        boolean configured = true;
        boolean configuredDifferentItem = false;
        FileConfiguration configuration = AdvancedBan.getInstance().getConfigManager().getBans();
        Debug.debug("Loading predications");
        for (String key : configuration.getKeys(false)) {
            Debug.debug("Key: " + key);
        }
        Debug.debug("Loading bans");
        ConfigurationSection bans = configuration.getConfigurationSection(BANS_KEY);
        if (bans == null) {
            return;
        }

        for (EventType eventType : EventType.values()) {
            String key = eventType.getKey();
            if (!bans.contains(key)) {
                continue;
            }

            ConfigurationSection eventSection = bans.getConfigurationSection(key);
            if (eventSection == null) {
                continue;
            }

            for (String groupKey : eventSection.getKeys(false)) {
                ConfigurationSection groupSection = eventSection.getConfigurationSection(groupKey);
                if (groupSection == null) {
                    continue;
                }

                boolean enabled = groupSection.getBoolean(ENABLED_KEY, true);
                if (!enabled) {
                    continue;
                }

                String eventPriorityStr = groupSection.getString(PRIORITY_KEY, DEFAULT_PRIORITY).toUpperCase();
                EventPriority eventPriority;
                try {
                    eventPriority = EventPriority.valueOf(eventPriorityStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    invalidKey(key + "." + groupKey + "." + PRIORITY_KEY, eventPriorityStr);
                    eventPriority = EventPriority.NORMAL;
                }

                List<String> players = groupSection.getStringList(PLAYERS_KEY);
                List<String> permissions = groupSection.getStringList(PERMISSIONS_KEY);
                List<String> items = groupSection.getStringList(ITEMS_KEY);
                for (String rid : items) {
                    String id = rid.toUpperCase();
                    SlimefunItem slimefunItem = SlimefunItem.getById(id);
                    if (slimefunItem == null) {
                        if (id.equalsIgnoreCase(EXAMPLE_ITEM)) {
                            configured = false;
                            continue;
                        }

                        invalidKey(key + "." + groupKey + "." + ITEMS_KEY, id);
                        continue;
                    }

                    Predications.addPredication(id, eventType, eventPriority);
                    if (players != null && !players.isEmpty()) {
                        if (players.size() == 1 && players.get(0).equalsIgnoreCase(EXAMPLE_PLAYER)) {
                            continue;
                        }
                        Predications.addBypassPlayers(id, eventType, players);
                    }

                    if (permissions != null && !permissions.isEmpty()) {
                        if (permissions.size() == 1 && permissions.get(0).equalsIgnoreCase(EXAMPLE_PERMISSION_NODE)) {
                            continue;
                        }
                        Predications.addBypassPermissions(id, eventType, permissions);
                    }
                    AdvancedBan.getInstance().getLogger().info(Lang.getMessage("load.added-predication", "id", id, "event_priority", eventPriority, "event_type", eventType.name()));
                    configuredDifferentItem = true;
                }

            }
        }

        if (!configured && !configuredDifferentItem) {
            AdvancedBan.getInstance().getLogger().warning(Lang.getMessage("load.no-configured-predications"));
        }
    }

    public static void invalidKey(String path, String value) {
        AdvancedBan.getInstance().getLogger().severe(Lang.getMessage("load.invalid-bans-key"));
        AdvancedBan.getInstance().getLogger().severe(Lang.getMessage("load.path", "path", path));
        AdvancedBan.getInstance().getLogger().severe(Lang.getMessage("load.value", "value", value));
    }
}
