package fr.martinfimbel.Minecraft_NewSwitch.impl;

import java.util.function.Function;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;

import fr.martinfimbel.Minecraft_NewSwitch.entries.TimeBeforePVPEntry;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchObjective;
import fr.pederobien.minecraftborder.entries.WorldBorderSizeCountDownEntry;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.entries.simple.CenterEntry;
import fr.pederobien.minecraftgameplateform.entries.simple.LocationEntry;
import fr.pederobien.minecraftgameplateform.entries.simple.TeamPlayerOnModeEntry;
import fr.pederobien.minecraftgameplateform.entries.updaters.TimeTaskObserverEntryUpdater;
import fr.pederobien.minecraftgameplateform.impl.element.GameObjective;
import fr.pederobien.minecraftgameplateform.interfaces.element.ITeam;
import fr.pederobien.minecraftmanagers.WorldManager;
import fr.pederobien.minecraftscoreboards.impl.updaters.UpdatersFactory;
import fr.pederobien.minecraftscoreboards.interfaces.IEntry;

public class SwitchObjective extends GameObjective<ISwitchConfiguration> implements ISwitchObjective {
	private int score;

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin        The plugin used to update this objective.
	 * @param player        The player associated to this objective. This player is used to display its informations.
	 * @param name          The name of this objective.
	 * @param displayName   The name displayed on the given player score board.
	 * @param criteria      The criteria tracked by this objective.
	 * @param displaySlot   The slot where this objective is displayed on player screen.
	 * @param configuration The configuration associated to this objective.
	 */
	public SwitchObjective(Plugin plugin, Player player, String name, String displayName, String criteria, DisplaySlot displaySlot, ISwitchConfiguration configuration) {
		super(plugin, player, name, displayName, criteria, displaySlot, configuration);
		score = 0;
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin        The plugin used to update this objective.
	 * @param player        The player associated to this objective. This player is used to display its informations.
	 * @param name          The name of this objective.
	 * @param displayName   The name displayed on the given player score board.
	 * @param displaySlot   The slot where this objective is displayed on player screen.
	 * @param configuration The configuration associated to this objective.
	 */
	public SwitchObjective(Plugin plugin, Player player, String name, String displayName, DisplaySlot displaySlot, ISwitchConfiguration configuration) {
		this(plugin, player, name, displayName, "dummy", displaySlot, configuration);
	}

	/**
	 * Create an empty objective based on the given parameters.
	 * 
	 * @param plugin        The plugin used to update this objective.
	 * @param player        The player associated to this objective. This player is used to display its informations.
	 * @param name          The name of this objective.
	 * @param displayName   The name displayed on the given player score board.
	 * @param displaySlot   The slot where this objective is displayed on player screen.
	 * @param configuration The configuration associated to this objective.
	 */
	public SwitchObjective(Plugin plugin, Player player, String name, String displayName, ISwitchConfiguration configuration) {
		this(plugin, player, name, displayName, DisplaySlot.SIDEBAR, configuration);
	}

	@Override
	public void initialize() {
		initiate();
		super.initialize();
	}

	@Override
	public void initiate() {
		IBorderConfiguration overworld = getConfiguration().getBorder(WorldManager.OVERWORLD).get();
		add(score -> new LocationEntry(score, overworld.getBorderCenter()).addUpdater(UpdatersFactory.playerMove().condition(e -> e.getPlayer().equals(getPlayer()))));
		add(score -> new CenterEntry(score, getConfiguration().getBorder(WorldManager.OVERWORLD).get().getBorderCenter())
				.addUpdater(UpdatersFactory.playerMove().condition(e -> e.getPlayer().equals(getPlayer()))));
		emptyEntry(score--);
		add(score -> new TimeBeforePVPEntry(score, getConfiguration()).addUpdater(new TimeTaskObserverEntryUpdater()));
		emptyEntry(score--);
		for (IBorderConfiguration border : getConfiguration().getBorders())
			add(score -> new WorldBorderSizeCountDownEntry(score, border, "#").setDisplayHalfSize(true).addUpdater(new TimeTaskObserverEntryUpdater()));
	}

	@Override
	public void addTeams() {
		emptyEntry(score--);
		for (ITeam team : getConfiguration().getTeams())
			if (!team.getPlayers().isEmpty())
				add(score -> new TeamPlayerOnModeEntry(score, team, GameMode.SURVIVAL, true).addUpdater(UpdatersFactory.playerGameModeChange()));
	}

	private void add(Function<Integer, IEntry> constructor) {
		addEntry(constructor.apply(score));
		score--;
	}

}
