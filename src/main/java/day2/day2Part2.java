package main.java.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class day2Part2 {
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
		int maxRed = 0;
		int maxGreen = 0;
		int maxBlue = 0;

		line = line.substring(line.indexOf(":") + 1);
		String[] sets = line.split(";");
		for (String set : sets) {
			String[] cubes = set.split(",");
			for (String cube : cubes) {
				int value = Integer.parseInt(cube.trim().substring(0, cube.trim().indexOf(" ")));
				if (cube.contains("red")) {
					maxRed = Math.max(maxRed, value);
				}
				else if (cube.contains("green")) {
					maxGreen = Math.max(maxGreen, value);
				}
				else if (cube.contains("blue")) {
					maxBlue = Math.max(maxBlue, value);
				}
			}
		}

		return maxRed * maxGreen * maxBlue;
	}

}
