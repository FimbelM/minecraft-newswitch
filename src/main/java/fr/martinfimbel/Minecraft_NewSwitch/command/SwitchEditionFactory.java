package fr.martinfimbel.Minecraft_NewSwitch.command;

import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IMapPersistenceEdition;

public class SwitchEditionFactory {
	/**
	 * @return An edition to create a new hunger game configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> newSwitch() {
		return new NewSwitch();
	}

	/**
	 * @return An edition to rename an hunger game configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> renameSwitch() {
		return new RenameSwitch();
	}

	/**
	 * @return An edition to save an hunger game configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> saveSwitch() {
		return new SaveSwitch();
	}

	/**
	 * @return An edition to display the name of all registered hunger game configurations.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> listSwitch() {
		return new ListSwitch();
	}

	/**
	 * @return An edition to delete the file of an hunger game configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> deleteSwitch() {
		return new DeleteSwitch();
	}

	/**
	 * @return An edition to display the characteristics of the current hunger game configuration.
	 */
	public static IMapPersistenceEdition<ISwitchConfiguration> currentSwitch() {
		return new DetailsSwitch();
	}

	/**
	 * @return An edition to load an hunger game configuration.
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
}
