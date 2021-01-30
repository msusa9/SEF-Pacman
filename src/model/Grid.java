package model;

public class Grid {
	
	protected static Cell [] [] grid;
	
	public Grid(){
		
		grid = new Cell [Constant.ROW_CELL_COUNT] [Constant.COLUMN_CELL_COUNT];
		
	}
	
	public static void addCell(Cell cell){
		
		grid [cell.position.row][cell.position.column] = cell;
		
	}
	
	public static Cell getCell(int row, int column){
		
		return grid [row][column];
		
	}

}
