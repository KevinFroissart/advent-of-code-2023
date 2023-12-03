package main.java.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3Part2 {

	public static int lineCount = 0;

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/main/resources/day3/input.txt");
		List<String> lines = Files.readAllLines(path);
		List<Gear> gears = new ArrayList<>();
		List<EnginePart> engineParts = new ArrayList<>();
		for (String line : lines) {
			gears.addAll(getGears(line));
			engineParts.addAll(getEngineParts(line));
			lineCount++;
		}
		System.out.println(getRatio(gears, engineParts));
	}

	public static int getRatio (List<Gear> gears, List<EnginePart> engineParts) {
		int ratio = 0;
		for (Gear gear : gears) {
			List<Integer> neighboorsGear = new ArrayList<>();
			for (EnginePart enginePart : engineParts) {
				int isNeighboor = enginePart.isNeighboor(gear);
				if (isNeighboor == 0) {
					continue;
				}
				neighboorsGear.add(isNeighboor);
			}
			if (neighboorsGear.size() == 2) {
				ratio += neighboorsGear.get(0) * neighboorsGear.get(1);
			}
		}
		return ratio;
	}

	public static List<Gear> getGears(String line) {
		List<Gear> gears = new ArrayList<>();
		Pattern pattern = Pattern.compile("([*])");
		Matcher matcher = pattern.matcher(String.valueOf(line));

		while(matcher.find()) {
			gears.add(new Gear(matcher.start(), lineCount));
		}

		return gears;
	}

	public static List<EnginePart> getEngineParts(String line) {
		List<EnginePart> engineParts = new ArrayList<>();
		boolean isANumber = false;
		StringBuilder sb = new StringBuilder();
		int cpt = 0;
		for (char c : line.toCharArray()) {
			if (c >= '0' && c <= '9') {
				isANumber = true;
				sb.append(c);
				if (cpt == line.length() - 1) {
					EnginePart enginePart = new EnginePart(Integer.parseInt(sb.toString()), cpt - sb.length(), sb.length(), lineCount);
					engineParts.add(enginePart);
				}
			}
			else if (isANumber) {
				isANumber = false;
				EnginePart enginePart = new EnginePart(Integer.parseInt(sb.toString()), cpt - sb.length(), sb.length(), lineCount);
				engineParts.add(enginePart);
				sb = new StringBuilder();
			}
			cpt++;
		}

		return engineParts;
	}

	public static class Gear {

		public int index;
		public int lineNumber;

		public Gear(int index, int lineNumber) {
			this.index = index;
			this.lineNumber = lineNumber;
		}
	}

	public static class EnginePart {

		public int id;
		public int index;
		public int length;
		public int lineNumber;

		public EnginePart(int value, int index, int length, int lineNumber) {
			this.id = value;
			this.index = index;
			this.length = length;
			this.lineNumber = lineNumber;
		}

		public int isNeighboor(Gear gear) {
			if (gear.lineNumber == this.lineNumber - 1 || gear.lineNumber == this.lineNumber + 1 || gear.lineNumber == this.lineNumber) {
				for(int i = this.index; i <= this.index + length - 1; i++) {
					if (gear.index - 1 == i || gear.index == i || gear.index + 1 == i) {
						return this.id;
					}
				}
			}
			return 0;
		}
	}
}
