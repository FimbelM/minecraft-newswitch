package fr.martinfimbel.Minecraft_NewSwitch.command;

import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IMapPersistenceEdition;

public class SwitchEditionFactory {

	/**
	 * @return An edition to create a new switch configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> newSwitch() {
		return new NewSwitch();
	}

	/**
	 * @return An edition to rename an switch configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> renameSwitch() {
		return new RenameSwitch();
	}

	/**
	 * @return An edition to save an switch configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> saveSwitch() {
		return new SaveSwitch();
	}

	/**
	 * @return An edition to display the name of all registered switch configurations.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> listSwitch() {
		return new ListSwitch();
	}

	/**
	 * @return An edition to delete the file of an switch configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> deleteSwitch() {
		return new DeleteSwitch();
	}

	/**
	 * @return An edition to display the characteristics of the current switch configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> currentSwitch() {
		return new DetailsSwitch();
	}

	/**
	 * @return An edition to load an switch configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> loadSwitch() {
		return new LoadSwitch();
	}

	/**
	 * @return An edition to set the time after which players respawn in spectator mode.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> playerDontReviveTime() {
		return new PlayerDontReviveTime();
	}

	/**
	 * @return An edition to set the number of players switchable per team.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> numberOfSwitchablePlayer() {
		return new NumberOfSwitchablePlayer();
	}

	/**
	 * @return An edition to set if one single player in a team can be switched with other players.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> onePlayerSwitch() {
		return new OnePlayerSwitch();
	}

	/**
	 * @return An edition to set if the times and number of switch will be random or periodic.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> randomSwitch() {
		return new RandomSwitch();
	}

	/**
	 * @return An edition to set the time of the first switch.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> startSwitchTime() {
		return new StartSwitchTime();
	}

	/**
	 * @return An edition to set if there will be switch after border moves.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> switchAfterBorderMoves() {
		return new SwitchAfterBorderMoves();
	}

	/**
	 * @return An edition to set the time between switches in a periodic type game.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> periodicSwitchTime() {
		return new PeriodicSwitchTime();
	}
}
