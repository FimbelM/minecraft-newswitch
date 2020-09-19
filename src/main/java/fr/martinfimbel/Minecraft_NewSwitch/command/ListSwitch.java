package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.common.CommonList;

public class ListSwitch extends CommonList<ISwitchConfiguration> {

	protected ListSwitch() {
		super(ESwitchMessageCode.LIST_SW__EXPLANATION);
	}

	@Override
	protected void onNoElement(CommandSender sender) {
		sendSynchro(sender, ESwitchMessageCode.LIST_SW__NO_REGISTERED_CONFIGURATION);
	}

	@Override
	protected void onOneElement(CommandSender sender, String name) {
		sendSynchro(sender, ESwitchMessageCode.LIST_SW__ONE_REGISTERED_CONFIGURATION, name);
	}

	@Override
	protected void onSeveralElement(CommandSender sender, String names) {
		sendSynchro(sender, ESwitchMessageCode.LIST_SW__SEVERAL_ELEMENTS, names);
	}
}
