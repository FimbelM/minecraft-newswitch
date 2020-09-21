package fr.martinfimbel.Minecraft_NewSwitch.impl;

import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IPlateformCodeSender;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.IObsTimeLine;
import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftmanagers.EColor;
import fr.pederobien.minecraftmanagers.MessageManager.DisplayOption;
import fr.pederobien.minecraftmanagers.WorldManager;

public class SwitchActivator implements IObsTimeLine, IPlateformCodeSender {
	private ISwitchConfiguration configuration;
	private IBorderConfiguration borderConf;
	private Integer countdownTime;
	private Integer currentCountDown;
	private Integer lambda;
	private Boolean isRandomSwitchActivated;

	public SwitchActivator(ISwitchConfiguration configuration) {
		this.configuration = configuration;
	}

	public void initializeTimeMap() {
		borderConf = configuration.getBorder(WorldManager.OVERWORLD).get();
		isRandomSwitchActivated = configuration.isRandomSwitchActivated();
		countdownTime = configuration.getSwitchCountdownTime().toSecondOfDay();
		lambda = configuration.getAverageNumberOfSwitch();
		// TODO fill switch time map depending on is random or not

		LinkedHashMap<Integer, LocalTime> switchTimes = new LinkedHashMap<>();
		Map<Integer, LocalTime> sortedSwitchTimes = new LinkedHashMap<>();
		LocalTime startSwitchTime = configuration.getStartSwitchTime();
		LocalTime periodicSwitchTime = configuration.getPeriodSwitchTime();
		LocalTime nextSwitchTime = startSwitchTime;
		LocalTime borderStartTime = borderConf.getStartTime();
		LocalTime lowerBound = configuration.getMinimalSwitchTime();
		LocalTime tmax = configuration.getMaximalSwitchTime();
		LocalTime minimalIntervalBetweenSwitches = configuration.getSwitchCountdownTime();
		Boolean isSwitchAfterBorderMoves = configuration.isSwitchAfterBorderMovesActivated();
		Integer switchCount = 0;
		Random rand = new Random();

		// Cas pas random
		if (!isRandomSwitchActivated) {
			// initialization of map
			switchTimes.put(switchCount, startSwitchTime);

			if (!isSwitchAfterBorderMoves) {
				do {
					switchCount++;
					nextSwitchTime = nextSwitchTime.plusSeconds(periodicSwitchTime.toSecondOfDay());
					if (nextSwitchTime.toSecondOfDay() > borderStartTime.toSecondOfDay())
						break;
					switchTimes.put(switchCount, nextSwitchTime);
				} while (nextSwitchTime.toSecondOfDay() < borderStartTime.toSecondOfDay());
			} else {
				do {
					switchCount++;
					nextSwitchTime = nextSwitchTime.plusSeconds(periodicSwitchTime.toSecondOfDay());
					if (nextSwitchTime.toSecondOfDay() > borderConf.getMoveTime().plusSeconds(borderStartTime.toSecondOfDay()).plusHours(1).toSecondOfDay())
						break;
					switchTimes.put(switchCount, nextSwitchTime);
				} while (nextSwitchTime.toSecondOfDay() < borderConf.getMoveTime().plusSeconds(borderStartTime.toSecondOfDay()).plusHours(1).toSecondOfDay());
			}
			// display map
			BukkitManager.broadcastMessage("Switch times : ");
			for (int count = 0; count < switchTimes.size(); count++) {
				BukkitManager.broadcastMessage("count : " + count + " " + switchTimes.get(count).toString());
			}
		}
		// cas random
		else {
			Integer randomGeneratedTimeInteger;
			LocalTime randomGeneratedTime;

			LocalTime upperBound;
			// Define upper bound as a function of isSwitchafterBorderMoves true or false
			if (!isSwitchAfterBorderMoves)
				upperBound = borderStartTime.minusSeconds(tmax.toSecondOfDay());
			else
				upperBound = borderStartTime.plusSeconds(4000);

			// Object that return random int.
			switchCount = getPoissonRandom(lambda); // loi de poisson pour déterminer le nombre de switch en fonction d'un paramètre lambda
			BukkitManager.broadcastMessage(switchCount.toString()); // debugging and information
			switchTimes.put(0, LocalTime.of(0, 0, 0));
			for (int i = 0; i < switchCount; i++) { // for each switchcount, associate a random time and then concatening it in the map
				// generate a "integer" time between lower and upper bounds
				randomGeneratedTimeInteger = rand.nextInt(upperBound.toSecondOfDay() - lowerBound.toSecondOfDay()) + lowerBound.toSecondOfDay();
				randomGeneratedTime = LocalTime.of(0, 0, 0).plusSeconds(randomGeneratedTimeInteger);
				BukkitManager.broadcastMessage("i : " + i + " generated time : " + randomGeneratedTime.toString());
				// verify that the selected time isn't too close (less than the warning before switch time) from another already selected time
				for (int j = 0; j < switchTimes.size(); j++) {
					if (switchTimes.get(j).minusSeconds(randomGeneratedTimeInteger).toSecondOfDay() > minimalIntervalBetweenSwitches.toSecondOfDay()) {
						switchTimes.put(i, randomGeneratedTime); // put the switch time value in the map
					} else
						break;
				}
			}

			sortedSwitchTimes = sortMap(switchTimes);

			// display map
			for (Map.Entry<Integer, LocalTime> entry : sortedSwitchTimes.entrySet()) {
				BukkitManager.broadcastMessage("Key after sort : " + entry.getKey());
				BukkitManager.broadcastMessage("Value after sort : " + entry.getValue());
			}

		}

	}

	@Override
	public int getCountDown() {
		return countdownTime;
	}

	@Override
	public int getCurrentCountDown() {
		return currentCountDown;
	}

	@Override
	public void onTime(LocalTime currentTime) {
		// TODO do the switch (teams + tp)

		// Plateform.getOrCreateConfigurationHelper(configuration) --> accès à toutes les commandes concernant les équipes
	}

	@Override
	public void onCountDownTime(LocalTime currentTime) {
		sendNotSynchro(ESwitchMessageCode.NEXT_SWITCH_IN, DisplayOption.TITLE, EColor.GOLD, currentCountDown);
		currentCountDown--;
	}

	@Override
	public LocalTime getNextNotifiedTime() {
		// recuperer les valeurs de la map
		// comment faire???

		return LocalTime.of(0, 0, 35);
	}

	private static int getPoissonRandom(double mean) {
		Random r = new Random();
		double L = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		do {
			p = p * r.nextDouble();
			k++;
		} while (p > L);
		return k - 1;
	}

	private Map<Integer, LocalTime> sortMap(LinkedHashMap<Integer, LocalTime> map) {
		List<Map.Entry<Integer, LocalTime>> switchTimesList = new LinkedList<>(map.entrySet());

		Collections.sort(switchTimesList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

		LinkedHashMap<Integer, LocalTime> result = new LinkedHashMap<>();
		int counter = 0;
		for (Map.Entry<Integer, LocalTime> entry : switchTimesList) {
			result.put(counter, entry.getValue());
			counter++;
		}
		return result;
	}

	// rajouter classe interne qui permet de découper ontime en plusieurs méthodes
}
