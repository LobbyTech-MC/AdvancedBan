package com.balugaq.advancedban.api;

import com.balugaq.advancedban.api.events.FakeEvent;
import com.balugaq.advancedban.implementation.AdvancedBan;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;

@UtilityClass
public class EventUtil {
    public static boolean isFakeEvent(Event event) {
        return AdvancedBan.getInstance().isEnabledPlugin() && event instanceof FakeEvent;
    }
    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String color(String message, Object... args) {
        String coloredMessage = color(message);
        for (int i = 0; i < args.length; i += 2) {
            coloredMessage = coloredMessage.replace("{" + args[i] + "}", args[i+1].toString());
        }
        return coloredMessage;
    }
}
