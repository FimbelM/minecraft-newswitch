package fr.martinfimbel.Minecraft_NewSwitch.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.martinfimbel.Minecraft_NewSwitch.ESwitchMessageCode;
import fr.martinfimbel.Minecraft_NewSwitch.interfaces.ISwitchConfiguration;
import fr.pederobien.minecraftborder.interfaces.IBorderConfiguration;
import fr.pederobien.minecraftgameplateform.interfaces.editions.IPlateformCodeSender;
import fr.pederobien.minecraftgameplateform.interfaces.element.ITeam;
import fr.pederobien.minecraftgameplateform.interfaces.helpers.IGameConfigurationHelper;
import fr.pederobien.minecraftgameplateform.interfaces.runtime.timeline.IObsTimeLine;
import fr.pederobien.minecraftgameplateform.utils.Plateform;
import fr.pederobien.minecraftmanagers.EColor;
import fr.pederobien.minecraftmanagers.MessageManager.DisplayOption;
import fr.pederobien.minecraftmanagers.PlayerManager;
import fr.pederobien.minecraftmanagers.WorldManager;

public class SwitchActivator implements IObsTimeLine, IPlateformCodeSender {
	private ISwitchConfiguration configuration;
	private IGameConfigurationHelper helper;
	private IBorderConfiguration borderConf;
	private int countdownTime, currentCountdown, lambda, count;
	private boolean isRandomSwitchActivated, isOnePlayerSwitchActivated, isNextSwitchAvailable, isOnePermutationPerSwitch;
	private Map<Integer, LocalTime> switchTimes;
	private Random rand = new Random();

	public SwitchActivator(ISwitchConfiguration configuration) {
		this.configuration = configuration;
		helper = Plateform.getOrCreateConfigurationHelper(configuration);
		switchTimes = new HashMap<>();
	}

