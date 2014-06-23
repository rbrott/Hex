package com.hex.model;

public class Point {
	
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(String pointString) {
		String[] pointData = pointString.split(",");
		this.x = Integer.parseInt(pointData[0]);
		this.y = Integer.parseInt(pointData[1]);
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public void setX(int val) { this.x = val; }
	public void setY(int val) { this.y = val; }
	
	public String toString() {
		return this.x + "," + this.y;
	}

}
