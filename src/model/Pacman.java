package model;

import java.util.Random;

import application.mapGen;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Pacman {
	
	private Position pos;
	private int points;
	private Random rand = new Random();
	private ImageView pacmanImage;
	
	public Pacman(){
		points=0;
		pos = new Position(1,1);
		
		pacmanImage = new ImageView("./resources/right.gif");
	}
	
	public boolean Up() {
		if (Grid.getCell(pos.row-1, pos.column).getType() == Constant.OBJECT) {
			return false;
		}
		else {
			pacmanImage.setRotate(90+180);
    	
			pos = new Position(pos.row-1, pos.column);
			
			checkCoin(pos);
		}
		
    	return true;
	}
	
	public boolean Down() {
		if (Grid.getCell(pos.row+1, pos.column).getType() == Constant.OBJECT) {
			return false;
		}
		else {
			pacmanImage.setRotate(90);
    	
			pos = new Position(pos.row+1, pos.column);
			
			checkCoin(pos);
		}
		
    	return true;
	}
	
	public boolean Left() {
		if (Grid.getCell(pos.row, pos.column-1).getType() == Constant.OBJECT) {
			return false;
		}
		else {
			pacmanImage.setRotate(-180);
    	
			pos = new Position(pos.row, pos.column-1);
			
			checkCoin(pos);
		}
		
    	return true;
	}
	
	public boolean Right() {
		if (Grid.getCell(pos.row, pos.column+1).getType() == Constant.OBJECT) {
			return false;
		}
		else {
			pacmanImage.setRotate(0);
    	
			pos = new Position(pos.row, pos.column+1);
			
			checkCoin(pos);
		}
		
    	return true;
	}
	
	public boolean Teleport() {
		boolean good = false;
		while(!good) {
			int row = rand.nextInt(15) + 1;
			int col = rand.nextInt(15) + 1;
			if(Grid.getCell(row, col).getType()==Constant.COIN || Grid.getCell(row, col).getType()==Constant.EMPTY) {
				pos = new Position(row, col);
				
				checkCoin(pos);
				good=true;
			}
		}
		return false;
		
	}
	
	private boolean checkCoin(Position p) {
		if(Grid.getCell(p.row, p.column).getType() == Constant.COIN) {
			points++;
			Grid.getCell(p.row, p.column).setType(Constant.EMPTY);
			return true;
		}
		else if(Grid.getCell(p.row, p.column).getType() == Constant.FRUIT) {
			points+=10;
			Grid.getCell(p.row, p.column).setType(Constant.EMPTY);
			return true;
		}
		else
			return false;
	}
	
	public int getScore() {
		return points;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public void setPosition(Position p) {
		pos=p;
	}
	
	public Node getNode(){
		Node node;
		
		double min = pos.height;
		if (pos.width < pos.height)
			min = pos.width;
		
		pacmanImage.setFitWidth(min);
		pacmanImage.setFitHeight(min);

		pacmanImage.setX(pos.x+pos.width/2 - min/2);
		pacmanImage.setY(pos.y+pos.height/2 - min/2);
		
		node= pacmanImage;
		
		return node;
	}

}
