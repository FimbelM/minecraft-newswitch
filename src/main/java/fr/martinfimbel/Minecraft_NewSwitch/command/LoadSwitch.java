package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonLoad;
import fr.pederobien.minecraftgameplateform.commands.common.ECommonLabel;
import fr.pederobien.minecraftgameplateform.commands.configurations.EGameConfigurationLabel;
import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public class LoadSwitch extends CommonLoad<ISwitchConfiguration> {

	protected LoadSwitch() {
		super(ESwitchMessageCode.LOAD_SW__EXPLANATION);
	}

	@Override
	protected void onStyleLoaded(CommandSender sender, String name) {
		sendSynchro(sender, ESwitchMessageCode.LOAD_SW__CONFIGURATION_LOADED, name);
		setAllAvailable();
	}

	@Override
	protected void onNameIsMissing(CommandSender sender) {
		sendSynchro(sender, ESwitchMessageCode.LOAD_SW__NAME_IS_MISSING);
	}

	private void setAllAvailable() {
		for (ILabel label : ECommonLabel.values())
			setAvailableLabelEdition(label);
		for (ILabel label : EGameConfigurationLabel.values())
			setAvailableLabelEdition(label);
		for (ILabel label : ESwitchLabel.values())
			setAvailableLabelEdition(label);
	}
}
