import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WordDictionary {
	private Iterator setIterator;
	
	@XmlElementWrapper
	@XmlElement(name="word")
	private Set wordStorage;
	
	public WordDictionary(Set storage){
		storage.clear();

		setIterator = storage.iterator();		
		
		wordStorage=storage;
	}
	public WordDictionary(){
		//For JAXB
		
		//setIterator not instatiated so done in NextWord method
		
		//THIS METHOD SHOULD NOT BE USED AS A CONSTRUCTOR FOR THE DICTIONARY
	}

	
	public void AddWord(String word){
		wordStorage.add(word.toLowerCase());
	}
	public boolean IsInDictionary(String word){
	
		return wordStorage.contains(word.toLowerCase());
	}
	
	public void ResetList(){
		setIterator = wordStorage.iterator(); //Resets the iterator by creating a new instance of the iterator
	}
	
	public String NextWord(){
		String word;
		try{
			word = (String)setIterator.next();
		}catch(Exception ex){
			//When JAXB reads from a file, it does not instatitate the setIterator.
			setIterator = wordStorage.iterator();
			word = NextWord();
		}
		return word;
	
	}
	
	public int Size(){
		return wordStorage.size();
	}
	
	//Exclude .xml
	public void Write(String fileName){
		try{
			JAXBContext jaxContext = JAXBContext.newInstance(WordDictionary.class);
			Marshaller marshaller = jaxContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(this, new File(fileName + ".xml"));
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("An error occurred while encoding the dictionary to a fie");
		}
		
	}
	//Exclude .xml
	public void Read(String fileName){
		try{
			
			JAXBContext jaxContext = JAXBContext.newInstance(WordDictionary.class);
			Unmarshaller unmarshaller = jaxContext.createUnmarshaller();
			WordDictionary dictionary = (WordDictionary)unmarshaller.unmarshal(new File(fileName + ".xml"));
			copyDictionaryOver(this, dictionary); //Copies the dictionary from the file into the current dictionary
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("An error occurred while reading the dictionary from a file");
		}
	}
	private void copyDictionaryOver(WordDictionary dictionaryBeingUsed, WordDictionary dictionaryBeingCopied){
		int wordCounter=0;
		while(wordCounter<dictionaryBeingCopied.Size()){
			dictionaryBeingUsed.AddWord(dictionaryBeingCopied.NextWord());
			wordCounter++;
		}
	}
	
	public static void main(String args[]){
		int[] numberOfWordsList = {100};
		for(int numberOfWords: numberOfWordsList){
			System.out.println("NUMBER OF WORDS: " + numberOfWords);
			Set[] setsArray = {new HashSet(), new TreeSet()};
			for(int setTypeCounter=0; setTypeCounter<setsArray.length; setTypeCounter++){
				 Set set = setsArray[setTypeCounter];
				 String setType = set.getClass().toString();
				 System.out.println("Testing Set: " + setType);
				 double startTime = System.currentTimeMillis();
				 WordDictionary dictionary = new WordDictionary(set);
				 for(int stringCounter=0; stringCounter<numberOfWords; stringCounter++){
					 dictionary.AddWord(generateRandomString());
				 }
				 String dictionaryFileName = "dictionaryFileFor" + setType;
				 dictionary.Write(dictionaryFileName);
				 dictionary.Read(dictionaryFileName);
				 System.out.println("The dictionary which read its own file contains " + dictionary.Size() + " words");
				 Set newDictionarySet;
				 if(setType.contains("tree")){
					 newDictionarySet = new TreeSet();
				 }else{
					 newDictionarySet = new HashSet();
				 }
				 WordDictionary newDictionary =  new WordDictionary(newDictionarySet);
				 newDictionary.Read(dictionaryFileName);
				 System.out.println("A new dictionary which read-in the previous dictionary file contains " + dictionary.Size() + " words. Should be the same as the last output.");
				 
				 String word;
				 int wordCount=0;
				 while((word=newDictionary.NextWord()) != null && wordCount<newDictionary.Size()){
					// System.out.println(word);
					 wordCount++;
				 }
				 System.out.println("Word Count from Iteration through:" + wordCount);
				 			 
				 double endTime = System.currentTimeMillis();
				 System.out.println("Time for " + setType + ":" + " " + (endTime-startTime));
			}
			 System.out.println("\n\n");

		}



	}
	
	public static void printDictionary(WordDictionary dictionary){
		System.out.println("PRINTING DICTIONARY");
		int wordCounter=0;
		while(wordCounter<dictionary.Size()){
			System.out.println("ENTRY:" + dictionary.NextWord());
			wordCounter++;
		}
	}

	public static String generateRandomString(){
		Random randomGenerator = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int stringLength = randomGenerator.nextInt(10)+4;
		
		String randomString="";
		for(int letterCounter=1; letterCounter<=stringLength; letterCounter++){
			randomString+=alphabet.charAt(randomGenerator.nextInt(alphabet.length()));
		}
		
		return randomString;
	}
}
