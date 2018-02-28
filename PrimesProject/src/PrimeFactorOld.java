public class PrimeFactorOld extends FastestPrimeCounter{
	private int[] alreadyFound;
	//private ArrayList<Integer> primeFrequency;
	private int primeCounter=0;
	private int arrayPrimeCounter=0;
	
	public boolean isPrime(int N){
		boolean isPrime = true;
		int magnitudeOfN = Math.abs(N);
		if(magnitudeOfN<2){
			isPrime=false;
		}else{
			for(int foundPrimeCounter=0; foundPrimeCounter<arrayPrimeCounter && isPrime && alreadyFound[foundPrimeCounter]<=Math.sqrt(N); foundPrimeCounter++){
				if(N%alreadyFound[foundPrimeCounter]==0){ 
					//primeFrequency.set(foundPrimeCounter, primeFrequency.get(foundPrimeCounter)+1);
					isPrime=false;
				}
			}
		}
		
		return isPrime;
	}
	public int countPrimes(int limit){
		alreadyFound = new int[limit/3]; //Minimize the amount of copying needed by scaling the storage array based on limit
		primeCounter=0;
		arrayPrimeCounter=0;
		
		for(int primePossibleCounter=2; primePossibleCounter<limit; primePossibleCounter+=2){
			if(primePossibleCounter==4){
				primePossibleCounter=3; //Counter needs to check 2. Then, it increments by two (now equals 4) and switches to 3 so it checks only odd numbers
			}
			if(isPrime(primePossibleCounter)){
				 addPrimeToAlreadyFound(primePossibleCounter, limit);
			}
		}
		
		return primeCounter;
	}
	
	public void addPrimeToAlreadyFound(int N, int limit){
		primeCounter++;
		if(N!=2 && N<=Math.sqrt(limit)){ //Only add primes less than the squareRoot of the limit. 2 not added because even numbers are skipped in prime counter
			arrayPrimeCounter++;
			if(arrayPrimeCounter>alreadyFound.length){
				int[] newAlreadyFoundArray = new int[alreadyFound.length*2];
				for(int alreadyFoundCounter=0; alreadyFoundCounter<alreadyFound.length; alreadyFoundCounter++){
					newAlreadyFoundArray[alreadyFoundCounter]=alreadyFound[alreadyFoundCounter]; //Copies over
				}
				
				alreadyFound=newAlreadyFoundArray;
			}
	
			//primeFrequency.add(0);
			alreadyFound[arrayPrimeCounter-1] = N;
		}
		
	}
	
	/*private void printAllFrequency(){
		for(int primeCounter=0; primeCounter<arrayPrimeCounter; primeCounter++){
			System.out.println("The prime number, " + alreadyFound[primeCounter] + ", occurrs at a frequency of: " + primeFrequency.get(primeCounter));
		}
	}
	*/

}
