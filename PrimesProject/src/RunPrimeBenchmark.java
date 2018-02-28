//DO NOT CHANGE THIS FILE!!!
//If there are errors, fix your own program
public class RunPrimeBenchmark {
	
	public static int alivenessTest(){
		int i=(new PrimeCounter()).countPrimes(5);
		return i;
	}
	
	public static void main (String Args[])
    {
    	PrimeCounterTester test = new PrimeCounterTester();
		test.runTestCases();
    }
}