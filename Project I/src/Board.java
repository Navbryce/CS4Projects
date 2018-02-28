public class Board {
	public static final int EMPTY = 0;
	public static final int RESTRICTED = -1;
	public static final int QUEEN = 1;
	private int[][] boardArray;
	

	
	public Board(int numberOfRows, int numberOfColumns){
		boardArray = new int[numberOfRows][numberOfColumns];
	}
	/**
	 * 
	 * @param startingBoard Will be copied OVER and not modified
	 */
	public Board(int[][] startingBoard){
		boardArray = new int[startingBoard.length][startingBoard[0].length];
		for(int rowCounter = 0; rowCounter < boardArray.length; rowCounter++){
			for(int columnCounter = 0; columnCounter < boardArray[0].length; columnCounter++){
				boardArray[rowCounter][columnCounter] = startingBoard[rowCounter][columnCounter];
			}
		}
	}
	
	
	public int[][] getBoardArray(){
		return boardArray;
	}
	public void setSpaceStatus(int row, int column, int status){
		boardArray[row][column] = status;
	}
	public void setRowStatus(int row, int status){
		for(int columnCounter = 0; columnCounter < boardArray[0].length; columnCounter++){
			setSpaceStatus(row, columnCounter, status);
		}
	}
	public void setColumnStatus(int column, int status){
		for(int rowCounter = 0; rowCounter < boardArray.length; rowCounter++){
			setSpaceStatus(rowCounter, column, status);
		}
	}
	/**
	 * 
	 * @param row Row of point that defines diagonal
	 * @param column Column of point that defines diagonal
	 * @param status What the statuses of the diagonals should be set to
	 */
	public void setDiagonalStatus(int row, int column, int status){
		//First diagonal
		int rowCounter = row;
		int columnCounter = column;
		while(rowCounter < boardArray.length && columnCounter < boardArray[0].length){
			setSpaceStatus(rowCounter, columnCounter, RESTRICTED);
			rowCounter++;
			columnCounter++;
		}
		rowCounter = row;
		columnCounter = column;
		while(rowCounter >= 0 && columnCounter >= 0){
			setSpaceStatus(rowCounter, columnCounter, RESTRICTED);
			rowCounter--;
			columnCounter--;
		}
		
		//Second diagonal
		rowCounter = row;
		columnCounter = column;
		while(rowCounter < boardArray.length && columnCounter >= 0){
			setSpaceStatus(rowCounter, columnCounter, RESTRICTED);
			rowCounter++;
			columnCounter--;
		}
		rowCounter = row;
		columnCounter = column;
		while(rowCounter >= 0 && columnCounter < boardArray[0].length){
			setSpaceStatus(rowCounter, columnCounter, RESTRICTED);
			rowCounter--;
			columnCounter++;
		}
		
	}

	public Space firstEmptySpace(){
		Space space = null;
		for(int rowCounter = 0; rowCounter < boardArray.length && space == null; rowCounter++){
			for(int columnCounter = 0; columnCounter < boardArray[0].length && space == null; columnCounter++){
				if(boardArray[rowCounter][columnCounter] == EMPTY){
					space = new Space(rowCounter, columnCounter);
				}
			}
		}

		
		return space;
	}
	public void printBoard(){
		for(int[] rowArray: boardArray){
			for(int spaceValue: rowArray){
				char charValue;
				if (spaceValue == QUEEN){
					charValue = 'Q';
				}else if(spaceValue == RESTRICTED){
					charValue = 'R';
				}else{
					charValue = 'E';
				}
				System.out.print(charValue + " ");
			}
			System.out.println();
		}
		System.out.println();

	}
}
