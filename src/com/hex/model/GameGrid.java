package com.hex.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * This class encapsulates a game grid composed of layers of hexagons.
 * @author Ryan Brott
 */
public class GameGrid  {

    private ScoreListener listener = null;

    private HashMap<String, HexData> grid;
    private int depth;

    /**
     * This method initializes an empty game grid.
     * @param depth number of layers in the grid
     */
    public GameGrid(int depth) {
        this.depth = depth;
        this.grid = new HashMap<String, HexData>();
        Random rand = new Random();
        HexData hex;
        int layerSize = depth, limit = depth * 2 - 1, layerNum = 0;
        while (layerSize < limit) {
            for (int i = 0; i < layerSize; i++) {
                hex = new HexData();
                put(new Point((float) i - layerSize / 2F, (float) layerNum - limit / 2F), hex);
            }
            layerSize++;
            layerNum++;
        }
        while (layerSize >= depth) {
            for (int i = 0; i < layerSize; i++) {
                hex = new HexData(HexData.colors[rand.nextInt(HexData.colors.length)]);
                put(new Point((float) i - layerSize / 2F, (float) layerNum - limit / 2F), hex);
            }
            layerSize--;
            layerNum++;
        }

    }

    /**
     * This method sets the data of the hexagon at a Point.
     * @param point Point representing the location of the hexagon
     * @param hexData new hexagon data
     */
    public void put(Point point, HexData hexData) {
        this.grid.put(point.toString(), hexData);
    }

    /**
     * This method gets the data of the hexagon at a Point.
     * @param point Point representing the location of the hexagon
     * @return HexData
     */
    public HexData get(Point point) {
        return this.grid.get(point.toString());
    }

    /**
     * This method checks whether the grid contains a given point.
     * @param point the point to be tested for membership
     * @return a boolean representing whether the grid contains the point
     */
    public boolean contains(Point point) {
        return grid.containsKey(point.toString());
    }

    /**
     * This method returns a Set of Point string and HexData pairs
     * @return Set of Map entries
     */
    public Set<Map.Entry<String, HexData>> entrySet() {
        return this.grid.entrySet();
    }

    /**
     * This method sets the score listener for this grid instance.
     * @param listener the listener instance
     */
    public void setOnScoreListener(ScoreListener listener) {
        this.listener = listener;
    }

    /**
     * This method clears the score listener for this grid instance.
     */
    public void unsetOnScoreListener() {
        this.listener = null;
    }

    /**
     * This method checks whether or not a score listener is set for this grid instance.
     * @return <code>true</code> if the score listener is set
     */
    public boolean hasScoreListener() {
        return this.listener != null;
    }

    /**
     * This method rotates a ring of hexagons around a point clockwise once.
     * @param center the center point of the target hexagon
     */
    public void rotate(Point center) {
        Point[] neighbors = HexUtil.getNeighbors(center);
        for (Point p : neighbors) {
            if (!this.grid.containsKey(p.toString())) return;
        }
        HexData first = get(neighbors[0]);
        for (int i = 5; i > 0; i--) {
            put(neighbors[(i+1)%6], get(neighbors[i]));
        }
        put(neighbors[1], first);

        ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();
        ArrayList<Point> path;
        ArrayDeque<Point> frontier;
        Point next;

        for (Point neighbor : neighbors) {
            path = new ArrayList<Point>();
            frontier = new ArrayDeque<Point>();
            frontier.push(neighbor);
            while (!frontier.isEmpty()) {
                next = frontier.pollFirst();
                path.add(next);
                for (Point p : HexUtil.getNeighbors(next)) {
                    if (contains(p) && (get(p).color == get(next).color) && !path.contains(p) && !frontier.contains(p)) {
                        frontier.addLast(p);
                    }
                }
            }
            if (path.size() > 3) {
                paths.add(path);
            }
        }

        for (ArrayList<Point> list : paths) {
            if (hasScoreListener()) {
                this.listener.onScore((int) Math.pow(2, list.size()));
            }
            for (Point p : list) {
                put(p, new HexData());
            }
        }
    }
}
