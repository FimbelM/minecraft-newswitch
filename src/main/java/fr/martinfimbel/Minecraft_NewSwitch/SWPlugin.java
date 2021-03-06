package fr.martinfimbel.Minecraft_NewSwitch;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.minecraftgameplateform.utils.Plateform;

public class SWPlugin extends JavaPlugin {
	public static final String NAME = "minecraft-newswitch";

	@Override

	public void onEnable() {
		Plateform.getPluginHelper().register(this);
		new SwitchCommand(this);
		registerDictionaries();
	}

	private void registerDictionaries() {
		// Registering French dictionaries
		registerDictionary("French", "Switch.xml");

		// Registering English dictionaries
		registerDictionary("English", "Switch.xml");
	}

	private void registerDictionary(String parent, String... dictionaryNames) {
		Path jarPath = Plateform.ROOT.getParent().resolve(NAME.concat(".jar"));
		String dictionariesFolder = "resources/dictionaries/".concat(parent).concat("/");
		for (String name : dictionaryNames)
			registerDictionary(Plateform.getDefaultDictionaryParser(dictionariesFolder.concat(name)), jarPath);
	}

	private void registerDictionary(IDictionaryParser parser, Path jarPath) {
		try {
			Plateform.getNotificationCenter().getDictionaryContext().register(parser, jarPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
