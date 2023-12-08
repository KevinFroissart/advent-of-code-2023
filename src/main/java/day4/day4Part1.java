package main.java.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day4Part1 {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/main/resources/day4/input.txt");
		List<String> lines = Files.readAllLines(path);
		int total = 0;
		for (String line : lines) {
			total += getPoints(line);
		}
		System.out.println(total);
	}

	public static int getPoints(String line) {
		if (line.isEmpty()) {
			return 0;
		}
		line = line.substring(line.indexOf(":") + 1);
		List<String> winningNumbers = new ArrayList<>(Arrays.asList(line.substring(0, line.indexOf("|")).trim().split(" ")));
		List<String> numbers = new ArrayList<>(Arrays.asList(line.substring(line.indexOf("|") + 1).trim().split(" ")));
		numbers.removeIf(number -> number == "");
		winningNumbers.removeIf(number -> number == "");
		List<String> matches = numbers.stream().filter(number -> winningNumbers.contains(number)).toList();
		int points = matches.isEmpty() ? 0 : 1;
		for (int i = 1; i < matches.size(); i++) {
			points *= 2;
		}
		return points;
	}

}
