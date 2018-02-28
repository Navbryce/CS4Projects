
public class FirstPrimeCounter { //Big-O O(n)
	public boolean isPrime(int N){
		boolean isPrime = true;
		int magnitudeOfN = Math.abs(N);
		
		if(magnitudeOfN<2){
			isPrime = false;
		}else{
			for(int factorCounter=2; factorCounter<magnitudeOfN; factorCounter++){
				if(magnitudeOfN%factorCounter==0){
					isPrime=false;
				}
			}
		}
		
		return isPrime;
	}
	

	public int countPrimes(int limit){ //Counts primes between 1 and limit aka excluding 1 and limit
		int numberOfPrimes=0;
		for(int primeCounter=2; primeCounter<limit; primeCounter++){
			if(isPrime(primeCounter)){
				numberOfPrimes++;
			}
		}
		return numberOfPrimes;
	}
}


