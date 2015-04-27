package com.hex.model;

public class GameModel {
	
	private int score, depth;
	private GameGrid grid;
	
	public GameModel(int depth) {
		score = 0;
		this.depth = depth;
		grid = new GameGrid(depth);
	}
	
	public static float distance(Point p1, Point p2) {
		float x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
		return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
//	public void fireMotionEvent(MotionEvent motionEvent) {
//		Point mouse = new Point((int) motionEvent.getX(), (int) motionEvent.getY()), key;
//		HexData value;
//		for (HashMap.Entry<String, HexData> entry : grid.entrySet()) {
//			key = Point.fromString(entry.getKey());
//			value = entry.getValue();
//			if (distance(mouse, key) <= sideLength) {
//				grid.put(key.toString(), new HexData(0xFFFFFFFF));
//			}
//		}
//	}
	
	public int getScore() { return this.score; }
	public void incrementScore(int val) { this.score = this.score + val; }
	public void reset() { this.score = 0; }
	public GameGrid getGrid() {
		return this.grid;
	}
	public int getDepth() {
		return this.depth;
	}

}