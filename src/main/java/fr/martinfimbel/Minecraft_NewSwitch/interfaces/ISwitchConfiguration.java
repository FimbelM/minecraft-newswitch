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

	/**
	 * Define if the switch is activated even if it remains only one player in a team
	 * 
	 * @param letter (y,Y,n or N)
	 */
	void setOnePlayerSwitch(String letter);

	/**
	 * Reads if the switch is activated even if it remains only one player in a team
	 * 
	 * @return letter (y,Y,n or N)
	 */
	Object getOnePlayerSwitch();

	/**
	 * Define the time at which first switch occurs
	 * 
	 * @param parse hh:mm:ss
	 */
	void setStartSwitchTime(LocalTime parse);

	/**
	 * Reads the time at which first switch occurs
	 * 
	 * @return hh:mm:ss
	 */
	LocalTime getStartSwitchTime();

	/**
	 * Define the time at which every switch following the first one will occur periodically
	 * 
	 * @param parse Time hh:mm:ss
	 */
	void setPeriodSwitchTime(LocalTime parse);

	/**
	 * Reads the time at which every switch following the first one will occur periodically
	 * 
	 * @return parse hh:mm:ss
	 */
	LocalTime getPeriodSwitchTime();

	/**
	 * Define if switch are going to be periodic or randomly placed
	 * 
	 * @param letter Y, y, N or n
	 */
	void setRandomSwitch(String letter);

	/**
	 * Define if switch are going to be periodic or randomly placed
	 * 
	 * @param letter Y, y, N or n
	 */
	Object getRandomSwitch();
}
