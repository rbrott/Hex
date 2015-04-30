package com.hex.model;

/**
 * This interface allows classes to listen to onScore events.
 * @author Ryan Brott
 */
public interface ScoreListener {
    /**
     * This method is called when the game score is incremented.
     * @param incr the amount the score has been incremented by.
     */
    public void onScore(int incr);
}
