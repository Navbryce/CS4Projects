import java.util.ArrayList;
import java.util.Scanner;

public class PatternMaker {
	
	public static String MakePattern(String input){ // Is case sensitive
		int letterLastAssignedIndex=-1; //Index in the alphabet of the letter that was last assigned
		
		//Indexes of these two arraylists correspond
		ArrayList<Character> lettersSeen = new ArrayList();
		ArrayList<Character> assignedLetter= new ArrayList();
		
		String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		String output="";
		
		input = input.toLowerCase();
		
		for(int letterCounter=0; letterCounter<input.length(); letterCounter++){
			char letter = input.charAt(letterCounter);
			
			int indexInAssignedLetters = seenLetter(letter, lettersSeen);
			
			char replaceLetter;
			if(indexInAssignedLetters==-1){ //letter has not been seen
				letterLastAssignedIndex++; //Get a new letter from the alphabet
				replaceLetter = alphabet.charAt(letterLastAssignedIndex);
				
				lettersSeen.add(letter);
				assignedLetter.add(replaceLetter);
				
			}else{
				replaceLetter = assignedLetter.get(indexInAssignedLetters);
			}
			output+=replaceLetter;
		}
		return output;
	}
	
	private static int seenLetter(char letter, ArrayList<Character> lettersSeen){ //will return the index of the assigned letter or -1
		int returnIndex=-1;
		
		for(int letterCounter=0; letterCounter<lettersSeen.size() && returnIndex==-1; letterCounter++){
			if(lettersSeen.get(letterCounter)==letter){
				returnIndex=letterCounter;
			}
		}
		
		return returnIndex;
	}
	
	public static void main(String args[]){
		Scanner inputScanner = new Scanner(System.in);

		do{ //An infinite loop
			 System.out.print("Please enter a word to make a pattern from: ");
			 String inputString = inputScanner.nextLine();
			 System.out.println("Output: " + MakePattern(inputString));
		}while(true); 
	}

}
