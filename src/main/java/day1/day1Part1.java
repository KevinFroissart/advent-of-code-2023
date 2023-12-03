package main.java.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day1Part1 {

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
		StringBuilder digits = new StringBuilder();
		Pattern pattern = Pattern.compile("\\d+");

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			digits.append(matcher.group());
		}

		String number = "";
		number += digits.charAt(0);
		number += digits.charAt(digits.length() - 1);

		return Integer.parseInt(number);
	}

}
