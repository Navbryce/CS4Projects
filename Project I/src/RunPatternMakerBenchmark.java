//DO NOT CHANGE THIS FILE!!!
//If there are errors, fix your own program
public class RunPatternMakerBenchmark {
	public static String alivenessTest(){
		String s = PatternMaker.MakePattern("ABCD");
		return s;
	}
	
	public static void main (String Args[])
    {
    	PatternMakerTester test = new PatternMakerTester();
		test.runTestCases();
    }
}