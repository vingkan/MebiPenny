package FizzBuzz;

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

		/*for (String l : lines) {
			System.out.println(processLine(l));
		}*/
		
		int x = Integer.parseInt(lines.get(0));
		int y = Integer.parseInt(lines.get(1));
		int z = Integer.parseInt(lines.get(2));
		
		for(int i = 1; i <= x; i++){
			if(i%y == 0 && i%z == 0){
				System.out.println("FizzBuzz");
			}
			else if(i%y == 0){
				System.out.println("Fizz");
			}
			else if(i%z == 0){
				System.out.println("Buzz");
			}
			else{
				System.out.println(i);
			}
			
		}
		
	}

	static String processLine(String line) {
		
		return line;
	}
}
