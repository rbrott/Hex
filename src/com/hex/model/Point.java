package com.hex.model;

public class Point {
	
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public void setX(int val) { this.x = val; }
	public void setY(int val) { this.y = val; }
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
