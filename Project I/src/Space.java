public class Space{
	private int rowIndex = -1;
	private int columnIndex = -1;
	public Space(int row, int column){
		rowIndex = row;
		columnIndex = column;
	}
	public int getRowIndex(){
		return rowIndex;
	}
	public int getColumnIndex(){
		return columnIndex;
	}
}