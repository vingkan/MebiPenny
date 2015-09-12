package FiatCurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static int cash = 0;
	static int bitCoins = 0;
	
	public static void main(String[] args) {

		/*processLine("105 buy");
		processLine("102 hold");
		processLine("101 hold");
		processLine("106 sell");
		processLine("102 buy");
		processLine("107 sell");
		System.out.println(cash);*/
		
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String> lines = new ArrayList<String>();

		try {
			while ((line = bi.readLine()) != null) {
				lines.add(line);
			}
		} catch(IOException e) { }

		boolean firstLine = true;
		for (String l : lines) {
			if(firstLine){
				cash = processNumber(l);
				firstLine = false;
			}
			processLine(l);
		}
		
		System.out.println(cash);
		
	}
	
	static void processLine(String line) {
		char action = line.charAt(line.length() - 1);
		int amount = processNumber(line);
		switch(action){
			case 'y':
				if(amount < cash){
					bitCoins = bitCoins + 1;
					cash = cash - amount;
				}
				break;
			case 'l':
				if(bitCoins > 0){
					bitCoins = bitCoins - 1;
					cash = cash + amount;
				}
				break;
			default:
				break;
		}
	}
	
	static int processNumber(String number){
		//regular expression excludes all digits (d)
		return Integer.parseInt(number.replaceAll("[^\\d]", ""));
	}
	
}
