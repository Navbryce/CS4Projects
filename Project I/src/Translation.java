import java.util.HashMap;

public class Translation {
	private HashMap<String, String> decoder;
	
	public Translation(){
		decoder =  new HashMap();
	}
	Translation(Translation t){
		decoder = (HashMap<String, String>)t.getTranslation().clone();
	}
	public void translateLetterTo(String encoded, String decoded){
		decoder.put(encoded, decoded);
	}
	/***
	 * @param letter
	 * @return True if the letter is already in the decoder
	 */
	public boolean isTranslated(String letter){
		return decoder.containsKey(letter);
	}
	/***
	 * 
	 * @return Returns the translation HashMap
	 */
	public HashMap<String, String> getTranslation(){
		return decoder;
	}
	public String translation(String encoded){ 
		String returnString = "";
		boolean missingLetterInTranslation = true;
		for(int indexCounter =  0; indexCounter < encoded.length(); indexCounter++){
			String letter = encoded.substring(indexCounter, indexCounter + 1);
			if(isTranslated(letter)){
				letter = decoder.get(letter); //Gets the translated form of the letter
			}else{
				letter = "*";
			}
			
			returnString += letter; //Adds the (translated depending on the above condition) letter to the decoded/return string
		}

		return returnString; 
	}
	public boolean canBeTranslated(String encoded){
		String translatedString = translation(encoded);
		return !translatedString.contains("*");
	}
	public boolean couldTranslateTo(String encoded, String word){ // caps sensitive
		boolean couldTranslateTo  = true;
		String decoded = translation(encoded);
		if(decoded.length() != word.length()){
			couldTranslateTo = false;
		}else{
			if (!PatternMaker.MakePattern(encoded).equals(PatternMaker.MakePattern(word))){
				couldTranslateTo = false;
			}else{
				for(int indexCounter = 0; indexCounter < decoded.length() && couldTranslateTo; indexCounter ++){
					String decodedLetter = decoded.substring(indexCounter, indexCounter + 1);
					String wordLetter = word.substring(indexCounter, indexCounter + 1);
					boolean letterInDecoder = decoder.containsValue(wordLetter);
					if(!(decodedLetter.equals("*") && !letterInDecoder) && !decodedLetter.equals(wordLetter)){
						couldTranslateTo = false;
					}
				}
			}
		}
		return couldTranslateTo;
	}
	
	public static void main(String args[]){
		String encoded = "czt";
		Translation translation = new Translation();
		translation.translateLetterTo("z", "a");
		System.out.println("Should translate to '*a*': " + translation.translation(encoded));
		translation.translateLetterTo("c", "c");
		System.out.println("Should translate to 'ca*': " + translation.translation(encoded));
		translation.translateLetterTo("t", "t");
		System.out.println("Should translate to 'cat': " + translation.translation(encoded));
		
		int counter = 0;
		do{
			System.out.println("Is translated test:");
			System.out.println("Is 'z' translated--should return true: " + translation.isTranslated("z"));
			System.out.println("Is 'c' translated--should return true: " + translation.isTranslated("c"));
			System.out.println("Is 't' translated--should return true: " + translation.isTranslated("t"));
			System.out.println("Is 'y' translated--should return false: " + translation.isTranslated(""));
			
			System.out.println("Can be translated test:");
			System.out.println("Can 'czt' be translated--should return true: " + translation.canBeTranslated(encoded));
			System.out.println("Can 'notranslation' be translated--should return false: " + translation.canBeTranslated("notranslation"));
	
			System.out.println("Can translate to:");
			System.out.println("'czt' can translate to 'cat'--should return true: " + translation.couldTranslateTo("czt", "cat"));
			System.out.println("'carolina' can translate to 'cassioss'--should return false: " + translation.couldTranslateTo("carolina", "cassioss"));
			System.out.println("'czrolina' can translate to 'cassioss'--should return false: " + translation.couldTranslateTo("czrolina", "cassioss"));
			System.out.println("'cassioss' can translate to 'cassioss'--should return false: " + translation.couldTranslateTo("cassioss", "cassioss"));
			System.out.println("'czt' can translate to 'bat'--should return false: " + translation.couldTranslateTo("czt", "bat"));
			System.out.println("'czt' can translate to 'translate'--should return false: " + translation.couldTranslateTo("czt", "translate"));
			
			counter++;
			if (counter == 1){
				System.out.println("\n\nTranslation constructor with decoder parameter test:");
				translation = new Translation(translation);
				
			}
		}while(counter <= 1);
			



	}


}
