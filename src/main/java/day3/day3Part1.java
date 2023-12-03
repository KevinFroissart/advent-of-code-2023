package main.java.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3Part1 {

	public static int lineCount = 0;

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/main/resources/day3/input.txt");
		List<String> lines = Files.readAllLines(path);
		int total = 0;
		List<EnginePart> engineParts = new ArrayList<>();
		for (String line : lines) {
			engineParts.addAll(getEngineParts(line));
		}
		for (EnginePart enginePart : engineParts) {
			total += isEnginePart(enginePart, lines);
		}
		System.out.println(total);
	}

	public static int isEnginePart(EnginePart enginePart, List<String> lines) {
		int maxLine = lines.size();
		int maxLength = lines.get(0).length();

		int line = enginePart.lineNumber;
		int index = enginePart.index;
		int length = enginePart.length;

		String line0 = line - 1 >= 0 ? lines.get(line - 1) : "";
		String line1 = lines.get(line);
		String line2 = line + 1 < maxLine ? lines.get(line + 1) : "";

		int minIndex = index - 1 >= 0 ? index - 1 : 0;
		int maxIndex = index + length + 1 < maxLength ? index + length + 1 : maxLength;

		String[] linesToCheck = {line0, line1, line2};

		for (String lineToCheck : linesToCheck) {
			if (lineToCheck.isEmpty()) {
				continue;
			}
			String lineToCheckSub = lineToCheck.substring(minIndex, maxIndex);
			Pattern pattern = Pattern.compile("([^.0-9])");

			Matcher matcher = pattern.matcher(lineToCheckSub);
			if (matcher.find()) {
				return enginePart.id;
			}
		}

		return 0;
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

		lineCount++;
		return engineParts;
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
	}

}
