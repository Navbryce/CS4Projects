import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

public class WordGetter {
	public void GetWords(WordDictionary dict, String filename){
		try{
	        FileInputStream fileInput = new FileInputStream(new File(filename));
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileInput));
			String nextLine;
			
			WordMaker wordMaker = new WordMaker();
		
			while( (nextLine = fileReader.readLine()) != null ){
				for(int indexCounter = 0; indexCounter < nextLine.length(); indexCounter++){
					wordMaker.addChar(nextLine.charAt(indexCounter));
					if(wordMaker.wordReady()){
						dict.AddWord(wordMaker.getWord());
						wordMaker.reset();
					}
				}
				wordMaker.addChar('.'); //The current line has ended. Add stop punctuation so wordMaker knows the current word should end.
				if(wordMaker.getWord().length() > 0){ //Make sure there is actually a word to add because of the new line
					dict.AddWord(wordMaker.getWord());
				}
			}

		}catch(Exception ex){
			System.out.println("The file could not be found");
			ex.printStackTrace();
		}

	}
	public static void main(String[] args){
		WordGetter wordGetter = new WordGetter();
		HashSet set = new HashSet();
		WordDictionary dictionary = new WordDictionary(set);
		wordGetter.GetWords(dictionary, "words_short.txt");
		dictionary.Write("dictionary_short");
	}
}
