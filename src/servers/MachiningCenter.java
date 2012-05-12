package servers;

import java.util.PriorityQueue;

import simulation.Item;
import simulation.Simulator;

import events.MachiningCenterArrival;
import events.MachiningCenterDeparture;
import generators.UniformRandomVariable;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to represent the machining center system (Queue +
 *         Service Unit).
 * 
 */
public class MachiningCenter {

	/**
	 * Machining center queue
	 */
	private PriorityQueue<MachiningCenterArrival> queue;
	
	/**
	 * Simulator controller.
	 */
	private Simulator simulator;

	/**
	 * Machining center service time
	 */
	private double serviceTime;
	
	/**
	 * Represents the closest departure event.
	 */
	private MachiningCenterDeparture nearestDeparture;

	/**
	 * Indicates if the machining center is under repair.
	 */
	private boolean inRepair;
	
	/**
	 * Used for generating uniform service times from [0.65, 0.75] minutes.
	 */
	private UniformRandomVariable random;
	
	/**
	 * Check if the machining center is in repair.
	 * @return
	 */
	public boolean isInRepair() {
		return inRepair;
	}
	
	/**
	 * Change the status of the machining service.
	 * @param value
	 */
	public void setInRepair(boolean value) {
		inRepair = value;
	}
	
	/**
	 * Returns the nearest departure event.
	 * @return
	 */
	public MachiningCenterDeparture getNearestDeparture() {
		return nearestDeparture;
	}
	
	/**
	 * Machining center constructor.
	 * @param simulator simulator controller.
	 */
	public MachiningCenter(Simulator simulator) {
		this.simulator = simulator;
		queue = new PriorityQueue<MachiningCenterArrival>();
		nearestDeparture = null;
		inRepair = false;
		long seed = simulator.getSeedGenerator().getNextSeed();
		random = new UniformRandomVariable(0.65, 0.70, seed);
		serviceTime = random.generate();
	}
	
	public void startService(Item item) {
		// Print the service time
		simulator.getMachiningCenterWriter().println(serviceTime);
		// Schedule a new departure event from machining center.
		MachiningCenterDeparture departure = new MachiningCenterDeparture(simulator);
		departure.setEventItem(item);
		double masterClock = simulator.getMasterClock();
		// Set the departure time to be the current simulation time + service
		// time.
		departure.setEventTime(masterClock + serviceTime);
		simulator.addEvent(departure);
		nearestDeparture = departure;
		serviceTime = random.generate();
	}
	
	/**
	 * Enqueue event at the waiting queue.
	 * @param event event to enqueue.
	 */
	public void enqueue(MachiningCenterArrival event) {
		queue.add(event);
	}
	
	/**
	 * Remove item from the waiting queue.
	 * @return nearest arrival item from waiting queue.
	 */
	public MachiningCenterArrival dequeue() {
		MachiningCenterArrival closestEvent = queue.poll();
		return closestEvent;
	}
}
