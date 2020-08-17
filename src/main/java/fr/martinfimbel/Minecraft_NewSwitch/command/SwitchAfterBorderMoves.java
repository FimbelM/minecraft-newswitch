package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class SwitchAfterBorderMoves extends AbstractLabelEdition<ISwitchConfiguration> {

	public SwitchAfterBorderMoves() {
		super(ESwitchLabel.SWITCH_AFTER_BORDER_MOVES, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String letter = args[0];
		if (letter.equalsIgnoreCase("Y")) {
			get().setSwitchAfterBorderMoves(letter);
			sendMessageToSender(sender, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_DEFINED, get().getSwitchAfterBorderMoves());
			return true;
		} else if (letter.equalsIgnoreCase("N")) {
			get().setSwitchAfterBorderMoves(letter);
			sendMessageToSender(sender, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_DEFINED, get().getSwitchAfterBorderMoves());
			return true;
		} else {
			sendMessageToSender(sender, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_WRONG_FORMAT);
			return true;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return Arrays.asList(getMessageFromDictionary(sender, ESwitchMessageCode.SWITCH_AFTER_BORDER_MOVES_TAB_COMPLETE));
		return emptyList();
	}
}
