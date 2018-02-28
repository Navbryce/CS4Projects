import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class PuzzleDriver {
	private PatternTable dictionaryTableLarge;
	private PatternTable dictionaryTableSmall;
	private String typeOfTable;
	
	public static void main (String args[]) {
		new PuzzleDriver();
	}
	public PuzzleDriver () {
		HashSet dictionarySet = new HashSet();
		WordDictionary dictionary = new WordDictionary(dictionarySet);
		dictionary.Read("dictionary");
		System.out.println("Large Dictionary loaded with size " + dictionary.Size());
		dictionaryTableLarge = Puzzle.convertDictionaryToTable(dictionary);
		System.out.println("Dictionary converted to pattern table...");

		dictionarySet = new HashSet();
		dictionary = new WordDictionary(dictionarySet);
		dictionary.Read("dictionary_short");
		System.out.println("Small dictionary loaded with size " + dictionary.Size());
		dictionaryTableSmall = Puzzle.convertDictionaryToTable(dictionary);
		System.out.println("Dictionary converted to pattern table...");


		
		Scanner translateInput = new Scanner(System.in);
		translatePrompt(translateInput);
		//table.printTable();
		
		
		/*for (String string: arrayString) {
			System.out.println(string);
		}*/
		
	
	}
	public void translatePrompt (Scanner translateInput) {
		System.out.print("Please enter a string to translate: ");
		String messageString = translateInput.nextLine();
		System.out.print("Please enter a cut: ");
		int cut = translateInput.nextInt();
		translateInput.nextLine(); // Consume the new line that nextInt created (a weird behavior of scanner)
		LinkedList<Puzzle> solutions = getTranslations(messageString, cut);
		for (Puzzle solution: solutions) {
			System.out.println("Translated: " + solution.getMessageTranslation());
		}
		translatePrompt(translateInput);
	}
	public LinkedList<Puzzle> getTranslations (String encoded, int cut) {
		String[] arrayString = Puzzle.convertStringToArray(encoded);
		System.out.print("Decoding ");
		for (int wordCounter = 0; wordCounter < arrayString.length; wordCounter++) {
			System.out.print("(" + wordCounter + ")" + arrayString[wordCounter] + " ");
			if (wordCounter == arrayString.length - 1) {
				System.out.print("\n");
			}
		}
		boolean useLargeDictionary = true; // Use large dictionary ? use large dictionary : don't use large dictionary
		Puzzle puzzle;
		if (useLargeDictionary) {
			puzzle = new Puzzle(arrayString, dictionaryTableLarge);
			typeOfTable = "dictionary table large";
		} else {
			puzzle = new Puzzle(arrayString, dictionaryTableSmall);
			typeOfTable = "dictionary table small";
		}
		
		System.out.println("Translating with " + typeOfTable);
		double startTime = System.currentTimeMillis();
		LinkedList<Puzzle> solutions = puzzle.SolveMonalphabeticCiper(puzzle, cut, 0);
		double endTime = System.currentTimeMillis();
		System.out.println("Translated in " + (endTime - startTime) + " ms with " + typeOfTable);
		return solutions;
	}


	
}
