package fr.martinfimbel.Minecraft_NewSwitch.impl.state;

import java.time.LocalTime;

import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitch;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.IObsTimeLine;
import fr.pederobien.minecraftgameplateform.utils.Plateform;
import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.ScoreboardManager;
import fr.pederobien.minecraftmanagers.WorldManager;

public class InitialState extends AbstractState {
	private IObsTimeLine teamObjective;

	public InitialState(ISwitch game) {
		super(game);
		teamObjective = new TeamObjective();
	}

	@Override
	public boolean initiate() {
		getConfiguration().getBorders().forEach(border -> border.apply(Plateform.getTimeLine()));
		Plateform.getTimeLine().addObserver(getConfiguration().getPlayerDontReviveTime(), getGame());
		Plateform.getTimeLine().addObserver(getConfiguration().getBorder(WorldManager.OVERWORLD).get().getStartTime(), teamObjective);
		PlayerManager.getPlayers().forEach(player -> getGame().createObjective(ScoreboardManager.createScoreboard(), player));
		return true;
	}

	@Override
	public void start() {
		getGame().setCurrentState(getGame().getStartState()).start();
	}

	private class TeamObjective implements IObsTimeLine {

		@Override
		public void timeChanged(LocalTime time) {
			getGame().getObjectives().forEach(obj -> obj.addTeams());
		}
	}
}
