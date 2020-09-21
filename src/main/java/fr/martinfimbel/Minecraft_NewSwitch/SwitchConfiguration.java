package fr.martinfimbel.Minecraft_NewSwitch;

import java.time.LocalTime;
import java.util.StringJoiner;

import fr.martinfimbel.Minecraft_NewSwitch.impl.Switch;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftborder.impl.AbstractGameBorderConfiguration;
import fr.pederobien.minecraftborder.impl.BorderConfiguration;
import fr.pederobien.minecraftdevelopmenttoolkit.utils.DisplayHelper;
import fr.pederobien.minecraftgameplateform.impl.element.PlateformTeam;
import fr.pederobien.minecraftgameplateform.interfaces.element.IGame;
import fr.pederobien.minecraftgameplateform.interfaces.element.ITeam;
import fr.pederobien.minecraftmanagers.EColor;
import fr.pederobien.minecraftmanagers.WorldManager;

public class SwitchConfiguration extends AbstractGameBorderConfiguration implements ISwitchConfiguration {
	private static final LocalTime DEFAULT_PLAYER_DONT_REVIVE_TIME = LocalTime.of(0, 0, 0);
	private static final LocalTime DEFAULT_START_SWITCH_TIME = LocalTime.of(0, 20, 0);
	private static final LocalTime DEFAULT_PERIODIC_SWITCH_TIME = LocalTime.of(0, 20, 0);
	private static final LocalTime DEFAULT_SWITCH_COUNTDOWN = LocalTime.of(0, 0, 10);
	private static final LocalTime DEFAULT_MINIMAL_RANDOM_SWITCH_TIME = LocalTime.of(0, 10, 0);
	private static final LocalTime DEFAULT_MAXIMAL_RANDOM_SWITCH_TIME = LocalTime.of(0, 10, 0);
	private static final Boolean DEFAULT_SWITCH_AFTER_BORDER_MOVES = false;
	private static final Boolean DEFAULT_RANDOM_SWITCH = false;
	private static final Boolean DEFAULT_ONE_PLAYER_SWITCH = false;
	private static final Integer DEFAULT_NUMBER_OF_PLAYER_SWITCHABLE = 1;
	private static final Integer DEFAULT_AVERAGE_NUMBER_OF_SWITCH = 5;

	private IGame game;
	private LocalTime playerDontReviveTime, startSwitchTime, periodicSwitchTime, beforeSwitchCountdown, minimalRandomSwitchTime, maximalRandomSwitchTime;
	private Integer numberOfPlayerSwitchable, averageNumberOfSwitch;
	private Boolean isSwitchAfterBorderMoves, isRandomSwitch, isOnePlayerSwitch;

	public SwitchConfiguration(String name) {
		super(name);
		game = new Switch(this);

		add(new BorderConfiguration("DefaultSWOverworldBorder"));
		add(PlateformTeam.of("Jedi", EColor.DARK_AQUA));
		add(PlateformTeam.of("Clones", EColor.GREEN));
		add(PlateformTeam.of("Sith", EColor.DARK_RED));
		add(PlateformTeam.of("Resistance", EColor.GOLD));
	}

	@Override
	public IGame getGame() {
		return game;
	}

	@Override
	public LocalTime getPlayerDontReviveTime() {
		return playerDontReviveTime == null ? DEFAULT_PLAYER_DONT_REVIVE_TIME : playerDontReviveTime;
	}

	@Override
	public void setPlayerDontReviveTime(LocalTime playerDontReviveTime) {
		this.playerDontReviveTime = playerDontReviveTime;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner("\n");
		joiner.add("Name : " + getName());
		joiner.add("Teams :" + (getTeams().isEmpty() ? " none" : ""));
		for (ITeam team : getTeams())
			joiner.add(team.toString());

		joiner.add("Borders :" + (getBorders().isEmpty() ? "none" : ""));
		if (!getBorders().isEmpty()) {
			joiner.add(getWorldBorders(WorldManager.OVERWORLD));
			joiner.add(getWorldBorders(WorldManager.NETHER_WORLD));
			joiner.add(getWorldBorders(WorldManager.END_WORLD));
		}
		joiner.add("Player don't revive time : " + display(playerDontReviveTime, DisplayHelper.toString(getPlayerDontReviveTime(), true)));
		joiner.add("Pvp time : " + DisplayHelper.toString(getPvpTime(), true));
		joiner.add("Start switch time : " + display(startSwitchTime, DisplayHelper.toString(getStartSwitchTime(), true)));
		joiner.add("Periodic switch time : " + display(periodicSwitchTime, DisplayHelper.toString(getPeriodSwitchTime(), true)));
		joiner.add("Number of Switchable player : " + display(numberOfPlayerSwitchable, "" + getNumberOfPlayerSwitchable()));
		joiner.add("One player switch : " + display(isOnePlayerSwitch, "" + isOnePlayerSwitchActivated()));
		joiner.add("Switch after border moves : " + display(isSwitchAfterBorderMoves, "" + isSwitchAfterBorderMovesActivated()));
		joiner.add("Random switch : " + display(isRandomSwitch, "" + isRandomSwitchActivated()));
		joiner.add("Number of seconds warning before switch : " + display(beforeSwitchCountdown, "" + getSwitchCountdownTime()));
		joiner.add("Average switch number : " + display(averageNumberOfSwitch, "" + getAverageNumberOfSwitch()));
		joiner.add("Minimal time without switch when random : " + display(minimalRandomSwitchTime, "" + getMinimalSwitchTime()));
		joiner.add("Maximal time with switch when random : " + display(maximalRandomSwitchTime, "" + getMaximalSwitchTime()));
		return joiner.toString();
	}

