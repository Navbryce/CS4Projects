
public class FastestPrimeCounter extends FirstPrimeCounter{
	private int[] alreadyFound;
	private int primeCounter=0;
	public boolean isPrime(int N){
		boolean isPrime = true;
		int magnitudeOfN = Math.abs(N);
		if(magnitudeOfN<2){
			isPrime=false;
		}else{
			for(int foundPrimeCounter=0; foundPrimeCounter<primeCounter && isPrime && alreadyFound[foundPrimeCounter]<=Math.sqrt(N); foundPrimeCounter++){
				if(N%alreadyFound[foundPrimeCounter]==0){
					isPrime=false;
				}
			}
		}
		
		return isPrime;
	}
	
	public int countPrimes(int limit){
		alreadyFound = new int[10];
		primeCounter=0;
		
		for(int primeCounter=2; primeCounter<limit; primeCounter++){
			if(isPrime(primeCounter)){
				 addPrimeToAlreadyFound(primeCounter, limit);
			}
		}
		
		return primeCounter;
	}
	
	protected void addPrimeToAlreadyFound(int N, int limit){
		primeCounter++;
		if(primeCounter<=Math.sqrt(limit)){ //Only add primes less than the squareRoot of the limit
			if(primeCounter>alreadyFound.length){
				int[] newAlreadyFoundArray = new int[alreadyFound.length*2];
				for(int alreadyFoundCounter=0; alreadyFoundCounter<alreadyFound.length; alreadyFoundCounter++){
					newAlreadyFoundArray[alreadyFoundCounter]=alreadyFound[alreadyFoundCounter]; //Copies over
				}
				
				alreadyFound=newAlreadyFoundArray;
			}
	
	
			alreadyFound[primeCounter-1] = N;
		}
		
	}
	/*Q4
	 * Every non-prime number has at least one PRIME factor that is less than the square root of that number. Similarly,
	 * there is no reason to add to the "list of possible primes" prime numbers greater than the square root of the limit in count primes, because any number
	 * in the range of 2 to limit--if they are non-prime--must have a prime factor less than the square root of that number, which is inherently less than
	 * the square root of the limit. Thus, no information-giving divisor will be missed for a number.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
