package fr.frinn.custommachinerycreate.integration.crafttweaker.machine;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import fr.frinn.custommachinery.api.requirement.RequirementIOMode;
import fr.frinn.custommachinery.common.integration.crafttweaker.CTConstants;
import fr.frinn.custommachinery.common.integration.crafttweaker.CustomCraftRecipeCTBuilder;
import fr.frinn.custommachinery.common.integration.crafttweaker.CustomMachineRecipeCTBuilder;
import fr.frinn.custommachinerycreate.requirements.ContraptionRequirement;
import org.openzen.zencode.java.ZenCodeType.Expansion;
import org.openzen.zencode.java.ZenCodeType.Method;

@ZenRegister
@Expansion(CTConstants.RECIPE_BUILDER_MACHINE)
public class ContraptionRequirementCT {

    @Method
    public static CustomMachineRecipeCTBuilder requireSU(CustomMachineRecipeCTBuilder builder, float speed) {
        return requireSU(builder, speed, 0.0F);
    }

    @Method
    public static CustomMachineRecipeCTBuilder requireSU(CustomMachineRecipeCTBuilder builder, float speed, float stressImpact) {
        return addContraptionRequirement(builder, RequirementIOMode.INPUT, speed, stressImpact, false);
    }

    @Method
    public static CustomMachineRecipeCTBuilder requireScalingSU(CustomMachineRecipeCTBuilder builder, float speed) {
        return requireScalingSU(builder, speed, 0.0F);
    }

    @Method
    public static CustomMachineRecipeCTBuilder requireScalingSU(CustomMachineRecipeCTBuilder builder, float speed, float stressImpact) {
        return addContraptionRequirement(builder, RequirementIOMode.INPUT, speed, stressImpact, true);
    }

    @Method
    public static CustomMachineRecipeCTBuilder produceSU(CustomMachineRecipeCTBuilder builder, float speed) {
        return produceSU(builder, speed, 0.0F);
    }

    @Method
    public static CustomMachineRecipeCTBuilder produceSU(CustomMachineRecipeCTBuilder builder, float speed, float stressCapacity) {
        return addContraptionRequirement(builder, RequirementIOMode.OUTPUT, speed, stressCapacity, false);
    }

    private static CustomMachineRecipeCTBuilder addContraptionRequirement(CustomMachineRecipeCTBuilder builder, RequirementIOMode mode, float speed, float stress, boolean scaling) {
        if(speed < 0)
            return builder.error("Speed value cannot be negative: {}", speed);
        if(stress < 0)
            return builder.error("Stress value cannot be negative: {}", stress);
        if(scaling && mode == RequirementIOMode.OUTPUT)
            return builder.error("Scaling can only be enabled in input mode");
        return builder.addRequirement(new ContraptionRequirement(mode, speed, stress, scaling));
    }
}
