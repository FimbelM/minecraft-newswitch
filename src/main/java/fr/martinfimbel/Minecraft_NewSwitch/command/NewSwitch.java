package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.SwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonNew;
import fr.pederobien.minecraftgameplateform.commands.common.ECommonLabel;
import fr.pederobien.minecraftgameplateform.commands.configurations.EGameConfigurationLabel;
import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public class NewSwitch extends CommonNew<ISwitchConfiguration> {

	protected NewSwitch() {
		super(ESwitchMessageCode.NEW_SW__EXPLANATION);
	}

	@Override
	protected void onNameAlreadyTaken(CommandSender sender, String name) {
		sendMessageToSender(sender, ESwitchMessageCode.NEW_SW__NAME_ALREADY_TAKEN, name);
	}

	@Override
	protected void onNameIsMissing(CommandSender sender) {
		sendMessageToSender(sender, ESwitchMessageCode.NEW_SW__NAME_IS_MISSING);
	}

	@Override
	protected ISwitchConfiguration create(String name) {
		return new SwitchConfiguration(name);
	}

	@Override
	protected void onCreated(CommandSender sender, String name) {
		sendMessageToSender(sender, ESwitchMessageCode.NEW_SW__CONFIGURATION_CREATED, name);
		setAllAvailable();
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
