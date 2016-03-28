package com.company;

import java.util.Collection;
import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 *
 * According to the exercise instructions:
 * The simulation starts when calling start(), and ends when calling stop().
 *
 */
public interface ISimulator {

    /**
     * Starts the background loop.
     */
    void start();

    /**
     * Terminates the background loop.
     *
     * @return the items having maximum values at the moment of the termination.
     */
    Collection<Object> stop();
}
