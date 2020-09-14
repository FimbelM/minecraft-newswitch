package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class SwitchAfterBorderMoves extends AbstractLabelEdition<ISwitchConfiguration> {

	public SwitchAfterBorderMoves() {
		super(ESwitchLabel.SWITCH_AFTER_BORDER_MOVES, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			String value = args[0];
			if (value.equals("true"))
				get().setSwitchAfterBorderMovesActivated(true);
			else if (value.equals("false"))
				get().setSwitchAfterBorderMovesActivated(false);
			else {
				sendMessageToSender(sender, ECommonMessageCode.COMMON_BAD_BOOLEAN_FORMAT);
				return false;
			}
			sendMessageToSender(sender, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_DEFINED, get().isSwitchAfterBorderMovesActivated());
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_VALUE_IS_MISSING);
			return false;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return asList("true", "false");
		return emptyList();
	}
}
