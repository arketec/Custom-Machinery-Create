package fr.frinn.custommachinerycreate.fabric;

import fr.frinn.custommachinery.api.ICustomMachineryAPI;
import fr.frinn.custommachinerycreate.CustomMachineryCreate;
import net.fabricmc.api.ModInitializer;

public class CustomMachineryCreateFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // Fabric doesn't guarantee CM's entrypoint runs first. CM creates its custom
        // registries when its Registration class loads, so touch them via the API
        // to make sure they exist before we register into them.
        ICustomMachineryAPI.INSTANCE.guiElementRegistrar();
        ICustomMachineryAPI.INSTANCE.componentRegistrar();
        ICustomMachineryAPI.INSTANCE.requirementRegistrar();

        CustomMachineryCreate.init();
    }
}
