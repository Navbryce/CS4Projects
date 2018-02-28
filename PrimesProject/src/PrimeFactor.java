
public class PrimeFactor {
	private int frequency;
	private int number;
	
	public PrimeFactor(int N){
		number=N;
		frequency=0;
	}
	public void incrementFrequency(){
		frequency++;
	}
	public int getNumber(){
		return number;
	}
}
