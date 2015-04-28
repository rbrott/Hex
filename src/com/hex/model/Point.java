package com.hex.model;

/**
 * This class represents points in a Cartesian coordinate system with both an x and a y component.
 * @author Ryan Brott
 */
public class Point {
	
	public float x, y;

	/**
	 * This method creates a Point from an (x,y) pair.
	 * @param x x-coordinate of the pair
	 * @param y y-coordinate of the pair
	 */
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This static method creates a Point from a string created by Point#toString.
	 * @param pointString the String containing the point information
	 * @return the created Point object
	 */
	public static Point fromString(String pointString) {
		String[] pointData = pointString.split(",");
		float x = Float.parseFloat(pointData[0]);
		float y = Float.parseFloat(pointData[1]);
		return new Point(x, y);
	}

	/**
	 * This method returns a String representation of a Point object.
	 * @return the String representation
	 */
	public String toString() {
		return this.x + "," + this.y;
	}

	/**
	 * This method creates a clone of the current point.
	 * @return a clone of the current point
	 */
	public Point clone() {
		return new Point(x, y);
	}

}
