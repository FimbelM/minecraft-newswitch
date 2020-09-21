package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftdevelopmenttoolkit.utils.DisplayHelper;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;

public class RandomSwitchMinimalTimeBound extends AbstractLabelEdition<ISwitchConfiguration> {

	public RandomSwitchMinimalTimeBound() {
		super(ESwitchLabel.RANDOM_SWITCH_MINIMAL_TIME, ESwitchMessageCode.RANDOM_SWITCH_MINIMAL_TIME_EXPLANATION);
	}

	@Override
	public boolean isAvailable() {
		return get() != null && get().isRandomSwitchActivated();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			get().setMinimalSwitchTime(LocalTime.parse(args[0]));
			sendSynchro(sender, ESwitchMessageCode.RANDOM_SWITCH_MINIMAL_TIME_DEFINED, DisplayHelper.toString(get().getMinimalSwitchTime(), false));
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendSynchro(sender, ESwitchMessageCode.RANDOM_SWITCH_MINIMAL_MAXIMAL_TIME_MISSING_TIME);
		} catch (DateTimeParseException e) {
			sendSynchro(sender, ECommonMessageCode.COMMON_BAD_TIME_FORMAT);
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1)
			return Arrays.asList(getMessage(sender, ESwitchMessageCode.RANDOM_SWITCH_MINIMAL_MAXIMAL_TIME_TAB_COMPLETE));
		return emptyList();
	}

}