	public boolean initializeTimeMap() {
		switchTimes.clear();
		borderConf = configuration.getBorder(WorldManager.OVERWORLD).get();
		isRandomSwitchActivated = configuration.isRandomSwitchActivated();
		countdownTime = configuration.getSwitchCountdownTime().toSecondOfDay();
		currentCountdown = countdownTime;
		lambda = configuration.getAverageNumberOfSwitch();
		isOnePlayerSwitchActivated = configuration.isOnePlayerSwitchActivated();
		isOnePermutationPerSwitch = configuration.isOnePermutationPerSwitchActivated();
		isNextSwitchAvailable = true;
		count = 0;

		LocalTime startSwitchTime = configuration.getStartSwitchTime();
		LocalTime periodicSwitchTime = configuration.getPeriodSwitchTime();
		LocalTime nextSwitchTime = startSwitchTime;
		LocalTime borderStartTime = borderConf.getStartTime();
		LocalTime lowerBound = configuration.getMinimalSwitchTime();
		LocalTime tmax = configuration.getMaximalSwitchTime();
		LocalTime minimalIntervalBetweenSwitches = configuration.getSwitchCountdownTime();
		LocalTime randomGeneratedTime;
		LocalTime upperBound;
		boolean isSwitchAfterBorderMoves = configuration.isSwitchAfterBorderMovesActivated();
		int randomGeneratedTimeInteger, switchCount = 0;

		// Cas pas random
		if (!isRandomSwitchActivated) {

			// initialization of map
			switchTimes.put(switchCount, startSwitchTime);

			if (!isSwitchAfterBorderMoves) {
				if (startSwitchTime.toSecondOfDay() > borderStartTime.toSecondOfDay())
					return false;

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

			/*
			 * for (int count = 0; count < switchTimes.size(); count++) { BukkitManager.broadcastMessage("count : " + count + " " +
			 * switchTimes.get(count).toString()); }
			 */

		}

		// cas random
		else {

			// Object that return random int.
			switchCount = getPoissonRandom(lambda); // loi de poisson pour déterminer le nombre de switch en fonction d'un paramètre lambda

			// Define upper bound as a function of isSwitchafterBorderMoves true or false
			if (!isSwitchAfterBorderMoves) {
				upperBound = borderStartTime.minusSeconds(tmax.toSecondOfDay());
				if (switchCount * minimalIntervalBetweenSwitches.toSecondOfDay() > upperBound.toSecondOfDay())
					return false;
			} else
				upperBound = borderStartTime.plusHours(1);

			while (switchTimes.size() < switchCount) {

				// generate a "integer" time between lower and upper bounds
				randomGeneratedTimeInteger = rand.nextInt(upperBound.toSecondOfDay() - lowerBound.toSecondOfDay()) + lowerBound.toSecondOfDay();
				randomGeneratedTime = LocalTime.of(0, 0, 0).plusSeconds(randomGeneratedTimeInteger);

				// verify that the selected time isn't too close (less than the warning before switch time) from another already selected time
				boolean isValidated = true;
				for (int j = 0; j < switchTimes.size(); j++)
					isValidated &= Math.abs(switchTimes.get(j).toSecondOfDay() - randomGeneratedTimeInteger) > minimalIntervalBetweenSwitches.toSecondOfDay();

				// Put the time value in the map
				if (isValidated)
					switchTimes.put(switchTimes.size(), randomGeneratedTime);
			}
			sortMap();

			// display map

			/*
			 * for (Map.Entry<Integer, LocalTime> entry : switchTimes.entrySet()) { BukkitManager.broadcastMessage("times : " + entry.getKey()
			 * + " - " + entry.getValue()); }
			 */

		}
		return true;
	}

	@Override
	public int getCountDown() {
		return countdownTime;
	}

	@Override
	public int getCurrentCountDown() {
		return currentCountdown;
	}

	@Override
	public void onTime(LocalTime currentTime) {
		// Plateform.getOrCreateConfigurationHelper(configuration) --> accès à toutes les commandes concernant les équipes;

		// choix des joueurs
		List<Player> chosenPlayers = selectPlayers();
		if (chosenPlayers == null) {
			isNextSwitchAvailable = false;
			return;
		}

		// récuperer équipes
		List<ITeam> playersTeam = selectedPlayersTeam(chosenPlayers);

		// coordonnées et permutation
		teleportSelectedPlayers(chosenPlayers);

		// permutation des équipes des joueurs
		modifySelectedPlayersTeam(chosenPlayers, playersTeam);

		// reset du countdown et message du switch
		sendNotSynchro(ESwitchMessageCode.SWITCH, DisplayOption.TITLE, EColor.DARK_RED);
		currentCountdown = getCountDown();
	}

	@Override
	public void onCountDownTime(LocalTime currentTime) {
		sendNotSynchro(ESwitchMessageCode.NEXT_SWITCH_IN, DisplayOption.TITLE, EColor.GOLD, currentCountdown);
		currentCountdown--;
	}

	@Override
	public LocalTime getNextNotifiedTime() {
		if (count == switchTimes.size() || !isNextSwitchAvailable)
			return LocalTime.of(0, 0, 0);

		LocalTime nextSwitch = switchTimes.get(count);
		count++;
		return nextSwitch;

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

	private void sortMap() {
		List<Map.Entry<Integer, LocalTime>> switchTimesList = new ArrayList<>(switchTimes.entrySet());

		Collections.sort(switchTimesList, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

		Map<Integer, LocalTime> result = new HashMap<>();
		int counter = 0;
		for (Map.Entry<Integer, LocalTime> entry : switchTimesList) {
			result.put(counter, entry.getValue());
			counter++;
		}

		switchTimes = result;
	}

	private List<Player> selectPlayers() {
		List<Player> selectedPlayers = new ArrayList<>();
		List<ITeam> everyTeam = new ArrayList<ITeam>(configuration.getTeams());

		while (everyTeam.size() > 1) {

			// selecting player one of every switch
			Player chosenPlayerOne = selectPlayer(everyTeam);
			if (chosenPlayerOne == null)
				return null;
			selectedPlayers.add(chosenPlayerOne);

			// selecting player two of every switch
			if (everyTeam.isEmpty())
				return null;

			Player chosenPlayerTwo = selectPlayer(everyTeam);
			if (chosenPlayerTwo == null)
				return null;

			selectedPlayers.add(chosenPlayerTwo);
			if (isOnePermutationPerSwitch)
				break;

		}

		return selectedPlayers;
	}

	private List<ITeam> selectedPlayersTeam(List<Player> chosenPlayers) {
		List<ITeam> teams = new ArrayList<>();

		for (int i = 0; i < chosenPlayers.size(); i++) {
			Player actualPlayer = chosenPlayers.get(i);
			teams.add(helper.getTeam(actualPlayer).get());
		}
		return teams;
	}

	private void teleportSelectedPlayers(List<Player> chosenPlayers) {
		List<Location> location = new ArrayList<>();

		for (int i = 0; i < chosenPlayers.size(); i++) {
			Player actualPlayer = chosenPlayers.get(i);
			location.add(actualPlayer.getLocation());
		}
		for (int i = 0; i < chosenPlayers.size(); i += 2) {
			PlayerManager.teleporte(chosenPlayers.get(i), location.get(i + 1));
			PlayerManager.teleporte(chosenPlayers.get(i + 1), location.get(i));
		}
		return;
	}

	private void modifySelectedPlayersTeam(List<Player> chosenPlayers, List<ITeam> playersTeam) {
		for (int i = 0; i < chosenPlayers.size(); i += 2) {
			helper.movePlayer(chosenPlayers.get(i), playersTeam.get(i + 1));
			helper.movePlayer(chosenPlayers.get(i + 1), playersTeam.get(i));
		}
	}

	private Player selectPlayer(List<ITeam> everyTeam) {
		ITeam selectedTeam = everyTeam.get(rand.nextInt(everyTeam.size()));
		List<Player> availablePlayersInTeamList = listOfPlayers(selectedTeam);
		// Verifying if player is alone in his team
		if (!isOnePlayerSwitchActivated) {
			while (availablePlayersInTeamList.size() <= 1) {
				everyTeam.remove(selectedTeam);
				if (everyTeam.size() == 0)
					return null;
				selectedTeam = everyTeam.get(rand.nextInt(everyTeam.size()));
				availablePlayersInTeamList = listOfPlayers(selectedTeam);
			}
		} else {
			while (availablePlayersInTeamList.size() == 0) {
				everyTeam.remove(selectedTeam);
				if (everyTeam.size() == 0)
					return null;
				selectedTeam = everyTeam.get(rand.nextInt(everyTeam.size()));
				availablePlayersInTeamList = listOfPlayers(selectedTeam);
			}
		}
		everyTeam.remove(selectedTeam);

		return availablePlayersInTeamList.get(rand.nextInt(availablePlayersInTeamList.size()));

	}

	private List<Player> listOfPlayers(ITeam selectedTeam) {
		return PlayerManager.getPlayersOnMode(selectedTeam.getPlayers().stream(), GameMode.SURVIVAL).collect(Collectors.toList());
	}

	// rajouter classe interne qui permet de découper ontime en plusieurs méthodes
}
