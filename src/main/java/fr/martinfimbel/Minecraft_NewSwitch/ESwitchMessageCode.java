package fr.martinfimbel.Minecraft_NewSwitch;

import fr.pederobien.minecraftdictionary.impl.Permission;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;

public enum ESwitchMessageCode implements IMinecraftMessageCode {
	SW_EXPLANATION,

	// Code for command new
	NEW_SW__EXPLANATION, NEW_SW__NAME_IS_MISSING, NEW_SW__NAME_ALREADY_TAKEN, NEW_SW__CONFIGURATION_CREATED,

	// Code for command rename
	RENAME_SW__EXPLANATION, RENAME_SW__NAME_IS_MISSING, RENAME_SW__NAME_ALREADY_TAKEN, RENAME_SW__CONFIGURATION_RENAMED,

	// Code for command save
	SAVE_SW__EXPLANATION, SAVE_SW__CONFIGURATION_SAVED,

	// Code for command list
	LIST_SW__EXPLANATION, LIST_SW__NO_REGISTERED_CONFIGURATION, LIST_SW__ONE_REGISTERED_CONFIGURATION, LIST_SW__SEVERAL_ELEMENTS,

	// Code for command delete
	DELETE_SW__EXPLANATION, DELETE_SW__NAME_IS_MISSING, DELETE_SW__DID_NOT_DELETE, DELETE_SW__CONFIGURATION_DELETED,

	// Code for command details
	DETAILS_SW__EXPLANATION, DETAILS_SW__ON_DETAILS,

	// Code for command load
	LOAD_SW__EXPLANATION, LOAD_SW__NAME_IS_MISSING, LOAD_SW__CONFIGURATION_LOADED,

	// Code for command playerDontRevive
	PLAYER_DONT_REVIVE_TIME__EXPLANATION, PLAYER_DONT_REVIVE_TIME__TIME_IS_MISSING, PLAYER_DONT_REVIVE_TIME__FROM_THE_BEGINNING, PLAYER_DONT_REVIVE_TIME__TIME_DEFINED,

	// Code for in game messages
	PVP_ENABLED(Permission.ALL), PLAYER_DONT_REVIVE(Permission.ALL);

	private Permission permission;

	private ESwitchMessageCode() {
		this(Permission.OPERATORS);
	}

	private ESwitchMessageCode(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String value() {
		return toString();
	}

	@Override
	public Permission getPermission() {
		return permission;
	}

	@Override
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
