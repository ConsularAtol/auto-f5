package consular.auto_f5;

import consular.auto_f5.config.AutoF5Config;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.Perspective;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class AutoF5Client implements ClientModInitializer {
    public static AutoF5Config config;

    private boolean wasRiding = false;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(AutoF5Config.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(AutoF5Config.class).getConfig();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            Entity vehicle = client.player.getVehicle();
            boolean isRiding = vehicle != null;

            if (isRiding && !wasRiding) {
                Identifier entityId = Registries.ENTITY_TYPE.getId(vehicle.getType());
                if (!config.blacklistedEntities.contains(entityId.toString())) {
                    client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
                }
            } else if (!isRiding && wasRiding) {
                client.options.setPerspective(Perspective.FIRST_PERSON);
            }

            wasRiding = isRiding;
        });
    }
}