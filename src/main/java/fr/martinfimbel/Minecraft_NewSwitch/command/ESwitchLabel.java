package fr.martinfimbel.Minecraft_NewSwitch.command;

import fr.pederobien.minecraftgameplateform.interfaces.element.ILabel;

public enum ESwitchLabel implements ILabel {
	PLAYER_DONT_REVIVE_TIME("playerDontReviveTime");

	private String label;

	private ESwitchLabel(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
