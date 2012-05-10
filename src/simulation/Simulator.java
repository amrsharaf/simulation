package simulation;

import java.util.PriorityQueue;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to run the simulation
 * 
 */
public class Simulator {

	/**
	 * Priority queue used for storing the simulation events.
	 */
	PriorityQueue<Event> events = new PriorityQueue<Event>();
	
	/**
	 * Simulation master clock.
	 */
	double masterClock;

	/**
	 * This function is used to initialize the simulation attributes
	 */
	public void init() {
		masterClock = 0.0;
		Event machiningCenterArrival = new MachiningCenterArrival(this);
		events.add(machiningCenterArrival);
	}

	/**
	 * Used to detect if the simulation has ended
	 * 
	 * @return true when the simulation has ended
	 */
	public boolean simulationDone() {
		return false;
	}

	/**
	 * This method is the main method used for running the simulation.
	 */
	public void mainModule() {
		while (!simulationDone()) {
			// Select nearest event to execute
			Event nearestEvent = events.poll();
			// Handle the event
			nearestEvent.handleEvent();
		}
	}

	public static void main(String[] args) {
		new Simulator().mainModule();
	}
}
