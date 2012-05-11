package servers;

import java.util.PriorityQueue;

import simulation.Item;
import simulation.Simulator;

import events.MachiningCenterArrival;
import events.MachiningCenterDeparture;

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
		// TODO: replace with a random generator
		serviceTime = 5;
		nearestDeparture = null;
		inRepair = false;
	}
	
	public void startService(Item item) {
		// Schedule a new departure event from machining center.
		MachiningCenterDeparture departure = new MachiningCenterDeparture(simulator);
		departure.setEventItem(item);
		double masterClock = simulator.getMasterClock();
		// Set the departure time to be the current simulation time + service
		// time.
		departure.setEventTime(masterClock + serviceTime);
		simulator.addEvent(departure);
		nearestDeparture = departure;
		// log(machine, AC[machine], MC, DC) // log entries : m, A, B, C
		// TODO: use random generation here
		serviceTime = 5;
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
