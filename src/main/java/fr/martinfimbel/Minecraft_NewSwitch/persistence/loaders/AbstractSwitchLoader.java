package fr.martinfimbel.Minecraft_NewSwitch.persistence.loaders;

import fr.martinfimbel.Minecraft_NewSwitch.SwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.persistence.impl.xml.AbstractXmlPersistenceLoader;
import fr.pederobien.persistence.interfaces.IPersistence;

public abstract class AbstractSwitchLoader extends AbstractXmlPersistenceLoader<ISwitchConfiguration> {
	private IPersistence<IBorderConfiguration> borderPersistence;

	protected AbstractSwitchLoader(Double version, IPersistence<IBorderConfiguration> borderPersistence) {
		super(version);
		this.borderPersistence = borderPersistence;
	}

	@Override
	protected ISwitchConfiguration create() {
		return new SwitchConfiguration("DefaultSwitchConfiguration");
	}

	/**
	 * @return The persistence that load borders associated to an hunger game configuration.
	 */
	public IPersistence<IBorderConfiguration> getBorderPersistence() {
		return borderPersistence;
	}
}