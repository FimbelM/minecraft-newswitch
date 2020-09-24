package fr.martinfimbel.Minecraft_NewSwitch.persistence.loaders;

import java.io.FileNotFoundException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.martinfimbel.Minecraft_NewSwitch.persistence.SwitchXmlTag;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.impl.element.PlateformTeam;
import fr.pederobien.minecraftgameplateform.interfaces.element.ITeam;
import fr.pederobien.minecraftmanagers.EColor;
import fr.pederobien.persistence.interfaces.IPersistence;
import fr.pederobien.persistence.interfaces.xml.IXmlPersistenceLoader;

public class SwitchLoaderV10 extends AbstractSwitchLoader {

	public SwitchLoaderV10(IPersistence<IBorderConfiguration> borderPersistence) {
		super(1.0, borderPersistence);
	}

	@Override
	public IXmlPersistenceLoader<ISwitchConfiguration> load(Element root) {
		createNewElement();

		// Getting configuration name
		Node name = getElementsByTagName(root, SwitchXmlTag.NAME).item(0);
		get().setName(name.getChildNodes().item(0).getNodeValue());

		get().clearBorders();
		// Getting border configurations
		NodeList borders = getElementsByTagName(root, SwitchXmlTag.BORDER);
		for (int i = 0; i < borders.getLength(); i++) {
			try {
				getBorderPersistence().load(getStringAttribute((Element) borders.item(i), SwitchXmlTag.NAME));
				get().add(getBorderPersistence().get());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		// Getting configuration times
		Element times = (Element) getElementsByTagName(root, SwitchXmlTag.TIMES).item(0);
		get().setPvpTime(getLocalTimeAttribute(times, SwitchXmlTag.PVP));
		get().setPlayerDontReviveTime(getLocalTimeAttribute(times, SwitchXmlTag.PLAYER_DONT_REVIVE));
		get().setStartSwitchTime(getLocalTimeAttribute(times, SwitchXmlTag.START_SWITCH_TIME));
		get().setPeriodSwitchTime(getLocalTimeAttribute(times, SwitchXmlTag.PERIODIC_SWITCH_TIME));
		get().setMaximalSwitchTime(getLocalTimeAttribute(times, SwitchXmlTag.MAXIMUM_SWITCH_TIME));
		get().setMinimalSwitchTime(getLocalTimeAttribute(times, SwitchXmlTag.MINIMUM_SWITCH_TIME));
		get().setSwitchCountdownTime(getLocalTimeAttribute(times, SwitchXmlTag.SWITCH_COUNTDOWN_TIME));

		get().clearTeams();

		// Getting configuration integers
		Element integers = (Element) getElementsByTagName(root, SwitchXmlTag.INTEGER).item(0);
		get().setAverageNumberOfSwitch(getIntAttribute(integers, SwitchXmlTag.AVERAGE_NUMBER));
		get().setNumberOfPlayerSwitchable(getIntAttribute(integers, SwitchXmlTag.NUMBER_OF_SWITCHABLE_PLAYERS));

		// Getting configuration booleans
		Element booleans = (Element) getElementsByTagName(root, SwitchXmlTag.BOOLEANS).item(0);
		get().setOnePlayerSwitch(getBooleanAttribute(booleans, SwitchXmlTag.ONE_PLAYER_SWITCH));
		get().setRandomSwitch(getBooleanAttribute(booleans, SwitchXmlTag.RANDOM_SWITCH));
		get().setSwitchAfterBorderMovesActivated(getBooleanAttribute(booleans, SwitchXmlTag.SWITCH_AFTER_BORDER_MOVES));
		get().setOnePermutationPerSwitchActivated(getBooleanAttribute(booleans, SwitchXmlTag.ONE_PERMUTATION_PER_SWITCH));

		// Getting configuration teams
		NodeList teams = getElementsByTagName(root, SwitchXmlTag.TEAM);
		for (int i = 0; i < teams.getLength(); i++) {
			Element t = (Element) teams.item(i);
			ITeam team = PlateformTeam.of(getStringAttribute(t, SwitchXmlTag.NAME), EColor.getByColorName(getStringAttribute(t, SwitchXmlTag.COLOR)));
			get().add(team);
		}
		return this;
	}
}
