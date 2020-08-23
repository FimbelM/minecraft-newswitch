package fr.martinfimbel.Minecraft_NewSwitch.command;

import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public enum ESwitchLabel implements ILabel {
	PLAYER_DONT_REVIVE_TIME("playerDontReviveTime"), NUMBER_OF_SWITCHABLE_PLAYER_PER_TEAM("numberOfSwitchablePlayerPerTeam"),
	SWITCH_AFTER_BORDER_MOVES("switchAfterBorderMoves"), ONE_PLAYER_SWITCH("onePlayerSwitch"), START_SWITCH_TIME("startSwitchTime"), SWITCH_TIME("switchTime");

	private String label;

	private ESwitchLabel(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
