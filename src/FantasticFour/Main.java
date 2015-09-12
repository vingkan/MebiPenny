package FantasticFour;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
	
		
		
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String> lines = new ArrayList<String>();

		try {
			while ((line = bi.readLine()) != null) {
				lines.add(line);
			}
		} catch(IOException e) { }

		for (String l : lines) {
			System.out.println(processLine(l));
		}
		
	}

	static String processLine(String line) {
		
		return line;
	}
}
