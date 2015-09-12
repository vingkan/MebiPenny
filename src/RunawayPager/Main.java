package RunawayPager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		//System.out.println(processLine("KKKKKDDDTTTTMMMMMKKKKTTYYYYYYYYR"));
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
		String response = "";
		char[] letters = line.toCharArray();
		char lastChar = '-';
		int counter = 1;
		for(char c : letters){
			if(c == lastChar){
				counter++;
			}
			else if(counter > 1){
				response += counter;
				counter = 1;
				response += c;
			}
			else{
				response += c;
			}
			lastChar = c;
		}
		if(counter > 1){
			response += counter;
		}
		return response;
	}
}
