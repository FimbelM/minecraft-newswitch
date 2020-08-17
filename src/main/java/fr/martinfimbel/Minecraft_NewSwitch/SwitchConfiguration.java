package fr.martinfimbel.Minecraft_NewSwitch;

import java.time.LocalTime;
import java.util.StringJoiner;

import fr.martinfimbel.Minecraft_NewSwitch.impl.Switch;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftdevelopmenttoolkit.utils.DisplayHelper;
import fr.pederobien.minecraftgameplateform.border.BorderConfiguration;
import fr.pederobien.minecraftgameplateform.impl.element.AbstractGameBorderConfiguration;
import fr.pederobien.minecraftgameplateform.impl.element.PlateformTeam;
import fr.pederobien.minecraftgameplateform.interfaces.element.IGame;
import fr.pederobien.minecraftgameplateform.interfaces.element.ITeam;
import fr.pederobien.minecraftgameplateform.utils.EColor;
import fr.pederobien.minecraftmanagers.WorldManager;

public class SwitchConfiguration extends AbstractGameBorderConfiguration implements ISwitchConfiguration {
	private static final LocalTime DEFAULT_PLAYER_DONT_REVIVE_TIME = LocalTime.of(0, 0, 0);

	private IGame game;
	private LocalTime playerDontReviveTime;

	public SwitchConfiguration(String name) {
		super(name);
		game = new Switch(this);

		add(new BorderConfiguration("DefaultHGOverworldBorder"));
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
		return joiner.toString();
	}

	@Override
	public void setNumberOfPlayerSwitchable(int parseInt) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNumberOfPlayerSwitchable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSwitchAfterBorderMoves(String letter) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getSwitchAfterBorderMoves() {
		// TODO Auto-generated method stub
		return null;
	}
}
