package com.balugaq.advancedban.api.annotations;

import com.balugaq.advancedban.api.enums.ConfigVersion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Since {
    ConfigVersion value();
}
