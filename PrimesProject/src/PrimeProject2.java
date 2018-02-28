import java.util.ArrayList;

public class PrimeProject2 extends FirstPrimeCounter{
	private boolean[] possibleNumbers;
	private ArrayList<Integer> primesFound;
	
	public int countPrimes(int limit){
		possibleNumbers = new boolean[limit];
		for(int possiblePrimeCounter=0; possiblePrimeCounter<possibleNumbers.length; possiblePrimeCounter++){
			possibleNumbers[possiblePrimeCounter]=true;
		}
		
		
		primesFound = new ArrayList<Integer>();
		
		possibleNumbers[0]=false;
		possibleNumbers[1]=false;
		
		/* CHANGE TO GO THROUGH SIEVE NUMBERS */
		for(int possiblePrimeCounter=0; possiblePrimeCounter<possibleNumbers.length; possiblePrimeCounter++){
			if(possibleNumbers[possiblePrimeCounter]!=false){
				primesFound.add(possiblePrimeCounter);
				removeMultiplesOfPrime(possiblePrimeCounter);
			}
		}
		
		int numberOfPrimes=0;
		for(int possiblePrimeCounter=0; possiblePrimeCounter<possibleNumbers.length; possiblePrimeCounter++){
			if(possibleNumbers[possiblePrimeCounter]){
				numberOfPrimes++;
			}
		}
		return numberOfPrimes;
		
	}
	private boolean isPrime(int number, String somethingToDistinguishFromOtherMethod){
		boolean isPrime = true;
		
		for(int foundPrimeCounter=0; foundPrimeCounter<primesFound.size() && isPrime && primesFound.get(foundPrimeCounter)<=Math.sqrt(number); foundPrimeCounter++){
			if(number%primesFound.get(foundPrimeCounter)==0){ 
				//primeFrequency.set(foundPrimeCounter, primeFrequency.get(foundPrimeCounter)+1);
				isPrime=false;
			}
		}
		
		return isPrime;
		


	}
	private void removeMultiplesOfPrime(int primeNumber){
		for(int possibleNumbersCounter=2*primeNumber; possibleNumbersCounter<possibleNumbers.length; possibleNumbersCounter+=primeNumber){
			possibleNumbers[possibleNumbersCounter]=false;
		}
	}
}
