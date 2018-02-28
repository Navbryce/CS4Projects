import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Puzzle {
	public static final int UNTRANSLATED = 0;
	public static final int TRANSLATED = 1;
	public static final int UNTRANSLATABLE = 2;
	
	private String[] puzzleWords;
	private Translation translation;
	private int[] stringStatus;
	private int[] statusCounts; // Index 0 - Untranslated; Index 1- Translated; Index 2 - Untranslatable
	private ArrayList<String>[] wordLists;
	
	/**
	 * @param message - Array of strings to decode
	 * @param table - Should be a BST for the decode algorithm to be most efficient
	 */
	public Puzzle (String[] message, PatternTable table) {
		puzzleWords = message; // Should it copy the message array?
		stringStatus = new int[puzzleWords.length]; // All indexes should be 0 AKA untranslated
		statusCounts = new int[3];
		statusCounts[UNTRANSLATED] = puzzleWords.length; // All the words are untranslated
		translation = new Translation();

		
		// Create the wordLists. Some elements at different indexes might be the same (because the have the same pattern)
		wordLists = new ArrayList[puzzleWords.length];
		for (int wordCounter = 0; wordCounter < puzzleWords.length; wordCounter++) {
			String word = puzzleWords[wordCounter];
			wordLists[wordCounter] = table.WordsWithSamePatternAs(word);
		}
		
	}
	
	public Puzzle(Puzzle P){
		int size = P.messageSize();
		puzzleWords = new String[size];
		stringStatus = new int[size];
		wordLists = new ArrayList[size];
		translation = new Translation(P.getTranslation());
		
		// Copy status counts
		statusCounts = new int[3];
		for (int statusCounter = 0; statusCounter <= 2; statusCounter++){
			// statusCounter = status
			statusCounts[statusCounter] = P.numberOfWordsWithStatus(statusCounter);
		}
		
		// Copy individual statuses and lists of words with the same pattern. Also copy words
		for (int wordCounter = 0; wordCounter < size; wordCounter++) {
			puzzleWords[wordCounter] = P.nthWord(wordCounter);
			stringStatus[wordCounter] = P.statusOfWord(wordCounter);
			wordLists[wordCounter] = P.wordsWithSamePatternAs(wordCounter);
		}
	}
	
	public int messageSize () {
		return puzzleWords.length;
	}
	
	public String nthWord (int N) {
		return puzzleWords[N]; // should the word at the 0 index be considered the N = 1 word?
	}
	
	public int statusOfWord (int N) {
		return stringStatus[N];
	}
	
	public void setStatus (int N, int status) {
		int currentStatus = statusOfWord(N);
		statusCounts[currentStatus] = statusCounts[currentStatus] - 1; // Subtract by 1 because the status of N word is being changed away from currentStatus
		stringStatus[N] = status;
		statusCounts[status] = statusCounts[status] + 1; // Increment by 1 because the status of N word is being changed to status
	}
	
	public int numberOfWordsWithStatus (int status) {
		return statusCounts[status];
	}
	
	public ArrayList wordsWithSamePatternAs (int N) {
		return wordLists[N];
	}
	
	public Translation getTranslation () {
		return translation;
	}
	
	/**
	 * Use if encoded translates into decoded; adds letters to key
	 * @param encoded
	 * @param decoded
	 */
	public void addWordTranslation (String encoded, String decoded) {
		for (int indexCounter = 0; indexCounter < encoded.length(); indexCounter++) {
			translation.translateLetterTo(encoded.substring(indexCounter, indexCounter + 1), decoded.substring(indexCounter, indexCounter + 1));
		}
	}
	
	public void TranslateNthWordTo (int N, String word) {
		String encoded = puzzleWords[N];
		// Update Translation
		for (int letterCounter = 0; letterCounter < encoded.length(); letterCounter++) {
			String encodedLetter = encoded.substring(letterCounter, letterCounter + 1);
			String decodedLetter = encoded.substring(letterCounter, letterCounter + 1);
			translation.translateLetterTo(encodedLetter, decodedLetter);
		}
		// Update status
		setStatus(N, TRANSLATED);
	}
	
	public int easiestToTranslate () {
		int translateIndex = -1;
		int smallestNumberOfWords = -1;
		
		for (int indexCounter = 0; indexCounter < wordLists.length; indexCounter++) {
			if (statusOfWord(indexCounter) == UNTRANSLATED){
				int wordListLength = wordLists[indexCounter].size();
				if ((wordListLength < smallestNumberOfWords && wordListLength > 0) || translateIndex == -1) {
					translateIndex = indexCounter;
					smallestNumberOfWords = wordListLength; 
				}
			}
		}
		return translateIndex;
	}
	
	public LinkedList<Puzzle> SolveMonalphabeticCiper (Puzzle puzzle, int cut, int recursionDepth) {
		LinkedList<Puzzle> puzzleSet = new LinkedList();
		if (puzzle.numberOfWordsWithStatus(UNTRANSLATED) == 0) {
			puzzleSet.add(puzzle);
		} else {
			// Pick an untranslated word
			int minimum = -1; // the minimum number of untranslatable
			int untranslatedIndex = puzzle.easiestToTranslate();
			String untranslatedWord = puzzle.nthWord(untranslatedIndex);
			
			if (recursionDepth == 0) {
				System.out.println(untranslatedWord + ": " + puzzle.wordsWithSamePatternAs(untranslatedIndex).size() + " words");
			}
			if (recursionDepth == 0) {
				// System.out.println("0: " + untranslatedWord);
			}
			if(untranslatedWord == null) {
				//System.out.println(puzzle.statusOfWord(0));

				//System.out.println(puzzle.numberOfWordsWithStatus(UNTRANSLATED));
			}
			ArrayList<String> wordsWithSamePattern = puzzle.wordsWithSamePatternAs(untranslatedIndex);
			for (String wordWithSamePattern: wordsWithSamePattern) {
				if (puzzle.getTranslation().couldTranslateTo(untranslatedWord, wordWithSamePattern)) {
					if (recursionDepth == 0) {
						// System.out.println("0-" + untranslatedWord + "- " + wordWithSamePattern);             
					}
					Puzzle newPuzzle = new Puzzle(puzzle);
					newPuzzle.setStatus(untranslatedIndex, TRANSLATED);
					newPuzzle.addWordTranslation(untranslatedWord, wordWithSamePattern);
					for (Puzzle possibleSolution: SolveMonalphabeticCiper(newPuzzle, cut, recursionDepth + 1)) {
						int numberOfUntranslatable = possibleSolution.numberOfWordsWithStatus(UNTRANSLATABLE);
						if (numberOfUntranslatable <= cut && (minimum == -1 || numberOfUntranslatable <= minimum)) {
							minimum = numberOfUntranslatable;
							puzzleSet.add(possibleSolution);
							cut = possibleSolution.numberOfWordsWithStatus(UNTRANSLATABLE);
						}
					}
					
				}
			}
			if (recursionDepth == 0) {
				// System.out.println("0 - Exiting WordsWithSamePattern loop");             
			}
			
			if (puzzle.numberOfWordsWithStatus(UNTRANSLATABLE) < cut && cut != 0) { // if cut is 0, there's no reason to try to find "untranslatable" combinations. They'll never be added to the solutions list.
				Puzzle newPuzzle = new Puzzle(puzzle);
				newPuzzle.setStatus(untranslatedIndex, UNTRANSLATABLE);
				for (Puzzle possibleSolution: SolveMonalphabeticCiper(newPuzzle, cut, recursionDepth + 1)) {
					int numberOfUntranslatable = possibleSolution.numberOfWordsWithStatus(UNTRANSLATABLE);
					if (possibleSolution.numberOfWordsWithStatus(UNTRANSLATABLE) < cut && (minimum == -1 || numberOfUntranslatable <= minimum)) {
						minimum = numberOfUntranslatable;
						puzzleSet.add(possibleSolution);
					}
				}
			}
			
			// Remove the minimum number of untranslatable
	
			puzzleSet = removeByMinimum(puzzleSet, minimum, 0);
		}
		return puzzleSet;
	}
	/** Remove all solutions except for ones with minimum number of untranslatable. If minimum is -1, then no minimum has been set.
		Should only ever run with a recursion depth of 2
	   	Could be done with two "for" loops, but that would be no fun
	**/
	public LinkedList<Puzzle> removeByMinimum (LinkedList<Puzzle> puzzles, int minimum, int recursionDepth) {
		if (recursionDepth >= 1) { // This should never happen. The maximum number of runs is one if the minimum is already known. if it is not known, the maximum number of runs is -1
			System.out.println("SOMETING WENT WRONG: the recursion depth in removeByMinimum() is too high. The minimum given in the first call was not the true minimum. The output of this function will stil be correct.");
		}
		
		Iterator<Puzzle> iterator = puzzles.iterator();
		int initialMinimum = minimum;
		while (iterator.hasNext()) {
			Puzzle puzzle = iterator.next();
			if (minimum == -1 || minimum > puzzle.numberOfWordsWithStatus(UNTRANSLATABLE)) { // if the existing minimum is greater than the number of untranslatable in the puzzle
				minimum = puzzle.numberOfWordsWithStatus(UNTRANSLATABLE);
				
			}else if (puzzle.numberOfWordsWithStatus(UNTRANSLATABLE) > minimum){ // The puzzle does not "meet" the minimum
				puzzles.remove(puzzle);
			}
		}
		
		// If the minimum changed during the "run", then it's possible that several puzzles that did not meet the minimum were missed
		if (minimum != initialMinimum) {
			removeByMinimum(puzzles, minimum, recursionDepth + 1); // Doesn't need to assign to anything because the reference to the object is the smae throughout
		}
		
		return puzzles;
	}
	
	public String getMessageTranslation () {
		String translationString = "";
		int wordCounter = 0;
		for (String word: puzzleWords) {
			wordCounter++;
			translationString += translation.translation(word);
			if (wordCounter != puzzleWords.length) {
				translationString += " ";
			}
		}
		return translationString;
	}
	
	public static PatternTable convertDictionaryToTable (WordDictionary dictionary) {
		PatternTable table = new PatternTable();
		int numberOfWords = 0;
		while(numberOfWords < dictionary.Size()) {
			String word = dictionary.NextWord();
			//System.out.println(word);
			table.AddWord(word);
			numberOfWords++;
		}
		return table;
	}
	
	public static String[] convertStringToArray (String message) {
		message = message.toLowerCase(); // Convert to lower case because canTranslateTo in Translation is caps sensistive
		ArrayList<String> stringList = new ArrayList(Arrays.asList(message.split(" ")));
		// Remove all spaces (Only occurs if there's multiple spaces directly adjacent to one another
		for (int indexCounter = 0; indexCounter < stringList.size(); indexCounter++) {
			String word = stringList.get(indexCounter);
			if (word.contains(" ") ||  word.length() == 0) { // The word is just a space
				stringList.remove(indexCounter);
				indexCounter--;
			} else {
				stringList.set(indexCounter, formatMessageString(word)); // Removes unwanted characters
			}
		}
		return stringList.toArray(new String[stringList.size()]);
	}
	
	public static String formatMessageString (String message) {
		Pattern removePattern = Pattern.compile("[.?,'’;:]");
		return message.replaceAll(removePattern.toString(), "");	
	}
	

}
