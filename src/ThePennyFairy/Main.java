package ThePennyFairy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

		/*for (String l : lines) {
			System.out.println(processLine(l));
		}
		
		/*lines.add("10");
		lines.add("11");
		lines.add("100");*/
		
		double growthRate = Double.parseDouble(lines.get(1)) / Double.parseDouble(lines.get(0));
		double capacity = Double.parseDouble(lines.get(2));
		double pennies = Double.parseDouble(lines.get(1));
		int days = 0;
		while(pennies < capacity){
			pennies = Math.ceil(pennies * growthRate);
			days++;
			//System.out.println("Pennies: " + pennies + " Days: " + days);
		}
		System.out.println(days);
		
	}

	static String processLine(String line) {
		
		return line;
	}
}
