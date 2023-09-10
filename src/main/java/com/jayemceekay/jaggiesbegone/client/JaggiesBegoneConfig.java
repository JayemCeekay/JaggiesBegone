package com.jayemceekay.jaggiesbegone.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "jaggiesbegone")
public class JaggiesBegoneConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean enableFXAA = true;
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean enableAutoMipmaps = true;

}
