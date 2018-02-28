import java.util.ArrayList;

//DO NOT CHANGE THIS FILE!!!
//If there are errors, fix your own program
public class RunPatternTableBenchmark {
	
	public static ArrayList alivenessTest(){
		PatternTable t = new PatternTable();
		t.AddWord("X");
		ArrayList l = t.WordsWithSamePatternAs("X");
		return l;
	}
	public static void main (String Args[])
    {
    	PatternTableTester test = new PatternTableTester();
		test.runTestCases();
    }
}