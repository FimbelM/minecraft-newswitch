package fr.martinfimbel.Minecraft_NewSwitch.command;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonDetails;

public class DetailsSwitch extends CommonDetails<ISwitchConfiguration> {

	protected DetailsSwitch() {
		super(ESwitchMessageCode.DETAILS_SW__EXPLANATION, ESwitchMessageCode.DETAILS_SW__ON_DETAILS);
	}
}
