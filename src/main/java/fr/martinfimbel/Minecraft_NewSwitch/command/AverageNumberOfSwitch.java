package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class AverageNumberOfSwitch extends AbstractLabelEdition<ISwitchConfiguration> {

	public AverageNumberOfSwitch() {
		super(ESwitchLabel.AVERAGE_NUMBER_OF_SWITCH, ESwitchMessageCode.AVERAGE_NUMBER_OF_SWITCH_EXPLANATION);
	}

	@Override
	public boolean isAvailable() {
		return get() != null && get().isRandomSwitchActivated();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			int number = Integer.parseInt(args[0]);
			if (number < 1) {
				sendSynchro(sender, ESwitchMessageCode.AVERAGE_NUMBER_OF_SWITCH_INFERIOR_AT_ONE);
				return true;
			}
			get().setAverageNumberOfSwitch(Integer.parseInt(args[0]));
			sendSynchro(sender, ESwitchMessageCode.AVERAGE_NUMBER_OF_SWITCH_DEFINED, "" + get().getAverageNumberOfSwitch());
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendSynchro(sender, ESwitchMessageCode.AVERAGE_NUMBER_OF_SWITCH_MISSING_NUMBER);
		} catch (NumberFormatException e) {
			sendSynchro(sender, ECommonMessageCode.COMMON_BAD_INTEGER_FORMAT);
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return Arrays.asList(getMessage(sender, ESwitchMessageCode.AVERAGE_NUMBER_OF_SWITCH_TAB_COMPLETE));
		return emptyList();
	}

}
