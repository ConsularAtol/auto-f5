package consular.auto_f5;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import consular.auto_f5.config.AutoF5Config;
import me.shedaniel.autoconfig.AutoConfig;

public class AutoF5ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(AutoF5Config.class, parent).get();
    }
}
