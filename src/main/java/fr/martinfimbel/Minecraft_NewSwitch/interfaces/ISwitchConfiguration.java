package fr.martinfimbel.Minecraft_NewSwitch.interfaces;

import java.time.LocalTime;

import fr.pederobien.minecraftgameplateform.interfaces.element.IGameBorderConfiguration;

public interface ISwitchConfiguration extends IGameBorderConfiguration {

	/**
	 * While the game time is less than this time, if a player dies, it will respawn in game mode survival and keep its inventory.
	 * Once this time is exceed, a player will respawn in spectator mode and drop its inventory if it dies.
	 * 
	 * @return The time after which a player respawn in spectator mode.
	 */
	LocalTime getPlayerDontReviveTime();

	/**
	 * While the game time is less than this time, if a player dies, it will respawn in game mode survival and keep its inventory.
	 * Once this time is exceed, a player will respawn in spectator mode and drop its inventory if it dies.
	 * 
	 * @param playerDontReviveTime The time after which a player respawn in spectator mode.
	 */
	void setPlayerDontReviveTime(LocalTime playerDontReviveTime);

	/**
	 * Define the number of player(s) switched between teams throughout the duration of the game.
	 * 
	 * @param parseInt The number you choose.
	 */
	void setNumberOfPlayerSwitchable(int parseInt);

	/**
	 * Define the number of player(s) switched between teams throughout the duration of the game.
	 * 
	 * @return The chosen number
	 */
	String getNumberOfPlayerSwitchable();

	/**
	 * Define if the switch is enabled or not when the borders are moving
	 * 
	 * @param letter
	 */
	void setSwitchAfterBorderMoves(String letter);

	/**
	 * Define if the switch is enabled or not when the borders are moving
	 * 
	 * @return if switch is enabled or not
	 */
	Object getSwitchAfterBorderMoves();
}
