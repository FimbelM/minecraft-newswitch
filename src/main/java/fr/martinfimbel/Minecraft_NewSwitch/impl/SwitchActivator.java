package fr.martinfimbel.Minecraft_NewSwitch.impl;

import java.time.LocalTime;

import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IPlateformCodeSender;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.IObsTimeLine;

public class SwitchActivator implements IObsTimeLine, IPlateformCodeSender {
	private ISwitchConfiguration configuration;

	public SwitchActivator(ISwitchConfiguration configuration) {
		// TODO Auto-generated constructor stub
		this.configuration = configuration;
		configuration.isRandomSwitchActivated();
	}

	public void initialize() {
		// TODO fill switch time map depending on is random or not
	}

	@Override
	public int getCountDown() {
		// TODO commande pour le r�gler ou constant � 10 sec
		// configuration.getCountdownValue;
		return 0;
	}

	@Override
	public int getCurrentCountDown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onTime(LocalTime currentTime) {
		// TODO do the switch (teams + tp)
		// Plateform.getOrCreateConfigurationHelper(configuration) --> acc�s � toutes les commandes concernant les �quipes
	}

	@Override
	public void onCountDownTime(LocalTime currentTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime getNextNotifiedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	// rajouter classe interne qui permet de d�couper ontime en plusieurs m�thodes
}
