package fr.martinfimbel.Minecraft_NewSwitch.command;

import org.bukkit.plugin.Plugin;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.persistence.SwitchPersistence;
import fr.pederobien.minecraftborder.editions.AbstractGameBorderConfigurationParent;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IMapPersistenceEdition;

public class SwitchParent extends AbstractGameBorderConfigurationParent<ISwitchConfiguration> {

	public SwitchParent(Plugin plugin) {
		super("sw", ESwitchMessageCode.SW_EXPLANATION, plugin, SwitchPersistence.getInstance());
		addEdition(SwitchEditionFactory.playerDontReviveTime());
		addEdition(SwitchEditionFactory.numberOfSwitchablePlayer());
		addEdition(SwitchEditionFactory.onePlayerSwitch());
		addEdition(SwitchEditionFactory.randomSwitch());
		addEdition(SwitchEditionFactory.startSwitchTime());
		addEdition(SwitchEditionFactory.switchAfterBorderMoves());
		addEdition(SwitchEditionFactory.periodicSwitchTime());
	}

	@Override
	protected IMapPersistenceEdition<ISwitchConfiguration> getNewEdition() {
		return SwitchEditionFactory.newSwitch();
	}

	@Override
	protected IMapPersistenceEdition<ISwitchConfiguration> getRenameEdition() {
		return SwitchEditionFactory.renameSwitch();
	}

	@Override
	protected IMapPersistenceEdition<ISwitchConfiguration> getSaveEdition() {
		return SwitchEditionFactory.saveSwitch();
	}

	@Override
	protected IMapPersistenceEdition<ISwitchConfiguration> getListEdition() {
		return SwitchEditionFactory.listSwitch();
	}

	@Override
	protected IMapPersistenceEdition<ISwitchConfiguration> getDeleteEdition() {
		return SwitchEditionFactory.deleteSwitch();
	}

	@Override
	protected IMapPersistenceEdition<ISwitchConfiguration> getDetailsEdition() {
		return SwitchEditionFactory.currentSwitch();
	}

	@Override
	protected IMapPersistenceEdition<ISwitchConfiguration> getLoadEdition() {
		return SwitchEditionFactory.loadSwitch();
	}
}
