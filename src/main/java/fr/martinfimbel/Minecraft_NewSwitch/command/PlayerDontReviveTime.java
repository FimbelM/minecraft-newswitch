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

public class PlayerDontReviveTime extends AbstractLabelEdition<ISwitchConfiguration> {

	protected PlayerDontReviveTime() {
		super(ESwitchLabel.PLAYER_DONT_REVIVE_TIME, ESwitchMessageCode.PLAYER_DONT_REVIVE_TIME__EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			get().setPlayerDontReviveTime(LocalTime.parse(args[0]));
			if (get().getPlayerDontReviveTime().equals(LocalTime.of(0, 0, 0)))
				sendMessageToSender(sender, ESwitchMessageCode.PLAYER_DONT_REVIVE_TIME__FROM_THE_BEGINNING);
			else
				sendMessageToSender(sender, ESwitchMessageCode.PLAYER_DONT_REVIVE_TIME__TIME_DEFINED, DisplayHelper.toString(get().getPlayerDontReviveTime(), false));
			return true;
		} catch (IndexOutOfBoundsException e) {
			sendMessageToSender(sender, ESwitchMessageCode.PLAYER_DONT_REVIVE_TIME__TIME_IS_MISSING);
			return false;
		} catch (DateTimeParseException e) {
			sendMessageToSender(sender, ECommonMessageCode.COMMON_BAD_TIME_FORMAT);
			return false;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return Arrays.asList(getMessageFromDictionary(sender, ECommonMessageCode.COMMON_TIME_TAB_COMPLETE));
	}
}
