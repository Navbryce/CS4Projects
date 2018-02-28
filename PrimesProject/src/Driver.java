
public class Driver {
	public static void main(String args[]){
		FirstPrimeCounter[] primeCounterArray = {new PrimeCounter()};
		String[] names = {"Prime Counter 5"};
		for(int primeCounterCounter=0; primeCounterCounter<primeCounterArray.length; primeCounterCounter++){
			FirstPrimeCounter primeCounter=primeCounterArray[primeCounterCounter]; //Not really FirstPrimeCounter. Just extended firstPrimeCounter for other classes
			System.out.println("Prime Counter Test for " + names[primeCounterCounter] + ": ");

			/*
			//Is prime test
			int[] possiblePrimes = {};
			
			for(int possiblePrimeCounter=0; possiblePrimeCounter<possiblePrimes.length; possiblePrimeCounter++){
				int possiblePrime = possiblePrimes[possiblePrimeCounter];
				System.out.println("Is " + possiblePrime + " a prime: " + primeCounter.isPrime(possiblePrime));
			}
			*/
			
			//Prime counter test
	  		int[] limitList = {1000000000};
			int numberOfSamples=1;
			for(int limitCounter=0; limitCounter<limitList.length; limitCounter++){
				int limit = limitList[limitCounter];
				double totalTime=0;
				int numberOfPrimes=0;

				for(int sampleCounter=0; sampleCounter<numberOfSamples; sampleCounter++){
					double startTime =System.currentTimeMillis();
					numberOfPrimes = primeCounter.countPrimes(limit);
					double endTime = System.currentTimeMillis();
					//System.out.println("For the limit of " + limit + ", " + numberOfPrimes + " primes were found in " + (endTime-startTime) + " ms.");	
					totalTime+=endTime-startTime;
				}
				System.out.println("For the limit of " + limit + ", " + numberOfPrimes + " in " + numberOfSamples + " cases had an average time of " + totalTime/numberOfSamples);
			}
		}
		


		
	}
}
