package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.minecraftgameplateform.impl.editions.AbstractLabelEdition;
import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public class RandomSwitch extends AbstractLabelEdition<ISwitchConfiguration> {

	protected RandomSwitch(ILabel label, IMinecraftMessageCode explanation) {
		super(ESwitchLabel.RANDOM_SWITCH, ESwitchMessageCode.RANDOM_SWITCH_EXPLANATION);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String letter = args[0];
		if (letter.equalsIgnoreCase("Y")) {
			get().setRandomSwitch(letter);
			sendMessageToSender(sender, ESwitchMessageCode.RANDOM_SWITCH_DEFINED, get().getRandomSwitch());
			return true;
		} else if (letter.equalsIgnoreCase("N")) {
			get().setRandomSwitch(letter);
			sendMessageToSender(sender, ESwitchMessageCode.RANDOM_SWITCH_DEFINED, get().getRandomSwitch());
			return true;
		}
		sendMessageToSender(sender, ESwitchMessageCode.RANDOM_SWITCH_WRONG_FORMAT);
		return false;
	}

}
