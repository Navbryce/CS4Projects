import java.util.ArrayList;

public class PrimeProject3 extends FirstPrimeCounter{
	private boolean[][] possibleNumbers; //true means not prime
	private int primesFound;
	
	public int countPrimes(int limit){
		int squareRootOfN = (int)Math.sqrt(limit);//will round down if not a whole number
		primesFound=limit;
		
		int sizeOfArrays = squareRootOfN; 
		int numberOfArrays = squareRootOfN;
		int sizeOfFirstArray=0;
		
		//If rounded out/down
		if(sizeOfArrays*numberOfArrays<limit){
			int increaseSizeOfFirstArray = limit-(sizeOfArrays*numberOfArrays);
			sizeOfFirstArray+=increaseSizeOfFirstArray; //If the square root is not a whole number, this will "account" for the rounding
		}
		sizeOfFirstArray+=sizeOfArrays;

		possibleNumbers = new boolean[numberOfArrays][];
		for(int arrayCounter=0; arrayCounter<numberOfArrays; arrayCounter++){
			if(arrayCounter==0){ //If it is the first array, set it to the sizeOfFirstArray
				possibleNumbers[arrayCounter] = new boolean[sizeOfFirstArray];
			}else{
				possibleNumbers[arrayCounter] = new boolean[sizeOfArrays];
			}
			boolean[] arrayBeingSet = possibleNumbers[arrayCounter];
			
			
			
		}
		
		boolean[] primarySieve  = possibleNumbers[0];
		primarySieve[0]=true;
		primarySieve[1]=true;
		primesFound-=2;
		
		/* CHANGE TO GO THROUGH SIEVE NUMBERS */
		for(int possiblePrimeCounter=0; possiblePrimeCounter<primarySieve.length; possiblePrimeCounter++){
			if(primarySieve[possiblePrimeCounter]!=true){
				removeMultiplesOfPrime(possiblePrimeCounter);
			}
		}
		

	

		return primesFound;
		
	}

	private void removeMultiplesOfPrime(int primeNumber){
		int numberTranslation = 0;
		int lastMultiple = primeNumber;

		for(int arrayCounter=0; arrayCounter<possibleNumbers.length; arrayCounter++){
			boolean[] arrayOfPossible = possibleNumbers[arrayCounter];
			
			for(int possiblePrime=lastMultiple+primeNumber-numberTranslation; possiblePrime<arrayOfPossible.length; possiblePrime+=primeNumber){
				if(!arrayOfPossible[possiblePrime]){
					arrayOfPossible[possiblePrime]=true;
					primesFound-=1;
				}


				if(possiblePrime+primeNumber>=arrayOfPossible.length){
					lastMultiple=possiblePrime;
				}
			}
			numberTranslation = arrayOfPossible.length; //The number of "indexes" that the value needs to be translated down if switching to a new array

		}
	}
}
