package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonDelete;

public class DeleteSwitch extends CommonDelete<ISwitchConfiguration> {

	protected DeleteSwitch() {
		super(ESwitchMessageCode.DELETE_SW__EXPLANATION);
	}

	@Override
	protected void onDidNotDelete(CommandSender sender, String name) {
		sendMessageToSender(sender, ESwitchMessageCode.DELETE_SW__DID_NOT_DELETE, name);
	}

	@Override
	protected void onDeleted(CommandSender sender, String name) {
		sendMessageToSender(sender, ESwitchMessageCode.DELETE_SW__CONFIGURATION_DELETED, name);
	}

	@Override
	protected void onNameIsMissing(CommandSender sender) {
		sendMessageToSender(sender, ESwitchMessageCode.DELETE_SW__NAME_IS_MISSING);
	}
}