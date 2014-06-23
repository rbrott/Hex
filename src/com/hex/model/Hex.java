package com.hex.model;

import java.util.ArrayDeque;

import android.graphics.Path;

public class Hex {
	
	public static int[] colors = {
		0xff0077ff,
		0xff9966cc,
		0xff3b7a57,
		0xffFfbf00,
		0xffE34234
	};
	
	public static Path getPath(Point start, int sideLength) {
		int startX = start.getX(), startY = start.getY();
		int factor = (int) Math.round((Math.sqrt(3.0)/2.0)*sideLength);
		int half = (int) Math.round(((double) sideLength)/2.0);
		Path path = new Path();
		path.moveTo(startX, startY);
		path.lineTo(startX+factor, startY+half);
		path.lineTo(startX+factor, startY+3*half);
		path.lineTo(startX, 2*sideLength+startY);
		path.lineTo(startX-factor, startY+3*half);
		path.lineTo(startX-factor, startY+half);
		path.lineTo(startX, startY);
		path.close();
		return path;
	}

	public static ArrayDeque<Point> getNeighbors(Point start, int sideLength) {
		int startX = start.getX(), startY = start.getY();
		ArrayDeque<Point> neighbors = new ArrayDeque<Point>();
		int factor = (int) Math.round((Math.sqrt(3.0)/2.0)*sideLength);
		int half = (int) Math.round(((double) sideLength)/2.0);
		Point[] points =  {
			new Point(startX+factor, startY-3*half),
			new Point(startX+factor*2, startY),
			new Point(startX+factor, startY+3*half),
			new Point(startX-factor, startY+3*half),
			new Point(startX-factor*2, startY),
			new Point(startX-factor, startY-3*half)
		};
		for (Point neighbor : points) {
			neighbors.add(neighbor);
		}
		return neighbors;
	}

}
