package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class OnePlayerSwitch extends AbstractLabelEdition<ISwitchConfiguration> {
	public OnePlayerSwitch() {
		super(ESwitchLabel.ONE_PLAYER_SWITCH, ESwitchMessageCode.ONE_PLAYER_SWITCH_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String letter = args[0];
		if (letter.equalsIgnoreCase("Y")) {
			get().setOnePlayerSwitch(letter);
			sendMessageToSender(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_DEFINED, get().getOnePlayerSwitch());
			return true;
		} else if (letter.equalsIgnoreCase("N")) {
			get().setOnePlayerSwitch(letter);
			sendMessageToSender(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_DEFINED, get().getOnePlayerSwitch());
			return true;
		} else {
			sendMessageToSender(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_WRONG_FORMAT);
			return true;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return Arrays.asList(getMessageFromDictionary(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_TAB_COMPLETE));
		return emptyList();
	}
}
