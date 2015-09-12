package callMeMaybe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		/*Keypad kp = new Keypad();
		String n1 = kp.translateNumber("5551234");
		String n2 = kp.translateNumber("GOPANDA");
		String n3 = kp.translateNumber("MebiPenny2012");
		String n4 = kp.translateNumber("GO%PANDA");
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(n4);*/
		
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
		Keypad kp = new Keypad();
		String result = kp.translateNumber(line);
		return result;
	}

	
	public static class Keypad{
		
		public Keypad(){
			
		}
		
		private String translateNumber(String line){
			char[] letters = line.toCharArray();
			String number = "";
			for(char letter : letters){
				if(isDigit(letter)){
					number += letter;
				}
				else{
					number += getNumber(letter);
				}
			}
			return number;
		}
		
		private boolean isDigit(char letter){
			return (letter >= '0' && letter <= '9');
		}
		
		private int ref(char letter){
			char uppercase = Character.toUpperCase(letter);
			return (int)uppercase;
		}
		
		private boolean between(int index, char start, char end){
			boolean onKey = false;
			if(index >= ref(start) && index <= ref(end)){
				onKey = true;
			}
			return onKey;
		}
		
		private String getNumber(char letter){
			char uppercase = Character.toUpperCase(letter);
			int charIndex = (int)uppercase;
			String keyNumber = "";
			if(between(charIndex, 'A', 'C')){
				keyNumber = "2";
			}
			else if(between(charIndex, 'D', 'F')){
				keyNumber = "3";
			}
			else if(between(charIndex, 'G', 'I')){
				keyNumber = "4";
			}
			else if(between(charIndex, 'J', 'L')){
				keyNumber = "5";
			}
			else if(between(charIndex, 'M', 'O')){
				keyNumber = "6";
			}
			else if(between(charIndex, 'P', 'S')){
				keyNumber = "7";
			}
			else if(between(charIndex, 'T', 'V')){
				keyNumber = "8";
			}
			else if(between(charIndex, 'W', 'Z')){
				keyNumber = "9";
			}
			return keyNumber;
		}
		
		/*private void numbers(){
			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			char[] alphabet = alpha.toCharArray();
			for(char letter : alphabet){
				System.out.println(letter + " = " + (int)letter);
			}
		}*/
		
	}
}

