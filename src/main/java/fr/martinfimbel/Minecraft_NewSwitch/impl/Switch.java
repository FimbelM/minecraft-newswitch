package fr.martinfimbel.Minecraft_NewSwitch.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.SWPlugin;
import fr.martinfimbel.Minecraft_NewSwitch.impl.state.InGameState;
import fr.martinfimbel.Minecraft_NewSwitch.impl.state.InitialState;
import fr.martinfimbel.Minecraft_NewSwitch.impl.state.StartState;
import fr.martinfimbel.Minecraft_NewSwitch.impl.state.StopState;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitch;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchObjective;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.state.IGameState;
import fr.pederobien.minecraftdictionary.impl.MinecraftMessageEvent;
import fr.pederobien.minecraftgameplateform.interfaces.element.IEventListener;
import fr.pederobien.minecraftgameplateform.utils.Plateform;
import fr.pederobien.minecraftmanagers.MessageManager;
import fr.pederobien.minecraftmanagers.PlayerManager;

public class Switch implements ISwitch {
	private IGameState initialState, startState, inGameState, stopState, current;
	private ISwitchConfiguration configuration;

	private List<ISwitchObjective> objectives;

	public Switch(ISwitchConfiguration configuration) {
		this.configuration = configuration;

		initialState = new InitialState(this);
		startState = new StartState(this);
		inGameState = new InGameState(this);
		stopState = new StopState(this);
		current = initialState;

		objectives = new ArrayList<ISwitchObjective>();
	}

	@Override
	public boolean initiate() {
		return current.initiate();
	}

	@Override
	public void start() {
		current.start();
	}

	@Override
	public void stop() {
		current.stop();
	}

	@Override
	public void pause() {
		current.pause();
	}

	@Override
	public void relaunch() {
		current.relaunch();
	}

	@Override
	public IEventListener getListener() {
		return current.getListener();
	}

	@Override
	public void onPvpEnabled() {
		PlayerManager.getPlayers().parallel().forEach(player -> {
			MessageManager.sendMessage(player, Plateform.getNotificationCenter().getMessage(new MinecraftMessageEvent(player, ESwitchMessageCode.PVP_ENABLED)));
		});
	}

	@Override
	public boolean isRunning() {
		return current == inGameState;
	}

	@Override
	public IGameState getCurrentState() {
		return current;
	}

	@Override
	public IGameState setCurrentState(IGameState current) {
		this.current.getListener().setActivated(false);
		current.getListener().register(Plateform.getPluginHelper().getPlugin(SWPlugin.NAME).get());
		current.getListener().setActivated(true);
		return this.current = current;
	}

	@Override
	public IGameState getInitialState() {
		return initialState;
	}

	@Override
	public IGameState getStartState() {
		return startState;
	}

	@Override
	public IGameState getInGameState() {
		return inGameState;
	}

	@Override
	public IGameState getStopState() {
		return stopState;
	}

	@Override
	public int getCountDown() {
		return current.getCountDown();
	}

	@Override
	public int getCurrentCountDown() {
		return current.getCurrentCountDown();
	}

	@Override
	public void onTime(LocalTime time) {
		current.onTime(time);
	}

	@Override
	public void onCountDownTime(LocalTime currentTime) {
		current.onCountDownTime(currentTime);
	}

	@Override
	public LocalTime getNextNotifiedTime() {
		return current.getNextNotifiedTime();
	}

	@Override
	public ISwitchConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	public void createObjective(Scoreboard scoreboard, Player player) {
		ISwitchObjective objective = new SwitchObjective(getPlugin(), player, "Side bar", "Switch", getConfiguration());
		objective.setScoreboard(scoreboard);
		objectives.add(objective);
		Plateform.getObjectiveUpdater().register(objective);
	}

	@Override
	public List<ISwitchObjective> getObjectives() {
		return Collections.unmodifiableList(objectives);
	}

	/**
	 * This is a convenient method and is equivalent to <code>Plateform.getPluginManager().getPlugin(HGPlugin.NAME).get()</code>.
	 * 
	 * @return This plugin registered in the plateform.
	 */
	private Plugin getPlugin() {
		return Plateform.getPluginHelper().getPlugin(SWPlugin.NAME).get();
	}
}
