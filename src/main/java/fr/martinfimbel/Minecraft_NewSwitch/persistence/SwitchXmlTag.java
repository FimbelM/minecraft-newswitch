package fr.martinfimbel.Minecraft_NewSwitch.persistence;

public enum SwitchXmlTag {
	NAME("name"), BORDERS("borders"), BORDER("border"), TIMES("times"), PVP("pvp"), PLAYER_DONT_REVIVE("playerDontRevive"), TEAMS("teams"), TEAM("team"), COLOR("color"),
	START_SWITCH_TIME("startSwitchTime"), PERIODIC_SWITCH_TIME("periodSwitchTime"), MINIMUM_SWITCH_TIME("lowerBound"), MAXIMUM_SWITCH_TIME("upperBound"),
	SWITCH_COUNTDOWN_TIME("switchCountdown"), INTEGER("integer"), AVERAGE_NUMBER("averageNumber"), NUMBER_OF_SWITCHABLE_PLAYERS("numberOfPlayerSwitchable"),
	BOOLEANS("booleans"), ONE_PLAYER_SWITCH("onePlayerSwitch"), RANDOM_SWITCH("randomSwitch"), SWITCH_AFTER_BORDER_MOVES("switchAfterBorderMoves");

	private String name;

	private SwitchXmlTag(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
