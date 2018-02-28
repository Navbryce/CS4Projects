
public class SquareRootPrimeCounter extends FirstPrimeCounter{
	public boolean isPrime(int N){
		boolean isPrime = true;
		int magnitudeOfN = Math.abs(N);
		
		if(magnitudeOfN<2){
			isPrime = false;
		}else{
			for(int factorCounter=2; factorCounter<=Math.sqrt(magnitudeOfN); factorCounter++){
				if(magnitudeOfN%factorCounter==0){
					isPrime=false;
				}
			}
		}
		
		return isPrime;
	}
	
	
	//EXPLANATION:
	/*Every whole number factor of a number has a complementary whole number. They come
	 * in pairs. For example, for the number 10, you can divide by 2 and get 5, or you can divide by  5
	 * and get 2. The complementary factor pair being (2, 5). When calculating primes, if you test 2 then later 5 as
	 * a factor, you are wasting time; it is redundant. If you know 2 divides evenly, then you also know 5 inherently divides
	 * evenly. If you are testing factors that start at 2 and go to N, the PEAK of the "factor mountain" is marked by the sqrt(N).
	 * After this peak, you start running into the complementary factor of a factor you already tested. Thus, any operations after
	 * this point are fruitless and a waste of resources. There's no reason to test a complementary factor because testing the original factor (the first
	 * number in the complementary number pair), you have essentially already tested the complementary factor. Since testing complementary factors
	 * is no different from testing the original factor, I know the algolrithim is not missing a possible factor of N by limiting the factor checkers to the sqrt(N) 
	 * (sqrt(N) marks the point where the factors go from "unique" to complementary of the unique factors).
	 * 
	 * 
	 */
}
