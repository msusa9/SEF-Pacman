package model;

import java.util.ArrayList;

public class fruitsAndObstacles {
	
	private ArrayList<Position> obstacles = new ArrayList<>();
	private ArrayList<Position> fruits = new ArrayList<>();

	public boolean isObstacle(Position position) {

		for (int i = 0; i < obstacles.size(); i++) {
			Position tmpPosition = obstacles.get(i);
			if (position.row == tmpPosition.row && position.column == tmpPosition.column)
				return true;
		}

		return false;

	}
	
	public boolean isFruit(Position position) {
		for (int i = 0; i < fruits.size(); i++) {
			Position tmpPosition = fruits.get(i);
			if (position.row == tmpPosition.row && position.column == tmpPosition.column)
				return true;
		}

		return false;
	}
	
	public void initFruits() {
		fruits.add(new Position(2, 1));
		fruits.add(new Position(2, 13));
		fruits.add(new Position(11, 1));
		fruits.add(new Position(11, 13));
	}

	public void initObstacles() {

		// Generate Left Obstacles
		obstacles.add(new Position(2, 2));
		obstacles.add(new Position(1, 4));
		obstacles.add(new Position(2, 4));
		obstacles.add(new Position(3, 4));
		obstacles.add(new Position(4, 4));

		obstacles.add(new Position(4, 2));
		obstacles.add(new Position(5, 2));
		obstacles.add(new Position(6, 2));

		obstacles.add(new Position(6, 3));

		obstacles.add(new Position(13, 4));
		obstacles.add(new Position(12, 4));
		obstacles.add(new Position(11, 4));
		obstacles.add(new Position(10, 4));

		obstacles.add(new Position(12, 2));
		obstacles.add(new Position(8, 2));
		obstacles.add(new Position(9, 2));
		obstacles.add(new Position(11, 2));

		obstacles.add(new Position(3, 6));

		// Generate Reflection
		int loopSize = obstacles.size();
		for (int i = 0; i < loopSize; i++) {

			Position tmpPosition = obstacles.get(i);
			Position newPosition = new Position(tmpPosition.row, Constant.COLUMN_CELL_COUNT - 1 - tmpPosition.column);
			obstacles.add(newPosition);
		}

		// Generate Center Obstacles
		obstacles.add(new Position(6, 6));
		obstacles.add(new Position(7, 6));
		obstacles.add(new Position(8, 6));
		obstacles.add(new Position(8, 7));
		obstacles.add(new Position(8, 8));

		obstacles.add(new Position(7, 8));
		obstacles.add(new Position(6, 8));

		obstacles.add(new Position(10, 7));
		obstacles.add(new Position(11, 7));
		obstacles.add(new Position(12, 7));

		obstacles.add(new Position(2, 7));
		obstacles.add(new Position(3, 7));
		obstacles.add(new Position(4, 7));

	}
}
