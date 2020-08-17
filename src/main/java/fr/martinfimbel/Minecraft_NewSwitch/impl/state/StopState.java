package fr.martinfimbel.Minecraft_NewSwitch.impl.state;

import org.bukkit.GameMode;

import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitch;
import fr.pederobien.minecraftgameplateform.helpers.TeamHelper;
import fr.pederobien.minecraftgameplateform.utils.Plateform;
import fr.pederobien.minecraftmanagers.PlayerManager;

public class StopState extends AbstractState {

	public StopState(ISwitch game) {
		super(game);
	}

	@Override
	public void stop() {
		getConfiguration().getBorders().forEach(border -> border.reset());
		TeamHelper.removeTeamsFromServer(getConfiguration().getTeams());
		PlayerManager.setGameModeOfAllPlayers(GameMode.CREATIVE);
		Plateform.getObjectiveUpdater().stop(true);
		getGame().setCurrentState(getGame().getInitialState());
	}
}
