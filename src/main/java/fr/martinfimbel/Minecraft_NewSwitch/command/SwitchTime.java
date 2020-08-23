package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class SwitchTime extends AbstractLabelEdition<ISwitchConfiguration> {

	public SwitchTime() {
		super(ESwitchLabel.SWITCH_TIME, ESwitchMessageCode.SWITCH_TIME_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			get().setPeriodSwitchTime(LocalTime.parse(args[0]));
			sendMessageToSender(sender, ESwitchMessageCode.SWITCH_TIME_DEFINED, "" + get().getPeriodSwitchTime().getHour(), "" + get().getPeriodSwitchTime().getMinute(),
					"" + get().getPeriodSwitchTime().getSecond());
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, ESwitchMessageCode.SWITCH_TIME_MISSING_TIME);
		} catch (DateTimeParseException e) {
			sendMessageToSender(sender, ESwitchMessageCode.BAD_TIME_FORMAT);
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return Arrays.asList(getMessageFromDictionary(sender, ESwitchMessageCode.ONE_PLAYER_SWITCH_TAB_COMPLETE));
		return emptyList();
	}
}
