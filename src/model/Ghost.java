package model;

import java.util.Random;

import application.mapGen;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Ghost {

	protected Position pos;
	private Pacman pacman;
	private mapGen mG;
	private ImageView ghostImage;

	public Ghost(Pacman pm, mapGen mG) {

		this.pos = new Position(Constant.ROW_CELL_COUNT / 2, Constant.COLUMN_CELL_COUNT / 2);
		
		ghostImage = new ImageView("./resources/blue mid.gif");
		
		pacman=pm;
		
		this.mG = mG;

	}

	public void moveGhost() {

		Random rand = new Random();
		int randomNum = rand.nextInt((3 - 0) + 1) + 0;

		if (pacman.getPosition().row > this.pos.row) {

			if (pacman.getPosition().column > this.pos.column) {

				randomNum = rand.nextInt((1 - 0) + 1) + 0;

				if (randomNum == 0)
					moveDown();
				else
					moveRight();

			}

			if (pacman.getPosition().column <= this.pos.column) {

				randomNum = rand.nextInt((1 - 0) + 1) + 0;

				if (randomNum == 0)
					moveDown();
				else
					moveLeft();

			}

		} else if (pacman.getPosition().row <= this.pos.row) {

			if (pacman.getPosition().column >= this.pos.column) {

				randomNum = rand.nextInt((1 - 0) + 1) + 0;

				if (randomNum == 0)
					moveUp();
				else
					moveRight();

			}

			if (pacman.getPosition().column < this.pos.column) {

				randomNum = rand.nextInt((1 - 0) + 1) + 0;

				if (randomNum == 0)
					moveUp();
				else
					moveLeft();

			}
			
			if (pos.row == pacman.getPosition().row && pos.column == pacman.getPosition().column ){
		    	mG.gameEnded();		
			}
			else{
	    		mG.redrawMap();
			}

		}

	}

	public boolean moveUp() {

		if (Grid.getCell(pos.row - 1, pos.column).getType() == Constant.OBJECT)
			return false;

		pos = new Position(pos.row - 1, pos.column);
		ghostImage = new ImageView("./resources/blue up.gif");
		mG.redrawMap();
		return true;

	}

	public boolean moveDown() {

		if (Grid.getCell(pos.row + 1, pos.column).getType() == Constant.OBJECT)
			return false;

		pos = new Position(pos.row + 1, pos.column);
		ghostImage = new ImageView("./resources/blue down.gif");
		mG.redrawMap();
		return true;

	}

	public boolean moveLeft() {

		if (Grid.getCell(pos.row, pos.column - 1).getType() == Constant.OBJECT)
			return false;

		pos = new Position(pos.row, pos.column - 1);
		ghostImage = new ImageView("./resources/blue left.gif");
		mG.redrawMap();
		return true;

	}

	public boolean moveRight() {

		if (Grid.getCell(pos.row, pos.column + 1).getType() == Constant.OBJECT)
			return false;

		pos = new Position(pos.row, pos.column + 1);
		ghostImage = new ImageView("./resources/blue right.gif");
		mG.redrawMap();
		return true;

	}
	
	public  Node getNode(){
		Node node;
	
		double min = pos.height;
		if (pos.width < pos.height)
			min = pos.width;
		
		ghostImage.setFitWidth(min);
		ghostImage.setFitHeight(min);
	
		ghostImage.setX(pos.x+pos.width/2 - min/2);
		ghostImage.setY(pos.y+pos.height/2 - min/2);
		
		node = ghostImage;
		
		return node;
	
}
}