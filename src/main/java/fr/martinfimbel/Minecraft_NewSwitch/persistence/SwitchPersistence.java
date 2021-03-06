package fr.martinfimbel.Minecraft_NewSwitch.persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.martinfimbel.Minecraft_NewSwitch.SwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.persistence.loaders.SwitchLoaderV10;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftborder.persistence.BorderPersistence;
import fr.pederobien.minecraftgameplateform.impl.element.persistence.AbstractMinecraftPersistence;
import fr.pederobien.minecraftgameplateform.interfaces.element.ITeam;
import fr.pederobien.minecraftgameplateform.interfaces.element.persistence.IMinecraftPersistence;
import fr.pederobien.minecraftgameplateform.utils.Plateform;
import fr.pederobien.persistence.interfaces.IPersistence;

public class SwitchPersistence extends AbstractMinecraftPersistence<ISwitchConfiguration> {
	private static final String ROOT_XML_DOCUMENT = "Switch";
	private IPersistence<IBorderConfiguration> borderPersistence;

	private SwitchPersistence() {
		super(Plateform.ROOT.resolve("Switch"), "DefaultSwitchConfiguration");
		borderPersistence = BorderPersistence.getInstance();
		register(new SwitchLoaderV10(borderPersistence));
	}

	public static IMinecraftPersistence<ISwitchConfiguration> getInstance() {
		return SingletonHolder.PERSISTENCE;
	}

	private static class SingletonHolder {
		public static final IMinecraftPersistence<ISwitchConfiguration> PERSISTENCE = new SwitchPersistence();
	}

	@Override
	public void saveDefault() {
		set(new SwitchConfiguration(getDefault()));
		save();
		for (IBorderConfiguration border : get().getBorders()) {
			borderPersistence.set(border);
			borderPersistence.save();
		}
		borderPersistence.set(null);
	}

	@Override
	public boolean save() {
		if (get() == null)
			return false;
		Document doc = newDocument();
		doc.setXmlStandalone(true);

		Element root = createElement(doc, ROOT_XML_DOCUMENT);
		doc.appendChild(root);

		Element version = createElement(doc, VERSION);
		version.appendChild(doc.createTextNode(getVersion().toString()));
		root.appendChild(version);

		Element name = createElement(doc, SwitchXmlTag.NAME);
		name.appendChild(doc.createTextNode(get().getName()));
		root.appendChild(name);

		Element borders = createElement(doc, SwitchXmlTag.BORDERS);
		for (IBorderConfiguration configuration : get().getBorders()) {
			Element border = createElement(doc, SwitchXmlTag.BORDER);
			setAttribute(border, SwitchXmlTag.NAME, configuration.getName());
			borders.appendChild(border);
		}
		root.appendChild(borders);

		Element times = createElement(doc, SwitchXmlTag.TIMES);
		setAttribute(times, SwitchXmlTag.PVP, get().getPvpTime());
		setAttribute(times, SwitchXmlTag.PLAYER_DONT_REVIVE, get().getPlayerDontReviveTime());
		setAttribute(times, SwitchXmlTag.START_SWITCH_TIME, get().getStartSwitchTime());
		setAttribute(times, SwitchXmlTag.PERIODIC_SWITCH_TIME, get().getPeriodSwitchTime());
		setAttribute(times, SwitchXmlTag.MINIMUM_SWITCH_TIME, get().getMinimalSwitchTime());
		setAttribute(times, SwitchXmlTag.MAXIMUM_SWITCH_TIME, get().getMaximalSwitchTime());
		setAttribute(times, SwitchXmlTag.SWITCH_COUNTDOWN_TIME, get().getSwitchCountdownTime());
		root.appendChild(times);

		Element integer = createElement(doc, SwitchXmlTag.INTEGER);
		setAttribute(integer, SwitchXmlTag.AVERAGE_NUMBER, get().getAverageNumberOfSwitch());
		setAttribute(integer, SwitchXmlTag.NUMBER_OF_SWITCHABLE_PLAYERS, get().getNumberOfPlayerSwitchable());
		root.appendChild(integer);

		Element booleans = createElement(doc, SwitchXmlTag.BOOLEANS);
		setAttribute(booleans, SwitchXmlTag.ONE_PLAYER_SWITCH, get().isOnePlayerSwitchActivated());
		setAttribute(booleans, SwitchXmlTag.RANDOM_SWITCH, get().isRandomSwitchActivated());
		setAttribute(booleans, SwitchXmlTag.SWITCH_AFTER_BORDER_MOVES, get().isSwitchAfterBorderMovesActivated());
		setAttribute(booleans, SwitchXmlTag.ONE_PERMUTATION_PER_SWITCH, get().isOnePermutationPerSwitchActivated());
		root.appendChild(booleans);

		Element teams = createElement(doc, SwitchXmlTag.TEAMS);
		for (ITeam t : get().getTeams()) {
			Element team = createElement(doc, SwitchXmlTag.TEAM);
			setAttribute(team, SwitchXmlTag.NAME, t.getName());
			setAttribute(team, SwitchXmlTag.COLOR, t.getColor().toString());
			teams.appendChild(team);
		}
		root.appendChild(teams);

		saveDocument(doc, get().getName());
		return true;
	}
}
