package fr.martinfimbel.Minecraft_NewSwitch.command;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftdevelopmenttoolkit.utils.DisplayHelper;
import fr.pederobien.minecraftgameplateform.dictionary.ECommonMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;
import fr.pederobien.minecraftmanagers.WorldManager;

public class RandomSwitchMaximalTimeBound extends AbstractLabelEdition<ISwitchConfiguration> {

	public RandomSwitchMaximalTimeBound() {
		super(ESwitchLabel.RANDOM_SWITCH_MAXIMAL_TIME, ESwitchMessageCode.RANDOM_SWITCH_MAXIMAL_TIME_EXPLANATION);
	}

	@Override
	public boolean isAvailable() {
		return get() != null && get().isRandomSwitchActivated();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			get().setMaximalSwitchTime(LocalTime.parse(args[0]));
			IBorderConfiguration borderConf = get().getBorder(WorldManager.OVERWORLD).get();
			LocalTime borderStart = borderConf.getStartTime();
			LocalTime upperBound = borderStart.minusSeconds(get().getMaximalSwitchTime().toSecondOfDay());
			sendSynchro(sender, ESwitchMessageCode.RANDOM_SWITCH_MAXIMAL_TIME_DEFINED, DisplayHelper.toString(get().getMaximalSwitchTime(), true),
					DisplayHelper.toString(upperBound, false));
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
