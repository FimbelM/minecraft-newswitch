package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonSave;

public class SaveSwitch extends CommonSave<ISwitchConfiguration> {

	protected SaveSwitch() {
		super(ESwitchMessageCode.SAVE_SW__EXPLANATION);
	}

	@Override
	protected void onSave(CommandSender sender, String name) {
		sendMessageToSender(sender, ESwitchMessageCode.SAVE_SW__CONFIGURATION_SAVED, name);
	}
}
