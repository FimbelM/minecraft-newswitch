package fr.martinfimbel.Minecraft_NewSwitch.entries;

import java.time.LocalTime;

import org.bukkit.entity.Player;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.minecraftgameplateform.entries.simple.TimeTaskEntry;

public class TimeBeforePVPEntry extends TimeTaskEntry {
	private ISwitchConfiguration configuration;
	private LocalTime timeBeforePvP;

	/**
	 * Create an entry that displays the time remaining before PvP is activated or if it is, tells it
	 * 
	 * @param score The line number of this entry.
	 * 
	 */
	public TimeBeforePVPEntry(int score, ISwitchConfiguration configuration) {
		super(score);
		this.configuration = configuration;
	}

	@Override
	public LocalTime getTime() {
		return timeBeforePvP = configuration.getPvpTime().minusSeconds(getTask().getGameTime().toSecondOfDay());
	}

	@Override
	protected String updateCurrentValue(Player player) {
		return timeBeforePvP.toSecondOfDay() > 0 ? super.updateCurrentValue(player) : "";
	}

	@Override
	public IMinecraftMessageCode getBeforeAsCode(Player player) {
		return timeBeforePvP.toSecondOfDay() > 0 ? ESwitchMessageCode.ENTRY_PVP_DISABLED : ESwitchMessageCode.ENTRY_PVP_ENABLED;
	}
}
