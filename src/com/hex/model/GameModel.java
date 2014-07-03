package com.hex.model;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import android.util.Log;
import android.view.MotionEvent;

public class GameModel {
	
	private int score;
	private int sideLength;
	private HashMap<String, HexData> grid;
	
	public GameModel() {
		score = 0;
		grid = new HashMap<String, HexData>();
	}
	
	public void generateGrid(Point start, int sideLength, int depth) {
		if (depth > 0) {
			if (!grid.containsKey(start.toString())) {
				grid.put(start.toString(), new HexData(Hex.colors[new Random().nextInt(Hex.colors.length)], depth));
			}
			for (Point neighbor : Hex.getNeighbors(start, sideLength)) {
				generateGrid(neighbor, sideLength, depth - 1);
			}
		}
	}
	
	public double distance(Point p1, Point p2) {
		int x1 = p1.getX(), y1 = p1.getY(), x2 = p2.getX(), y2 = p2.getY();
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	public void fireMotionEvent(MotionEvent motionEvent) {
		Point mouse = new Point((int) motionEvent.getX(), (int) motionEvent.getY()), key;
		HexData value;
		for (HashMap.Entry<String, HexData> entry : grid.entrySet()) {
			key = new Point(entry.getKey());
			value = entry.getValue();
			if (distance(mouse, key) <= sideLength && value.depth != 1) {
				grid.put(key.toString(), new HexData(0xFFFFFFFF, 1));
			}
		}
	}
	
	public int getScore() { return this.score; }
	public void incrementScore(int val) { this.score = this.score + val; }
	public void reset() { this.score = 0; }
	public HashMap<String, HexData> getGrid() { return this.grid; }
	public int getSideLength() { return this.sideLength; }

}