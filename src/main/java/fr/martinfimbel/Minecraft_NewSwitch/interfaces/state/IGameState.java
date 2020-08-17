package fr.martinfimbel.Minecraft_NewSwitch.interfaces.state;

import fr.pederobien.minecraftgameplateform.interfaces.element.IEventListener;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.ITimeLineObserver;

public interface IGameState extends ITimeLineObserver {

	/**
	 * @return True if the game is successful initiated, false otherwise.
	 */
	boolean initiate();

	/**
	 * Method called to start the game.
	 */
	void start();

	/**
	 * Method called to pause the game.
	 */
	void pause();

	/**
	 * Method called to relaunch the game when it is in pause.
	 */
	void relaunch();

	/**
	 * Method called to stop a game.
	 */
	void stop();

	/**
	 * @return The listener that interact with minecraft events.
	 */
	IEventListener getListener();
}