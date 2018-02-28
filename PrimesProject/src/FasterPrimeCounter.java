
public class FasterPrimeCounter extends FirstPrimeCounter{ //Big-O (N)
	public boolean isPrime(int N){
		boolean isPrime = true;
		int magnitudeOfN = Math.abs(N);
		
		if(magnitudeOfN<2){
			isPrime = false;
		}else{
			for(int factorCounter=2; factorCounter<=magnitudeOfN/2; factorCounter++){
				if(magnitudeOfN%factorCounter==0){
					isPrime=false;
				}
			}
		}
		
		return isPrime;
	}
}
