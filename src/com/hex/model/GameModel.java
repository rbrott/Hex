package com.hex.model;

public class GameModel implements ScoreListener {
	
	private int score, depth;
	private GameGrid grid;
	
	public GameModel(int depth) {
		score = 0;
		this.depth = depth;
		grid = new GameGrid(depth);
		grid.setOnScoreListener(this);
	}
	
	public void fireMotionEvent(Point target) {
		this.grid.rotate(target);
	}
	
	public int getScore() { return this.score; }
	public void incrementScore(int val) {
		this.score = this.score + val;
	}
	public void reset() { this.score = 0; }
	public GameGrid getGrid() {
		return this.grid;
	}
	public int getDepth() {
		return this.depth;
	}

	@Override
	public void onScore(int incr) {
		incrementScore(incr);
	}
}