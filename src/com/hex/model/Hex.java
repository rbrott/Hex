package com.hex.model;

import java.util.Random;
import java.util.ArrayDeque;

import android.graphics.Path;
import android.view.MotionEvent;

public class Hex {
	
	public static int[] colors = {
		0xff0077ff,
		0xff9966cc,
		0xff3b7a57,
		0xffFfbf00,
		0xffE34234
	};
	
	private int color;
	private int sideLength;
	private Path path;
	private Point start;
	private ArrayDeque<Hex> neighbors;
	
	public Hex(Point start, int sideLength) {
		Random rand = new Random();
		this.color = Hex.colors[(int) Math.abs(rand.nextInt()) % Hex.colors.length];
		this.neighbors = null;
		this.start = start;
		this.sideLength = sideLength;
		int factor = (int) Math.round((Math.sqrt(3.0)/2.0)*sideLength);
		int half = (int) Math.round(((double) sideLength)/2.0);
		int startX = start.getX();
		int startY = start.getY();
		path = new Path();
		path.moveTo(startX, startY);
		path.lineTo(startX+factor, startY+half);
		path.lineTo(startX+factor, startY+3*half);
		path.lineTo(startX, 2*sideLength+startY);
		path.lineTo(startX-factor, startY+3*half);
		path.lineTo(startX-factor, startY+half);
		path.lineTo(startX, startY);
		path.close();
	}

	public Path getPath() { return this.path; }
	public void setNeighbors(ArrayDeque<Hex> neighbors) { this.neighbors = neighbors; }
	public ArrayDeque<Hex> getNeighbors() { return this.neighbors; }
	
	public void fireMotionEvent(MotionEvent motionEvent) {
		Point temp, temp2;
		Hex h = neighbors.removeFirst(), h3;
		neighbors.addLast(h);
		temp = h.getStart();
		ArrayDeque<Hex> temp3 = new ArrayDeque<Hex>();
		for (Hex h2 : neighbors) {
			temp2 = h2.getStart();
			h3 = new Hex(temp, sideLength);
			h3.setColor(h2.getColor());
			temp = temp2;
			temp3.add(h3);
		}
		setNeighbors(temp3);
	}
	
	public Point getStart() { return this.start; }
	public void setStart(Point p) { this.start = p; }
	public int getColor() { return this.color; }
	public void setColor(int val) { this.color = val; }
	
}