	@Override
	public void setNumberOfPlayerSwitchable(int parseInt) {
		this.numberOfPlayerSwitchable = parseInt;
	}

	@Override
	public Integer getNumberOfPlayerSwitchable() {
		return numberOfPlayerSwitchable == null ? DEFAULT_NUMBER_OF_PLAYER_SWITCHABLE : numberOfPlayerSwitchable;
	}

	@Override
	public void setStartSwitchTime(LocalTime startSwitchTime) {
		this.startSwitchTime = startSwitchTime;
	}

	@Override
	public LocalTime getStartSwitchTime() {
		return startSwitchTime == null ? DEFAULT_START_SWITCH_TIME : startSwitchTime;
	}

	@Override
	public void setPeriodSwitchTime(LocalTime switchTime) {
		this.periodicSwitchTime = switchTime;
	}

	@Override
	public LocalTime getPeriodSwitchTime() {
		return periodicSwitchTime == null ? DEFAULT_PERIODIC_SWITCH_TIME : periodicSwitchTime;
	}

	@Override
	public void setRandomSwitch(boolean randomSwitch) {
		this.isRandomSwitch = randomSwitch;
	}

	@Override
	public boolean isRandomSwitchActivated() {
		return isRandomSwitch == null ? DEFAULT_RANDOM_SWITCH : isRandomSwitch;
	}

	@Override
	public void setSwitchAfterBorderMovesActivated(boolean SwitchAfterBorderMovesActivated) {
		this.isSwitchAfterBorderMoves = SwitchAfterBorderMovesActivated;
	}

	@Override
	public boolean isSwitchAfterBorderMovesActivated() {
		return isSwitchAfterBorderMoves == null ? DEFAULT_SWITCH_AFTER_BORDER_MOVES : isSwitchAfterBorderMoves;
	}

	@Override
	public void setOnePlayerSwitch(boolean switchIfAlone) {
		this.isOnePlayerSwitch = switchIfAlone;
	}

	@Override
	public boolean isOnePlayerSwitchActivated() {
		return isOnePlayerSwitch == null ? DEFAULT_ONE_PLAYER_SWITCH : isOnePlayerSwitch;
	}

	@Override
	public void setSwitchCountdownTime(LocalTime numberOfSeconds) {
		this.beforeSwitchCountdown = numberOfSeconds;

	}

	@Override
	public LocalTime getSwitchCountdownTime() {
		return beforeSwitchCountdown == null ? DEFAULT_SWITCH_COUNTDOWN : beforeSwitchCountdown;
	}

	@Override
	public void setAverageNumberOfSwitch(int averageNumberOfSwitch) {
		this.averageNumberOfSwitch = averageNumberOfSwitch;
	}

	@Override
	public int getAverageNumberOfSwitch() {
		return averageNumberOfSwitch == null ? DEFAULT_AVERAGE_NUMBER_OF_SWITCH : averageNumberOfSwitch;
	}

	@Override
	public void setMinimalSwitchTime(LocalTime minimalSwitchTime) {
		this.minimalRandomSwitchTime = minimalSwitchTime;
	}

	@Override
	public LocalTime getMinimalSwitchTime() {
		return minimalRandomSwitchTime == null ? DEFAULT_MINIMAL_RANDOM_SWITCH_TIME : minimalRandomSwitchTime;
	}

	@Override
	public void setMaximalSwitchTime(LocalTime maximalSwitchTime) {
		this.maximalRandomSwitchTime = maximalSwitchTime;

	}

	@Override
	public LocalTime getMaximalSwitchTime() {
		return maximalRandomSwitchTime == null ? DEFAULT_MAXIMAL_RANDOM_SWITCH_TIME : maximalRandomSwitchTime;
	}

}
