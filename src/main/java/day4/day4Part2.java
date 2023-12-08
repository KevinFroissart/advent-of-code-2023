package main.java.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class day4Part2 {

	public static int lineCount = 0;

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/main/resources/day4/input.txt");
		List<String> lines = Files.readAllLines(path);
		List<Card> cards = new LinkedList<>();
		for(String line : lines) {
			cards.add(new Card(line.substring(4, line.indexOf(":"))));
		}
		for (String line : lines) {
			getPoints(line, cards);
		}
		int total = 0;
		for (Card card : cards) {
			total += card.nbInstances;
		}
		System.out.println(total);
	}

	public static void getPoints(String line, List<Card> cards) {
		if (line.isEmpty()) {
			return;
		}
		line = line.substring(line.indexOf(":") + 1);
		List<String> winningNumbers = new ArrayList<>(Arrays.asList(line.substring(0, line.indexOf("|")).trim().split(" ")));
		List<String> numbers = new ArrayList<>(Arrays.asList(line.substring(line.indexOf("|") + 1).trim().split(" ")));
		numbers.removeIf(number -> number == "");
		winningNumbers.removeIf(number -> number == "");
		List<String> matches = numbers.stream().filter(number -> winningNumbers.contains(number)).toList();
		for(int i = 0; i < cards.get(lineCount).nbInstances; i++) {
			for (int j = lineCount + 1; j < matches.size() + lineCount + 1; j++) {
				cards.get(j).nbInstances++;
			}
		}
		lineCount++;
	}

	public static class Card {
		public int nbInstances = 1;
		public String number;

		public Card(String number) {
			this.number = number;
		}
	}

}
