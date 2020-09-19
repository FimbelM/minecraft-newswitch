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
		Node times = getElementsByTagName(root, SwitchXmlTag.TIMES).item(0);
		get().setPvpTime(getLocalTimeAttribute((Element) times, SwitchXmlTag.PVP));
		get().setPlayerDontReviveTime(getLocalTimeAttribute((Element) times, SwitchXmlTag.PLAYER_DONT_REVIVE));

		get().clearTeams();
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
