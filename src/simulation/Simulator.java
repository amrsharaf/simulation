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
	private PriorityQueue<Event> events = new PriorityQueue<Event>();

	/**
	 * Total number of jobs in the machining center, including the current job
	 * being served.
	 */
	private int nMachiningCenter;

	/**
	 * Simulation master clock.
	 */
	private double masterClock;

	/**
	 * Set the simulation master clock.
	 * 
	 * @param masterClock
	 *            the master clock to set.
	 */
	public void setMasterClock(double masterClock) {
		this.masterClock = masterClock;
	}
	
	/**
	 * Returns the simulation master clock.
	 * @return master clock.
	 */
	public double getMasterClock() {
		return masterClock;
	}

	/**
	 * Returns the total number of jobs in the machining center.
	 * 
	 * @return total number of jobs in machining center.
	 */
	public int getNmachiningCenter() {
		return nMachiningCenter;
	}

	/**
	 * This function is used to initialize the simulation attributes.
	 */
	public void init() {
		masterClock = 0.0;
		Event machiningCenterArrival = new MachiningCenterArrival(this);
		events.add(machiningCenterArrival);
	}

	/**
	 * Used to detect if the simulation has ended.
	 * 
	 * @return true when the simulation has ended.
	 */
	public boolean simulationDone() {
		return false;
	}

	/**
	 * This method is the main method used for running the simulation.
	 */
	public void runSimulation() {
		while (!simulationDone()) {
			// Select nearest event to execute
			Event nearestEvent = events.poll();
			// Handle the event
			nearestEvent.handleEvent();
		}
	}

	/**
	 * Application main entry point. Used to run the simulation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// start the simulation
		new Simulator().runSimulation();
	}
}
