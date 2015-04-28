package com.hex.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * This class encapsulates a game grid composed of layers of hexagons.
 * @author Ryan Brott
 */
public class GameGrid  {

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
                hex = new HexData(HexData.colors[rand.nextInt(HexData.colors.length)]);
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
     * This method returns a Set of Point string and HexData pairs
     * @return Set of Map entries
     */
    public Set<Map.Entry<String, HexData>> entrySet() {
        return this.grid.entrySet();
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
    }
}
