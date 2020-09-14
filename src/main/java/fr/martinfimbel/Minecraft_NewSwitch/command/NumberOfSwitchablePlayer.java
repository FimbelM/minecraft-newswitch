package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class NumberOfSwitchablePlayer extends AbstractLabelEdition<ISwitchConfiguration> {

	public NumberOfSwitchablePlayer() {
		super(ESwitchLabel.NUMBER_OF_SWITCHABLE_PLAYER_PER_TEAM, ESwitchMessageCode.NUMBER_OF_SWITCHABLE_PLAYER_PER_TEAM_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			int number = Integer.parseInt(args[0]);
			if (number < 1) {
				sendMessageToSender(sender, ESwitchMessageCode.NUMBER_OF_SWITCHED_PLAYERS_INFERIOR_AT_ONE);
				return true;
			}
			get().setNumberOfPlayerSwitchable(Integer.parseInt(args[0]));
			sendMessageToSender(sender, ESwitchMessageCode.NUMBER_OF_SWITCHABLE_PLAYER_PER_TEAM_DEFINED, "" + get().getNumberOfPlayerSwitchable());
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, ESwitchMessageCode.NUMBER_OF_SWITCHABLE_PLAYER_PER_TEAM_MISSING_NUMBER);
		} catch (NumberFormatException e) {
			sendMessageToSender(sender, ECommonMessageCode.COMMON_BAD_INTEGER_FORMAT);
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return Arrays.asList(getMessageFromDictionary(sender, ESwitchMessageCode.NUMBER_OF_SWITCHABLE_PLAYER_ON_TAB_COMPLETE));
		return emptyList();
	}
}
