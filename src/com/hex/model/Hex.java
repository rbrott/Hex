package com.hex.model;

import android.graphics.Path;

/**
 * This class contains some static utility methods for manipulating hexagons.
 * @author Ryan Brott
 */
public class Hex {

	/**
	 * This method creates a drawable path of a hexagon centered at start.
	 * @param start Point at the center of a hexagon
	 * @param rawSize size of the hexagon (width)
	 * @param padding padding between hexagons
	 * @return drawable Path
	 */
	public static Path getPath(Point start, float rawSize, float padding) {
		float rawStartX = start.x, rawStartY = start.y;
		float size = rawSize + padding;
		float startX = rawStartX * size + size / 2;
		float startY = (rawStartY * size * 3F) / ((float) Math.sqrt(3) * 2F);
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

//	public static Point[] getNeighbors(Point start) {
//		Point[] points = {
//			new Point(start.x)
//		};
//		return points;
//	}

}
