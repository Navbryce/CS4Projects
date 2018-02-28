import java.util.ArrayList;

public class PrimeProject4 extends FirstPrimeCounter{
	private boolean[][] possibleNumbers; //true means not prime
	private ArrayList<Integer> mainPrimes;
	private boolean[] primarySieve;
	
	public int countPrimes(int limit){
		mainPrimes = new ArrayList();
		int squareRootOfN = (int)Math.sqrt(limit);//will round down if not a whole number
		int primesFound=0;
		int sizeOfArrays = squareRootOfN; 
		int numberOfArrays = squareRootOfN;
		int sizeOfFirstArray=0;
		
		//If rounded out/down
		if(sizeOfArrays*numberOfArrays<limit){
			int increaseSizeOfFirstArray = limit-(sizeOfArrays*numberOfArrays);
			sizeOfFirstArray+=increaseSizeOfFirstArray; //If the square root is not a whole number, this will "account" for the rounding
		}
		sizeOfFirstArray+=sizeOfArrays;


		
		primarySieve  = new boolean[sizeOfFirstArray];
		primarySieve[0]=true;
		primarySieve[1]=true;
		
		for(int possiblePrimeCounter=0; possiblePrimeCounter<=Math.sqrt(primarySieve.length); possiblePrimeCounter++){
			if(primarySieve[possiblePrimeCounter]!=true){
				mainPrimes.add(possiblePrimeCounter);
				removeMultiplesOfPrime(possiblePrimeCounter);
			}
		}
		for(int otherPrimarySievePrimes=(int)Math.sqrt(primarySieve.length)+1; otherPrimarySievePrimes<primarySieve.length; otherPrimarySievePrimes++){
			if(!primarySieve[otherPrimarySievePrimes]){
				mainPrimes.add(otherPrimarySievePrimes);
				//System.out.println(otherPrimarySievePrimes);
			}
		}
		primesFound=mainPrimes.size();
		
		//Removes all non-primes after the "primary sieve" thereby allowing one to find all the primes after the "primary sieve"
		for(int intervalCounter=0; intervalCounter<numberOfArrays-1; intervalCounter++){
			int segmentStartingPosition = sizeOfFirstArray-1 + intervalCounter * sizeOfArrays;
			primesFound+=removeMultiplesOfPrimes(sizeOfArrays, segmentStartingPosition);
		}

	

		return primesFound;
		
	}

	private void removeMultiplesOfPrime(int primeNumber){

	
			
		for(int possiblePrime=0; possiblePrime<primarySieve.length; possiblePrime+=primeNumber){
			if(!primarySieve[possiblePrime]){
				primarySieve[possiblePrime]=true;
			}
		}

	}
	//If transitioning from one array's index to another. counts primes in segments. Returns last multiple of prime
	private int removeMultiplesOfPrimes(int segmentSize, int segmentStartingPosition){
		int numberOfPrimes=segmentSize;
		boolean[] possiblePrimes = new boolean[segmentSize];
		for(int primesFoundCounter=0; primesFoundCounter<mainPrimes.size(); primesFoundCounter++){
			int primeNumber = mainPrimes.get(primesFoundCounter);
			int lastMultiple = ((int)((segmentStartingPosition-1)/primeNumber)*primeNumber); //-1 because outside the segment
		
			for(int primeCounter=lastMultiple + primeNumber-segmentStartingPosition; primeCounter<segmentSize; primeCounter+=primeNumber){
				if(!possiblePrimes[primeCounter]){
					possiblePrimes[primeCounter]=true; //true means not a boolean
					numberOfPrimes--;
					
				}
				if(primeCounter+primeNumber>=segmentSize){
					lastMultiple=primeCounter;
				}
			}
			
		}


		
		return numberOfPrimes;
	}
}
