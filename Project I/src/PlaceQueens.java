
public class PlaceQueens {
	public static void main(String args[]){
		System.out.println("8 X 8 Solution");
		Board board = new Board(8, 8);
		PlaceQueens(board, 8).printBoard();
		
		System.out.println("12 X 12 Solution");
		board = new Board(12, 12);
		PlaceQueens(board, 12).printBoard();
		
	}
	
	/**
	 * 
	 * @param board - The board the queens are being placed on
	 * @param numberOfQueens - Number of queens being placed
	 * @return Will return null if no solution is found
	 */
	public static Board PlaceQueens(Board board, int numberOfQueens){
		Board boardToReturn = null;
		if (numberOfQueens == 0){
			boardToReturn = board;
		}else{
			Space emptySpace;
			while(boardToReturn == null && (board.firstEmptySpace()) != null){				
				emptySpace = board.firstEmptySpace();
				Board testBoard = new Board(board.getBoardArray());
				int row = emptySpace.getRowIndex();
				int column = emptySpace.getColumnIndex();
				//Set restricted before setting queen space otherwise the QUEEN status will be overwritten
				testBoard.setColumnStatus(column, Board.RESTRICTED);
				testBoard.setRowStatus(row, Board.RESTRICTED);
				testBoard.setDiagonalStatus(row, column, Board.RESTRICTED);
				testBoard.setSpaceStatus(emptySpace.getRowIndex(), emptySpace.getColumnIndex(), Board.QUEEN);
				
				Board solutionBoard = PlaceQueens(testBoard, numberOfQueens-1);
				if(solutionBoard == null){
					board.setSpaceStatus(emptySpace.getRowIndex(), emptySpace.getColumnIndex(), Board.RESTRICTED);
				}else{
					boardToReturn = solutionBoard;
				}
				
			}
		
		}
		return boardToReturn;

	}
}
