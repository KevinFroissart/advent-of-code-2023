package main.java.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day1Part2 {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/main/resources/day1/input.txt");
		List<String> lines = Files.readAllLines(path);
		int total = 0;
		for (String line : lines) {
			total += extractNumber(line);
		}
		System.out.println(total);
	}

	public static int extractNumber(String input) {
		Map<String, Integer> digitMap = new HashMap<>();
		digitMap.put("one", 1);
		digitMap.put("two", 2);
		digitMap.put("three", 3);
		digitMap.put("four", 4);
		digitMap.put("five", 5);
		digitMap.put("six", 6);
		digitMap.put("seven", 7);
		digitMap.put("eight", 8);
		digitMap.put("nine", 9);
		digitMap.put("oneight", 18);
		digitMap.put("twone", 21);
		digitMap.put("eightwo", 82);

		StringBuilder digits = new StringBuilder();
		Pattern pattern = Pattern.compile("(?:\\d+|oneight|twone|eightwo|one|two|three|four|five|six|seven|eight|nine)");

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			String match = matcher.group().toLowerCase();
			if (Character.isDigit(match.charAt(0))) {
				digits.append(match);
			} else {
				digits.append(digitMap.get(match));
			}
		}

		String number = "";
		number += digits.charAt(0);
		number += digits.charAt(digits.length() - 1);

		return Integer.parseInt(number);
	}

}
