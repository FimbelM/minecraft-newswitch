package fr.martinfimbel.Minecraft_NewSwitch.interfaces;

import fr.pederobien.minecraftgameplateform.interfaces.element.IGameObjective;

public interface ISwitchObjective extends IGameObjective<ISwitchConfiguration> {

	/**
	 * Display not empty teams and the number of player in game mode Survival.
	 */
	void addTeams();
}
