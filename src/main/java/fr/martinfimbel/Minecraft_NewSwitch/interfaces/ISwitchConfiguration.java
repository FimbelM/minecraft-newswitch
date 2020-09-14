package fr.martinfimbel.Minecraft_NewSwitch.interfaces;

import java.time.LocalTime;

import fr.pederobien.minecraftborder.interfaces.IGameBorderConfiguration;

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
	 * @param numberOfPlayerSwitchable The number you choose.
	 */
	void setNumberOfPlayerSwitchable(int numberOfPlayerSwitchable);

	/**
	 * Define the number of player(s) switched between teams throughout the duration of the game.
	 * 
	 * @return The chosen number
	 */
	Integer getNumberOfPlayerSwitchable();

	/**
	 * Define if the switch is enabled or not when the borders are moving
	 * 
	 * @param activatedSwitchAfterBorderMoves true or false
	 */
	void setSwitchAfterBorderMovesActivated(boolean SwitchAfterBorderMovesActivated);

	/**
	 * Define if the switch is enabled or not when the borders are moving
	 * 
	 * @return if switch after border moves is enabled or not
	 */
	boolean isSwitchAfterBorderMovesActivated();

	/**
	 * Define if the switch is activated even if it remains only one player in a team
	 * 
	 * @param switchIfAlone True or false
	 */
	void setOnePlayerSwitch(boolean switchIfAlone);

	/**
	 * Reads if the switch is activated even if it remains only one player in a team
	 * 
	 * @return if switch if player alone in a team is enabled or not
	 */
	boolean isOnePlayerSwitchActivated();

	/**
	 * Define the time at which first switch occurs
	 * 
	 * @param startSwitchTime hh:mm:ss
	 */
	void setStartSwitchTime(LocalTime startSwitchTime);

	/**
	 * Reads the time at which first switch occurs
	 * 
	 * @return startSwitchTime the time at which first switch occurs
	 */
	LocalTime getStartSwitchTime();

	/**
	 * Define the time at which every switch following the first one will occur periodically
	 * 
	 * @param switchTime hh:mm:ss
	 */
	void setPeriodSwitchTime(LocalTime switchTime);

	/**
	 * Reads the time at which every switch following the first one will occur periodically
	 * 
	 * @return switchTime hh:mm:ss
	 */
	LocalTime getPeriodSwitchTime();

	/**
	 * Define if switch are going to be periodic or randomly placed
	 * 
	 * @param randomSwitch true or false
	 */
	void setRandomSwitch(boolean randomSwitch);

	/**
	 * Define if switch are going to be periodic or randomly placed
	 * 
	 * @return if switch is going to be periodic or random
	 */
	boolean isRandomSwitchActivated();
}
