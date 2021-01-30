package model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Cell {
	private int type;
	protected Position position;
	private Node node;
	private ImageView fruitImage;
	
	public Cell(Position position, int type){
		this.position = position;
		this.type = type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
	
	public Node getNode(){
		
		if (type == Constant.COIN){
			this.node = new Circle(position.x+position.width/2,position.y+position.height/2,position.width/8);
			((Circle)node).setFill(Color.YELLOW);
		}
		else if (type == Constant.OBJECT){
			this.node = new Rectangle(position.x,position.y,position.width,position.height);
			((Rectangle)node).setFill(Color.BLUE);
		}
		else if (type == Constant.EMPTY){
			this.node = new Rectangle(position.x,position.y,position.width,position.height);
			((Rectangle)node).setFill(Color.BLACK);
		}
		else if (type == Constant.FRUIT) {
			double min = position.height;
			if (position.width < position.height)
				min = position.width;
			
			fruitImage =  new ImageView("./resources/Cherry.png");
			
			fruitImage.setFitWidth(min);
			fruitImage.setFitHeight(min);

			fruitImage.setX(position.x+position.width/2 - min/2);
			fruitImage.setY(position.y+position.height/2 - min/2);
			
			this.node= fruitImage;
		}
		
		return node;
		
	}
	
	
}
