package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonRename;

public class RenameSwitch extends CommonRename<ISwitchConfiguration> {

	protected RenameSwitch() {
		super(ESwitchMessageCode.RENAME_SW__EXPLANATION);
	}

	@Override
	protected void onNameAlreadyTaken(CommandSender sender, String currentName, String newName) {
		sendMessageToSender(sender, ESwitchMessageCode.RENAME_SW__NAME_ALREADY_TAKEN, currentName, newName);
	}

	@Override
	protected void onNameIsMissing(CommandSender sender, String oldName) {
		sendMessageToSender(sender, ESwitchMessageCode.RENAME_SW__NAME_IS_MISSING, oldName);
	}

	@Override
	protected void onRenamed(CommandSender sender, String oldName, String newName) {
		sendMessageToSender(sender, ESwitchMessageCode.RENAME_SW__CONFIGURATION_RENAMED, oldName, newName);
	}
}
