import java.util.Scanner;

public class WordMaker {
	private String word="";
	private boolean makingWord = false;
	private boolean wordMade = false;
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public void reset(){
		word="";
		makingWord=false;
		wordMade=false;

	}
	
	public void addChar(char newChar){
		if(newChar=='\''){
			//ignore case?
		}else if(alphabet.indexOf(Character.toUpperCase(newChar))>=0){ //If the character is somewhere in the alphabet..
			//Letter case
			if(!makingWord){ //If you are starting a new word
				wordMade=false;
				makingWord=true;
				word="";
			}
			word+=newChar;
		}else{
			if(makingWord){
				makingWord=false;
				wordMade=true;
			}
		}
				
	}
	
	public boolean wordReady(){
		return wordMade;
	}
	
	public String getWord(){
		return word;
	}
	
	public static void main(String args[]){
		do{
			WordMaker wordMaker = new WordMaker();
			do{
				Scanner input = new Scanner(System.in);
				String inputString;
				do{
					System.out.print("Enter a character: ");
					inputString = input.nextLine();
				}while(inputString.length()!=1);
				wordMaker.addChar(inputString.charAt(0));
			}while(!wordMaker.wordReady());
			System.out.println("Word: " + wordMaker.getWord());
		}while(true);
	}
	
}
