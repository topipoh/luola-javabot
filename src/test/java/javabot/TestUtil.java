package javabot;

import java.util.Scanner;

public class TestUtil {
	public static String loadTextFile(String name) {
		try(Scanner scanner = new Scanner(TestUtil.class.getResourceAsStream(name))) {
			return scanner.useDelimiter("\\A").next();
		}
	}
}
