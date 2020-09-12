package fr.martinfimbel.Minecraft_NewSwitch.entries;

import java.time.LocalTime;

import org.bukkit.entity.Player;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.minecraftgameplateform.entries.simple.TimeTaskEntry;

public class EverySwitchTimeEntry extends TimeTaskEntry {
	private ISwitchConfiguration configuration;
	private LocalTime everySwitchTime;

	protected EverySwitchTimeEntry(int score, ISwitchConfiguration configuration) {
		super(score);
		this.configuration = configuration;
	}

	@Override
	public LocalTime getTime() {
		return everySwitchTime = configuration.getStartSwitchTime().minusSeconds(getTask().getGameTime().toSecondOfDay());
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return everySwitchTime.toSecondOfDay() > 0 ? super.updateCurrentValue(player) : "";
	}

	@Override
	public IMinecraftMessageCode getBeforeAsCode(Player player) {
		return ESwitchMessageCode.ENTRY_TIME_BEFORE_SWITCH;
	}
}
