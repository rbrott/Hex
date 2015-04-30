package com.hex.model;

import android.graphics.Path;

/**
 * This class contains some static utility methods for manipulating hexagons.
 * @author Ryan Brott
 */
public class HexUtil {

	/**
	 * This method creates a drawable path of a hexagon centered at start.
	 * @param start Point at the center of a hexagon
	 * @param rawSize size of the hexagon (width)
	 * @param padding padding between hexagons
	 * @return drawable Path
	 */
	public static Path getPath(Point start, float rawSize, float padding) {
		Point canvasPoint = getDevicePoint(start, rawSize, padding);
		float startX = canvasPoint.x, startY = canvasPoint.y;
		float dx = rawSize / 2;
		float dy = dx / (float) Math.sqrt(3);
		Path path = new Path();
		path.moveTo(startX, startY + 2 * dy);
		path.lineTo(startX + dx, startY + dy);
		path.lineTo(startX + dx, startY - dy);
		path.lineTo(startX, startY - 2 * dy);
		path.lineTo(startX - dx, startY - dy);
		path.lineTo(startX - dx, startY + dy);
		path.lineTo(startX, startY + 2 * dy);
		path.close();
		return path;
	}

	/**
	 * This method converts a GameGrid point to a point with actual device screen coordinates.
	 * @param start center point of a hexagon in GameGrid coordinates
	 * @param rawSize size of the hexagon (width)
	 * @param padding padding between hexagons
	 * @return Point in device coordinates
	 */
	public static Point getDevicePoint(Point start, float rawSize, float padding) {
		float rawStartX = start.x, rawStartY = start.y;
		float size = rawSize + padding;
		float startX = rawStartX * size + size / 2;
		float startY = (rawStartY * size * 3F) / ((float) Math.sqrt(3) * 2F);
		return new Point(startX, startY);
	}

	/**
	 * This method returns the distance between two points.
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return the distance between the points
	 */
	public static float distance(Point p1, Point p2) {
		float x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y;
		return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

    /**
     * This method generates the neighbors of any given hexagon at point start.
     * @param start the point of the target hexagon
     * @return an array of the points of the neighbors
     */
	public static Point[] getNeighbors(Point start) {
		Point[] points = {
			new Point(start.x - .5F, start.y - 1),
			new Point(start.x + .5F, start.y - 1),
			new Point(start.x + 1, start.y),
			new Point(start.x + .5F, start.y + 1),
			new Point(start.x - .5F, start.y + 1),
			new Point(start.x - 1, start.y)
		};
		return points;
	}

}
