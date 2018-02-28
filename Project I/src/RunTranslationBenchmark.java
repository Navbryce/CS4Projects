//DO NOT CHANGE THIS FILE!!!
//If there are errors, fix your own program
public class RunTranslationBenchmark {
	
	public String alivenessTest(){
		Translation t = new Translation();
		t.translateLetterTo("A", "B");
		String s = t.translation("X");
		boolean b = t.isTranslated("X") || t.couldTranslateTo("A", "B");
		if (b)
			return s;
		else
			return"";
			
	}
	public static void main (String Args[])
    {
    	TranslationTester test = new TranslationTester();
		test.runTestCases();
    }
}