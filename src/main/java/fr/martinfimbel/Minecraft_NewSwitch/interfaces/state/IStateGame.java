package fr.martinfimbel.Minecraft_NewSwitch.interfaces.state;

import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.ITimeLineObserver;

public interface IStateGame extends ITimeLineObserver {

	IGameState getCurrentState();

	IGameState setCurrentState(IGameState current);

	IGameState getInitialState();

	IGameState getStartState();

	IGameState getInGameState();

	IGameState getStopState();

}
