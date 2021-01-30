package model;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Position {
	
	public double x;
	public double y;
	
	public double width;
	public double height;
	
	public int row;
	public int column;
	
	public Position(int i, int j){
		
		this.row = i;
		this.column = j;
		
		this.x = (Constant.screenWidth/15)*j;
		this.y = (Constant.screenHeight/15)*i;
		this.width = Constant.screenWidth/15;
		this.height = Constant.screenHeight/15;
		
	}

}
