package consular.auto_f5.config;

import java.util.ArrayList;
import java.util.List;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "auto_f5")
public class AutoF5Config implements ConfigData{
    public List<String> blacklistedEntities = new ArrayList<>(List.of(
    ));
}