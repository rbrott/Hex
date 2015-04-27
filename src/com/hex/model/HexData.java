package com.hex.model;

/**
 * This class stores various information about each hexagon.
 * @author Ryan Brott
 */
public class HexData {

	public static int[] colors = {
		0xff0077ff,
		0xff9966cc,
		0xff3b7a57,
		0xffFfbf00,
		0xffE34234
	};

	public int color;

	/**
	 * This method creates a new HexData object with information about the corresponding hexagon.
	 * @param color color of the hexagon
	 */
	public HexData(int color) {
		this.color = color;
	}

}
