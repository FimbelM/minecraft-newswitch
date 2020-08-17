package fr.martinfimbel.Minecraft_NewSwitch.persistence;

public enum SwitchXmlTag {
	NAME("name"), BORDERS("borders"), BORDER("border"), TIMES("times"), PVP("pvp"), PLAYER_DONT_REVIVE("playerDontRevive"), TEAMS("teams"), TEAM("team"), COLOR("color");

	private String name;

	private SwitchXmlTag(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
