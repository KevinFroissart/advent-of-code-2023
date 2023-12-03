package main.java.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class day2Part1 {

	public static final int RED_CUBE = 12;
	public static final int GREEN_CUBE = 13;
	public static final int BLUE_CUBE = 14;

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/main/resources/day2/input.txt");
		List<String> lines = Files.readAllLines(path);
		int total = 0;
		for (String line : lines) {
			total += gamePossibble(line);
		}
		System.out.println(total);
	}

	public static int gamePossibble(String line) {
		int gameId = Integer.parseInt(line.substring(5, line.indexOf(":")));
		line = line.substring(line.indexOf(":") + 1);
		String[] sets = line.split(";");
		for (String set : sets) {
			String[] cubes = set.split(",");
			for (String cube : cubes) {
				int value = Integer.parseInt(cube.trim().substring(0, cube.trim().indexOf(" ")));
				if (cube.contains("red") && value > RED_CUBE) {
					return 0;
				}
				else if (cube.contains("green") && value > GREEN_CUBE) {
					return 0;
				}
				else if (cube.contains("blue") && value > BLUE_CUBE) {
					return 0;
				}
			}
		}

		return gameId;
	}

}
