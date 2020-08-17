package fr.martinfimbel.Minecraft_NewSwitch;

import org.bukkit.plugin.java.JavaPlugin;

import fr.martinfimbel.Minecraft_NewSwitch.command.SwitchParent;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.commands.AbstractParentCommand;

public class SwitchCommand extends AbstractParentCommand<ISwitchConfiguration> {

	protected SwitchCommand(JavaPlugin plugin) {
		super(plugin, new SwitchParent(plugin));
	}
}
