package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class OnePlayerSwitch extends AbstractLabelEdition<ISwitchConfiguration> {
	public OnePlayerSwitch() {
		super(ESwitchLabel.ONE_PLAYER_SWITCH, ESwitchMessageCode.ONE_PLAYER_SWITCH_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			String value = args[0];
			if (value.equals("true"))
				get().setOnePlayerSwitch(true);
			else if (value.equals("false"))
				get().setOnePlayerSwitch(false);
			else {
				sendMessageToSender(sender, ECommonMessageCode.COMMON_BAD_BOOLEAN_FORMAT);
				return false;
			}
			sendMessageToSender(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_DEFINED, get().isOnePlayerSwitchActivated());
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_VALUE_IS_MISSING);
			return false;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return Arrays.asList(getMessageFromDictionary(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_TAB_COMPLETE));
		return emptyList();
	}
}
