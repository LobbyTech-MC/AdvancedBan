package com.balugaq.advancedban.api;

import com.balugaq.advancedban.api.events.ConfigVersion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Since {
    ConfigVersion value();
}
