package com.balugaq.advancedban.api.utils;

import com.balugaq.advancedban.implementation.AdvancedBan;
import org.jetbrains.annotations.NotNull;

public class Lang {
    public static @NotNull String getMessage(String path) {
        return AdvancedBan.getInstance().getLocalizationService().getString("messages." + path);
    }

    public static @NotNull String getMessage(String path, Object... args) {
        return EventUtil.color(AdvancedBan.getInstance().getLocalizationService().getString("messages." + path), args);
    }
}
