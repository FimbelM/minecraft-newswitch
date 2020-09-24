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

	/**
	 * Define the countdown time before a switch where player will be warned every second
	 * 
	 * @param numberOfSeconds the number of seconds 1, 2, 3, ...
	 */
	void setSwitchCountdownTime(LocalTime numberOfSeconds);

	/**
	 * Define the countdown time before a switch where player will be warned every second
	 * 
	 * @return the defined or default value
	 */
	LocalTime getSwitchCountdownTime();

	/**
	 * Define a number that will be used as a parameter in Poisson Law to randomly generate a number of switch This number of switch
	 * will be unknown by the players
	 * 
	 * @param averageNumberOfSwitch the parameter chosen in entry
	 */
	void setAverageNumberOfSwitch(int averageNumberOfSwitch);

	/**
	 * Define a number that will be used as a parameter in Poisson Law to randomly generate a number of switch This number of switch
	 * will be unknown by the players
	 * 
	 * @return the parameter chosen in entry
	 */
	int getAverageNumberOfSwitch();

	/**
	 * Define the minimal time amount, if switches are randomly occurring, after which switch can occur
	 * 
	 * @param minimalSwitchTime hh:mm:ss
	 */
	void setMinimalSwitchTime(LocalTime minimalSwitchTime);

	/**
	 * Define the lower time bound, if switches are randomly occurring, before which switch can not occur
	 * 
	 * @return the defined or default value
	 */
	LocalTime getMinimalSwitchTime();

	/**
	 * Define a time that will be used to calculate the upper time bound, if switches are randomly occurring, after which switch can
	 * not occur The calculation of upper time bound will be this one : BorderStartTime - maximalSwitchTime = upperBoundTime
	 * 
	 * @param maximalSwitchTime hh:mm:ss
	 */
	void setMaximalSwitchTime(LocalTime maximalSwitchTime);

	/**
	 * Define a time that will be used to calculate the upper time bound, if switches are randomly occurring, after which switch can
	 * not occur
	 * 
	 * @return the defined or default value
	 */
	LocalTime getMaximalSwitchTime();

	/**
	 * Define if there is only two players in the whole game that are switched, or if one player is permuted with another one in every
	 * team
	 * 
	 * @param onePermutationPerSwitch as defined or default value
	 */
	void setOnePermutationPerSwitchActivated(boolean onePermutationPerSwitch);

	/**
	 * Define if there is only two players in the whole game that are switched, or if one player is permuted with another one in every
	 * team
	 * 
	 * @param onePermutationPerSwitch as defined or default value
	 */
	boolean isOnePermutationPerSwitchActivated();

}
